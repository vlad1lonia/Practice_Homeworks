package languages;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Python {

    public static void analyzeCode(String pythonCode) {

        // Construct the command to execute Python code and capture the output
        String[] command = {"python", "-c", pythonCode};
        ProcessBuilder processBuilder = new ProcessBuilder(command);

        try {
            // Start the process and read its output
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                // Analyze the output for errors and warnings
                if (line.startsWith("Traceback") || line.startsWith("SyntaxError") || line.startsWith("Warning")) {
                    System.out.println(line);
                }
            }

            // Wait for the process to finish
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
