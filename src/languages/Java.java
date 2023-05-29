package languages;

import javax.tools.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;

public class Java {

    public static void analyzeCode(String code) {
        // Analyze the code for errors
        List<Diagnostic<? extends JavaFileObject>> diagnostics = analyzeJavaCode(code);

        // Print the errors to the console
        printErrors(diagnostics);
    }

    private static List<Diagnostic<? extends JavaFileObject>> analyzeJavaCode(String code) {
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

    private static void printErrors(List<Diagnostic<? extends JavaFileObject>> diagnostics) {
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
