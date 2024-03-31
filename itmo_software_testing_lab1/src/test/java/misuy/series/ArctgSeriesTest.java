package misuy.series;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArctgSeriesTest {

    @Test
    void getFunctionValue() {
        assertEquals(ArctgSeries.getFunctionValue(0, 100), 0);
        assertEquals(ArctgSeries.getFunctionValue(-0.5, 100), Math.atan(-0.5), 0.00001);
    }
}