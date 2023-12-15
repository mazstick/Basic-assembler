import java.util.ArrayList;
import java.util.List;

public class Parser {
    public List<Instruction> tokenizer(String assembleCode) {
        List<Instruction> instructions = new ArrayList<>();
        String[] lines = assembleCode.split("\n");
        int org = 0;
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split("\\s+");
            int j = 0;
            if (parts[j].contains(",")) {
                labelTable(parts[j], i + org);
                j++;
            }
            Instruction t = identifyInstruction(parts, j);
            if (t.getOpcode().equals("ORG")) {
                Pseudo pseudo = (Pseudo) t;
                org = Integer.parseInt(pseudo.getOperand(),16);
            }
            instructions.add(t);
        }
        return instructions;
    }

    private void labelTable(String part, int line) {
        Library.labelTable.put(part.substring(0, part.length() - 1), String.valueOf(line));
    }

    private Instruction identifyInstruction(String[] parts, int j) {
        Instruction temp = Library.searchInLibrary(parts[j]);
        if (temp.getClass().equals(Memory.class)) {
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
        if (temp.getClass().equals(Pseudo.class)) {
            Pseudo t;
            t = (Pseudo) temp;
            if (t.getOpcode().equals("END")) {
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
