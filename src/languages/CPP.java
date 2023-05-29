package languages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPP {

    public static void analyzeCode(String code) {

        // Define regular expressions for common syntax errors
        Pattern missingSemicolonPattern = Pattern.compile("[^;}]\\s*\\n");
        Pattern missingClosingBracePattern = Pattern.compile("\\{\\s*\\n");
        Pattern unresolvedSymbolPattern = Pattern.compile("\\b[a-zA-Z_][a-zA-Z0-9_]*\\b");

        Matcher missingSemicolonMatcher = missingSemicolonPattern.matcher(code);
        Matcher missingClosingBraceMatcher = missingClosingBracePattern.matcher(code);
        Matcher unresolvedSymbolMatcher = unresolvedSymbolPattern.matcher(code);

        // Check for missing semicolons
        while (missingSemicolonMatcher.find()) {
            int lineNumber = getLineNumber(code, missingSemicolonMatcher.start());
            System.out.println("Syntax Error: Missing semicolon at line " + lineNumber);
        }

        // Check for missing closing braces
        while (missingClosingBraceMatcher.find()) {
            int lineNumber = getLineNumber(code, missingClosingBraceMatcher.start());
            System.out.println("Syntax Error: Missing closing brace at line " + lineNumber);
        }

        // Check for unresolved symbols
        while (unresolvedSymbolMatcher.find()) {
            String symbol = unresolvedSymbolMatcher.group();
            int lineNumber = getLineNumber(code, unresolvedSymbolMatcher.start());
            System.out.println("Warning: Unresolved symbol '" + symbol + "' at line " + lineNumber);
        }
    }

    private static int getLineNumber(String code, int index) {
        String substring = code.substring(0, index);
        return substring.split("\n").length;
    }
}
