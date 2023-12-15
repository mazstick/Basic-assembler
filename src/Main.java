import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        String path = pathBuilder();
        RandomAccessFile rfile = new RandomAccessFile(path, "r");
        StringBuilder result = new StringBuilder();
        while (rfile.getFilePointer() < rfile.length()) {
            result.append(rfile.readLine()).append("\n");
        }
        return result.toString();
    }

    private static String pathBuilder() throws IOException {
        Scanner scanner = new Scanner(System.in);
        StringBuilder result = new StringBuilder();
        RandomAccessFile menu = new RandomAccessFile(".\\test\\menu.txt", "rw");
        while (menu.getFilePointer() < menu.length()) {
            result.append(menu.readLine()).append("\n");
        }
        System.out.println(result);
        int command = scanner.nextInt();
        menu.seek(0);
        for (int i = 0; i < command-1; i++) {
            menu.readLine();
        }
        menu.seek(menu.getFilePointer()+2);
        StringBuilder path = new StringBuilder(menu.readLine());
        path.insert(0,".\\test\\");
        path.append(".txt");
        return path.toString();
    }
}