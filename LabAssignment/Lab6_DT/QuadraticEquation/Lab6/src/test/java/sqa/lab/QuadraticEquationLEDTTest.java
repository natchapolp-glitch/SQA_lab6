package sqa.lab;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class QuadraticEquationLEDTTest {

    private final QuadraticEquation quadraticEquation = new QuadraticEquation();

    @ParameterizedTest(name = "Rule#{0}: a={1}, b={2}, c={3} -> {4}")
    @CsvSource({
        "2, 0, 5, 3, NOT_QUADRATIC",
        "3, 0, 0, 5, NOT_QUADRATIC",
        "6, 1, 5, 1, REAL_ROOTS",
        "7, 1, 2, 1, EQUAL_ROOTS",
        "8, 1, 1, 1, IMAGINARY_ROOTS"
    })
    void testDetermineRootNature_LEDT(int ruleNo, int a, int b, int c, RootNature expected) {
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
    void testDetermineRootNature_LEDT_InvalidInput(int a, int b, int c) {
        assertThrows(IllegalArgumentException.class,
            () -> quadraticEquation.determineRootNature(a, b, c),
            "Invalid input case failed for a=" + a + ", b=" + b + ", c=" + c);
    }
}