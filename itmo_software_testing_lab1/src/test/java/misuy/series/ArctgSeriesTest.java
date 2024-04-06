package misuy.series;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import java.lang.IllegalArgumentException;

class ArctgSeriesTest {

    @ParameterizedTest(name = "atan({0})")
    @DisplayName("Check default values")
    @ValueSource(doubles = {-1, -0.5, 0, 0.5, 1})
    void defaultValues(double x) {
        assertEquals(Math.atan(x), ArctgSeries.getFunctionValue(x, 300), 0.001);
    }

    @ParameterizedTest(name = "atan({0})")
    @DisplayName("Check illegal arguments")
    @ValueSource(doubles = {-1.0001, -100, 12, 1.5})
    void illegalArguments(double x) {
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.getFunctionValue(x, 300));
    }
}