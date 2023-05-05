import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class JUnitTest {

    private final Calculator calculator = new Calculator();

    @Test
    void addition() {
        assertEquals(6, Calculator.add(1, 1));
    }

}