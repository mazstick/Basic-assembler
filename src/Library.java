import java.util.HashMap;

public class Library {
    static HashMap<String, Instruction> instructions = new HashMap<>();

    private static void libraryOfInstructions() {
        //Register Instruction
        instructions.put("CLA", new Register("CLA", "0111100000000000"));
        instructions.put("CLE", new Register("CLE", "0111010000000000"));
        instructions.put("CMA", new Register("CMA", "0111001000000000"));
        instructions.put("CME", new Register("CME", "0111000100000000"));
        instructions.put("CIR", new Register("CIR", "0111000010000000"));
        instructions.put("CIL", new Register("CIL", "0111000001000000"));
        instructions.put("INC", new Register("INC", "0111000000100000"));
        instructions.put("SPA", new Register("SPA", "0111000000010000"));
        instructions.put("SNA", new Register("SNA", "0111000000001000"));
        instructions.put("SZA", new Register("SZA", "0111000000000100"));
        instructions.put("SZE", new Register("SZE", "0111000000000010"));
        instructions.put("HLT", new Register("HLT", "0111000000000001"));
        //IO Instructions
        instructions.put("INP", new IO("INP", "1111100000000000"));
        instructions.put("OUT", new IO("OUT", "1111010000000000"));
        instructions.put("SKI", new IO("SKI", "1111001000000000"));
        instructions.put("SKO", new IO("SKO", "1111000100000000"));
        instructions.put("ION", new IO("ION", "1111000010000000"));
        instructions.put("IOF", new IO("IOF", "1111000001000000"));
        //Pseudo Instruction
        instructions.put("ORG", new Pseudo("ORG"));
        instructions.put("END", new Pseudo("END"));
        instructions.put("DEC", new Pseudo("DEC"));
        instructions.put("HEX", new Pseudo("HEX"));
        //Memory Instruction
        instructions.put("AND" , new Memory("AND" ,"000"));
        instructions.put("ADD" , new Memory("ADD" ,"001"));
        instructions.put("LDA" , new Memory("LDA" ,"011"));
        instructions.put("STA" , new Memory("STA" ,"100"));
        instructions.put("BUN" , new Memory("BUN" ,"101"));
        instructions.put("BSA" , new Memory("BSA" ,"110"));
        instructions.put("ISZ" , new Memory("ISZ" ,"111"));
    }
    public static Instruction searchInLibrary(String s){
        return instructions.get(s);
    }
}
