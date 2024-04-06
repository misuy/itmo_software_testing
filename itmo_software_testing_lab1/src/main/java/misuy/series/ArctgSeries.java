package misuy.series;

import java.lang.Math;

public class ArctgSeries {
    private static double getTermValue(double arg, int termIdx)
    {
        return Math.pow(-1, termIdx) * Math.pow(arg, 2 * termIdx + 1) / (2 * termIdx + 1);
    }

    public static double getFunctionValue(double arg, int termsCount) throws IllegalArgumentException
    {
        if ((arg < -1) | (arg > 1))
            throw new IllegalArgumentException();

        double value = 0;
        for (int i=0; i<termsCount; i++)
            value += ArctgSeries.getTermValue(arg, i);
        return value;
    }
}
