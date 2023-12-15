import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        String assembleCode = selectProgram();
        System.out.println(assembleCode);
        Library.loadLibrary();
        Parser parser = new Parser();
        Assembler assembler = new Assembler();
        List<Instruction> instructions = parser.tokenizer(assembleCode);
        if (instructions == null) {
            return;
        }
        assembler.assemble(instructions);
    }

    private static String selectProgram() throws IOException {

        RandomAccessFile rfile23 = new RandomAccessFile(".\\test\\Add 100 Numbers Program.txt", "rw");
        RandomAccessFile rfil = new RandomAccessFile(".\\test\\Subtraction Program.txt", "rw");
        RandomAccessFile rfile = new RandomAccessFile(".\\test\\Mul Program.txt", "rw");
        RandomAccessFile rfile4 = new RandomAccessFile(".\\test\\Add Program.txt", "r");
        StringBuilder result = new StringBuilder();
        while (rfile.getFilePointer()<rfile.length()){
            result.append(rfile.readLine()).append("\n");
        }
        return result.toString();
    }
}