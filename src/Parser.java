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
                if (!labelTable(parts[j], i + org - 1)) {
                    System.err.println("Empty label in line : " + (i + 1));
                    return null;
                }
                j++;
            }
            Instruction t = identifyInstruction(parts, j);
            if (t == null) {
                System.err.println("Invalid instruction(" + parts[j] + ") in line : " + (i + 1));
                return null;
            }
            if (t.getOpcode().equals("ORG")) {
                Pseudo pseudo = (Pseudo) t;
                org = Integer.parseInt(pseudo.getOperand(), 16);
            }
            instructions.add(t);
        }
        return instructions;
    }

    private boolean labelTable(String part, int line) {
        if (part.equals(",")) return false;
        Library.labelTable.put(part.substring(0, part.length() - 1), String.valueOf(line));
        return true;
    }

    private Instruction identifyInstruction(String[] parts, int j) {
        Instruction temp = Library.searchInLibrary(parts[j]);
        if (temp == null) return null;
        if (temp.getClass().equals(Memory.class)) {
            j++;
            Memory t;
            t = (Memory) temp;
            if (j >= parts.length) {
                System.err.print("Empty address or ");
                return null;
            }
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
            return temp;
        }
        return temp;
    }


    private boolean hasNextPart(int j, String[] parts) {
        return j == parts.length - 2;
    }
}
