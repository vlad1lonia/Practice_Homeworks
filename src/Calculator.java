public class Calculator {

    public static int add(int a, int b) {
        return a + b;
    }

    public static int subtract(int a, int b) {
        return a - b;
    }

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int divide(int a, int b) {
        if (b != 0) {
            return a / b;
        } else {
            throw new ArithmeticException("Cannot divide by zero");
        }
    }

    public static double exponentiation(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    public static double cos(double angle) {
        return Math.cos(angle);
    }

    public static double sin(double angle) {
        return Math.sin(angle);
    }

    public static double tan(double angle) {
        return Math.tan(angle);
    }

    public static double cot(double angle) {
        return 1 / Math.tan(angle);
    }
}
