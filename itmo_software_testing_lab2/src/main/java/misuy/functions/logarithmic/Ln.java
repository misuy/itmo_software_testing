package misuy.functions.logarithmic;

import misuy.functions.Function;

// maclaurin series for ln(x) = ln((1+z) / (1-z)) = 2 * sum_{i=0}^{+inf}(z^(2*i+1) / (2*i+1)), where z = (x-1) / (x+1)
public class Ln extends Function {
    @Override
    public double getValue(double arg, double accuracy) {
        double z = (arg - 1) / (arg + 1);
        System.out.println(z);
        double result = 0;
        double lastResult = accuracy + 1;
        int termIdx = 0;

        while (Math.abs(lastResult - result) > accuracy) {
            lastResult = result;
            result += 2 * Math.pow(z, 2 * termIdx + 1) / (2 * termIdx + 1);
            termIdx++;
        }

        return result;
    }
}
