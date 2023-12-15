import java.util.HexFormat;

public class Pseudo extends Instruction{
    String operand;
    public Pseudo(String name) {
        super(name,"");
    }

    @Override
    public byte[] castToBinary() {
        return new byte[0];
    }
    public void setOperand(String operand){
        this.opcode = operand;
    }
}
