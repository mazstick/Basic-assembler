import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<Instruction> tokenizer(String assembleCode) {
        List<Instruction> instructions = new ArrayList<>();
        String[] lines = assembleCode.split("\n");
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("\\s+");
            int j = 0;
            if (parts[j].contains(",")) {
                //add this to label table (based on HashMap)
                j++;
            }
            instructions.add(identifyInstruction(parts, j));
        }
        return instructions;
    }

    private Instruction identifyInstruction(String[] parts, int j) {
        Instruction temp = Library.searchInLibrary(parts[j]);
        if (temp.equals(Memory.class)) {
            j++;
            Memory t;
            t = (Memory) temp;
            t.setAddress(parts[j]);
            if (hasNextPart(j, parts)) {
                j++;
                if (parts[j].equals("I")) t.setI();
            }
            return t;
        }
        if (temp.equals(Pseudo.class)) {
            Pseudo t;
            t = (Pseudo) temp;
            if (t.opcode.equals("END")) {
                return t;
            }
            j++;
            t.setOperand(parts[j]);
            return t;
        }
        return temp;
    }

    private boolean hasNextPart(int j, String[] parts) {
        return j == parts.length - 2;
    }
}