import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = pathBuilder();
        String assembleCode = selectProgram(path);
        System.out.println(assembleCode);
        Library.loadLibrary();
        Parser parser = new Parser();
        Assembler assembler = new Assembler();
        List<Instruction> instructions = parser.tokenizer(assembleCode);
        if (instructions == null) {
            return;
        }
        System.out.println("-------------------------------------");
        String machineCode = assembler.assemble(instructions);
        machineCodeToFlie(machineCode, path);
    }
    private static void machineCodeToFlie(String machineCode, String path) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("-------------------------------------");
        System.out.println("Do you want to store machine code ?\n" +
                "1.YES\n" +
                "2.NO\n");
        int command = scanner.nextInt();
            if (command == 1) {
                writeMachineFile(path, machineCode);
            }
    }

    private static void writeMachineFile(String path, String machineCode) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(path);
        stringBuilder.insert(stringBuilder.length()-4,"(Machine Code)");
        RandomAccessFile rFile = new RandomAccessFile(stringBuilder.toString(),"rw");
        rFile.writeBytes(machineCode);
    }

    private static String selectProgram(String path) throws IOException {
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
        String temp = s + "." + name;
        menu.writeBytes("\n");
        menu.write(temp.getBytes());
    }
}