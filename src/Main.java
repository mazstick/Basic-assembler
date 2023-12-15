import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library.loadLibrary();
        String assembleCode = "ORG 0 \n" +
                "LDA A \n" +
                "ADD B \n" +
                "STA C \n" +
                "HLT \n" +
                "A, DEC 83 \n" +
                "B, DEC -23 \n" +
                "C, DEC 0 \n" +
                "END";


        Parser parser = new Parser();
        Assembler assembler = new Assembler();
        List<Instruction> instructions = parser.tokenizer(assembleCode);
        assembler.assemble(instructions);
    }
}