public class IO extends Instruction{

    public IO(String opcode, String binOpcode) {
        super(opcode, binOpcode);
    }

    @Override
    public byte[] castToBinary() {
        return new byte[0];
    }
}
