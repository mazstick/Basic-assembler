public class Memory extends Instruction {
    String address;
    byte I = 0;

    public Memory(String opcode, String binOpcode) {
        super(opcode, binOpcode);
    }
    @Override
    public byte[] castToBinary() {
        return new byte[0];
    }
    public void setI(){
        I = 1;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
