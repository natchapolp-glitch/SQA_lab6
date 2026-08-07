package sqa.lab;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class QuadraticEquationEEDTTest {

    private final QuadraticEquation quadraticEquation = new QuadraticEquation();

    @ParameterizedTest(name = "Rule#{0}: a={1}, b={2}, c={3} -> {4}")
    @CsvSource({
        "1, 0, 6, 2,  NOT_QUADRATIC",
        "2, 0, 0, 10, NOT_QUADRATIC",
        "4, 2, 10, 3, REAL_ROOTS",
        "5, 1, 4, 4,  EQUAL_ROOTS",
        "6, 1, 2, 5,  IMAGINARY_ROOTS"
    })
    void testDetermineRootNature_EEDT(int ruleNo, int a, int b, int c, RootNature expected) {
        RootNature actual = quadraticEquation.determineRootNature(a, b, c);
        assertEquals(expected, actual,
            "Rule#" + ruleNo + " failed for a=" + a + ", b=" + b + ", c=" + c);
    }
    @ParameterizedTest(name = "Invalid input: a={0}, b={1}, c={2}")
    @CsvSource({
        "-1, 5, 3",    // a below lower bound
        "101, 5, 3",   // a above upper bound
        "1, -1, 3",    // b below lower bound
        "1, 101, 3",   // b above upper bound
        "1, 5, -1",    // c below lower bound
        "1, 5, 101"    // c above upper bound
    })
    void testDetermineRootNature_EEDT_InvalidInput(int a, int b, int c) {
        assertThrows(IllegalArgumentException.class,
            () -> quadraticEquation.determineRootNature(a, b, c),
            "Invalid input case failed for a=" + a + ", b=" + b + ", c=" + c);
    }
}