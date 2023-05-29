package languages;

import javax.tools.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class Kotlin {

    public static void analyzeCode(String kotlinCode) {

        DiagnosticCollector<JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(diagnosticCollector, null, null);

        try {
            File file = createTempKotlinFile(kotlinCode);
            Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(List.of(file));
            JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, diagnosticCollector, null, null, compilationUnits);

            boolean success = task.call();
            if (success) {
                System.out.println("Kotlin code analyzed successfully with no errors or warnings.");
            } else {
                System.out.println("Kotlin code analysis completed with errors or warnings:");
                for (Diagnostic<? extends JavaFileObject> diagnostic : diagnosticCollector.getDiagnostics()) {
                    System.out.println(diagnostic);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileManager.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static File createTempKotlinFile(String kotlinCode) throws IOException {
        File file = File.createTempFile("temp", ".kt");
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.print(kotlinCode);
        }
        return file;
    }
}
