public class Memory extends Instruction {
    private String address;
    private byte I = 0;

    public Memory(String opcode, String binOpcode) {
        super(opcode, binOpcode);
    }

    public void setI(){
        I = 1;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte getI() {
        return I;
    }

    public String getAddress() {
        return address;
    }
}
