
import java.util.List;

public class Assembler {
    final int memoryCapacity = 4095;

    public void assemble(List<Instruction> instructions) {
        int org = 0;
        for (Instruction instruction : instructions) {
            if (instruction.getOpcode().equals("ORG")) {
                Pseudo p = (Pseudo) instruction;
                org = Integer.parseInt(p.getOperand(), 16);
                if (org > memoryCapacity) {
                    System.err.println("ORG out of memory capacity : " + p.getOperand() + " (HEX)");
                    return;
                }
                continue;
            }

            if (instruction.getOpcode().equals("END")) continue;

            if (instruction.getClass().equals(Pseudo.class)) {
                Pseudo pseudo = (Pseudo) instruction;
                buildBinCodeOfPseudo(pseudo);
            }

            if (instruction.getClass().equals(Memory.class)) {
                Memory memory = (Memory) instruction;
                buildBinCodeOfMemory(memory);
            }
            System.out.printf("%-6s     %s%n", Integer.toBinaryString(org), instruction.getBinOpcode());
            org++;
        }
    }

    private void buildBinCodeOfPseudo(Pseudo pseudo) {
        switch (pseudo.getOpcode()) {
            case "DEC":
                pseudo.setBinOpcode(decToBinary(pseudo.getOperand()));
                break;
            case "HEX":
                pseudo.setBinOpcode(hexToBinary(pseudo.getOperand()));
                break;
        }
    }

    private String hexToBinary(String operand) {
        int i = Integer.parseInt(operand, 16);
        return decToBinary(String.valueOf(i));
    }

    private String decToBinary(String dec) {
        int i = Integer.parseInt(dec);
        StringBuilder temp = new StringBuilder(Integer.toBinaryString(i));
        if (temp.length() < 16) {
            while (temp.length() < 16) {
                temp.insert(0, "0");
            }
        }
        if (temp.length() > 16) {
            temp.delete(0, temp.length() - 16);
        }
        return temp.toString();
    }

    private void buildBinCodeOfMemory(Memory memory) {
        String result = "";
        result += memory.getI();
        result += memory.getBinOpcode();
        String str = Library.searchInLabelTable(memory.getAddress());
        if (str == null) {
            memory.setBinOpcode("Invalid address");
            return;
        }
        int address = Integer.parseInt(str);
        StringBuilder temp = new StringBuilder(Integer.toBinaryString(address));
        if (temp.length() < 12) {
            while (temp.length() < 12) {
                temp.insert(0, "0");
            }
        }
        result += temp;
        memory.setBinOpcode(result);
    }
}
