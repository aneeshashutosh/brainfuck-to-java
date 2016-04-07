import java.util.Scanner;

import static java.lang.System.out;

/**
 * @author: aneeshashutosh
 */

public class BrainfuckToJavaCompiler {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        out.println("Enter your Brainfuck code below.");
        String brainfuckSourceCode = scanner.next();
        out.println(buildSourceCode(brainfuckSourceCode));
    }

    public static String buildSourceCode(String code) {
        StringBuilder brainfuckSourceCode = new StringBuilder();
        if (code.contains(",")) {
            brainfuckSourceCode.append("import java.util.Scanner;\n");
        }
        brainfuckSourceCode.append("public class BrainFuckCode {\n");
        brainfuckSourceCode.append("\tprivate static byte[] memory = new byte[65535];\n");
        brainfuckSourceCode.append("\tprivate static int pointer;\n");
        if (code.contains(",")) {
            brainfuckSourceCode.append("\tprivate static Scanner scanner = new Scanner(System.in);\n");
        }
        brainfuckSourceCode.append("\n\tpublic static void main(String[] args) {\n");

        int indent = 0;
        for (int i = 0; i < code.length(); i++) {
            for (int t = 0; t < indent; t++) {
                brainfuckSourceCode.append('\t');
            }
            switch (code.charAt(i)) {
                case '>':
                    brainfuckSourceCode.append("\t\tpointer++;\n");
                    break;
                case '<':
                    brainfuckSourceCode.append("\t\tpointer--;\n");
                    break;
                case '+':
                    brainfuckSourceCode.append("\t\tmemory[pointer]++;\n");
                    break;
                case '-':
                    brainfuckSourceCode.append("\t\tmemory[pointer]--;\n");
                    break;
                case '.':
                    brainfuckSourceCode.append("\t\tSystem.out.print((char) memory[pointer]);\n");
                    break;
                case ',':
                    brainfuckSourceCode.append("\t\tmemory[pointer] = (byte) scanner.next().charAt(0);\n");
                    break;
                case '[':
                    brainfuckSourceCode.append("\t\twhile (memory[pointer] != 0) {\n");
                    indent++;
                    break;
                case ']':
                    brainfuckSourceCode.append("\t\t}\n");
                    break;
                default:
                    out.println("Invalid Brainfuck code.");
                    System.exit(-1);
            }
            if (i + 1 < code.length() && code.charAt(i + 1) == ']') indent--;
        }


        brainfuckSourceCode.append("\t}\n");
        brainfuckSourceCode.append("}\n");
        return brainfuckSourceCode.toString();
    }
}