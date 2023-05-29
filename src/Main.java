import languages.*;
import java.io.*;
import java.util.*;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    /**
     * The main programme
     * @param args
     */
    public static void main(String[] args) {

        System.out.print("Enter the language: "); String language = scan.next();
        System.out.print("Enter the path to code: "); String pathToCode = scan.next();

        try {
            String code = readCodeFromFile(pathToCode);
            analyzeCode(language, code);
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
    }

    /**
     * A method that reads code from file
     * @param filePath
     * @return A new instance of String
     * @throws IOException
     */
    private static String readCodeFromFile(String filePath) throws IOException {
        StringBuilder codeBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            codeBuilder.append(line).append("\n");
        }

        reader.close();
        return codeBuilder.toString();
    }

    /**
     * A method that paraemters and chooses which language has to be checked
     * @param language
     * @param code
     */
    private static void analyzeCode(String language, String code) {

        if (language.equalsIgnoreCase("java")) {
            // Perform Java code analysis
            System.out.println("Performing " + language + " code analysis...");
            Java.analyzeCode(code);
        } else if (language.equalsIgnoreCase("c++")) {
            // Perform C++ code analysis
            System.out.println("Performing " + language + " code analysis...");
            CPP.analyzeCode(code);
        } else if (language.equalsIgnoreCase("kotlin")) {
            // Perform Kotlin code analysis
            System.out.println("Performing " + language + " code analysis...");
            Kotlin.analyzeCode(code);
        } else if (language.equalsIgnoreCase("python")) {
            // Perform Python code analysis
            System.out.println("Performing " + language + " code analysis...");
            Python.analyzeCode(code);
        } else {
            System.out.println("Unsupported language: " + language);
        }
    }
}
