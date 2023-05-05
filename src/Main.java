import java.net.URI;
import java.util.*;

import javax.tools.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Sample code for analysis
        Scanner scan = new Scanner(System.in);
        StringBuilder code = new StringBuilder();

        System.out.println("Write down the code that you need to analyze for errors:\n");

        String line = "";
        while (!Objects.equals(line, "/end")) {
            line = scan.nextLine();
            if (!Objects.equals(line, "/end")) {
                code.append('\n').append(line);
            }
        }

        System.out.println();

        // Analyze the code for errors
        List<Diagnostic<? extends JavaFileObject>> diagnostics = analyzeCode(code.toString());

        // Print the errors to the console
        printErrors(diagnostics);
    }

    public static List<Diagnostic<? extends JavaFileObject>> analyzeCode(String code) {
        // Prepare the compiler
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();

        // Prepare the in-memory Java source file
        JavaFileObject source = new JavaSourceFromString("Example", code);

        // Compile the code
        JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, Collections.singletonList(source));
        boolean success = task.call();

        if (!success) {
            // Compilation failed, return the diagnostics
            return diagnostics.getDiagnostics();
        }

        // No errors, return an empty list
        return Collections.emptyList();
    }

    public static void printErrors(List<Diagnostic<? extends JavaFileObject>> diagnostics) {
        for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics) {
            System.out.println("Error: " + diagnostic.getMessage(null));
        }
    }

    // JavaFileObject implementation for in-memory Java source code
    static class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        JavaSourceFromString(String name, String code) {

            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
}
