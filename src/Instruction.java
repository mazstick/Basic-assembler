public class Instruction {
    String opcode;
    String binOpcode;

    public byte[] castToBinary() {
        byte[] bytes = new byte[16];
        for (int i = 0; i < binOpcode.length(); i++) {
            if (binOpcode.charAt(i) == '0') {
                bytes[i] = 0;
            } else {
                bytes[i] = 1;
            }
        }
        return bytes;
    }
    public Instruction(String opcode,String binOpcode){
        this.opcode = opcode;
        this.binOpcode = binOpcode;
    }

}
