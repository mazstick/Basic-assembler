
public class Pseudo extends Instruction{
    String operand;
    public Pseudo(String name) {
        super(name,"");
    }


    public void setOperand(String operand){
        this.operand = operand;
    }
    public String getOperand(){
        return operand;
    }
}
