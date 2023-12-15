import java.util.Optional;

public class Register extends Instruction{
    public Register(String opcode ,String binOpcode){
        super(opcode,binOpcode);
    }

    @Override
    public byte[] castToBinary() {
        return new byte[0];
    }
}
