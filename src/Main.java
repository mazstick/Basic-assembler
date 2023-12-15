import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
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
        if (command == 0) {
            writeFile(menu);
            return pathBuilder();
        }
        menu.seek(0);
        for (int i = 0; i < command; i++) {
            menu.readLine();
        }
        menu.seek(menu.getFilePointer() + 2);
        StringBuilder path = new StringBuilder(menu.readLine());
        path.insert(0, ".\\test\\");
        path.append(".txt");
        return path.toString();
    }

    private static void writeFile(RandomAccessFile menu) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your program name :");
        String name = scanner.nextLine();
        System.out.println("Write your program :");
        String code = "";
        String str = "";
        while (scanner.hasNextLine()) {
            str = scanner.nextLine();
            code += str + "\n";
            if (str.equals("END")) break;
        }
        String path = ".\\test\\" + name + ".txt";
        RandomAccessFile rfile = new RandomAccessFile(path, "rw");
        rfile.write(code.getBytes());
        rfile.close();
        int counter = 0;
        menu.seek(0);
        while (menu.getFilePointer() < menu.length() - 1) {
            menu.readLine();
            counter++;
        }
        menu.seek(menu.length());
        String s = String.valueOf(counter);
        String temp = s +"."+ name;
       menu.writeBytes("\n");
        menu.write(temp.getBytes());
    }
}