public class Instruction {
    private String opcode;
    private String binOpcode;

    public String getBinOpcode() {
        return binOpcode;
    }

    public void setBinOpcode(String binOpcode) {
        this.binOpcode = binOpcode;
    }

    public Instruction(String opcode, String binOpcode) {
        this.opcode = opcode;
        this.binOpcode = binOpcode;
    }
    public String getOpcode(){
        return opcode;
    }
}
