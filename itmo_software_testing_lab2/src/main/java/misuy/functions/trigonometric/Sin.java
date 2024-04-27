package misuy.functions.trigonometric;

import misuy.functions.Function;

// maclaurin series for sin(x) = sum_{i=0}^{+inf}((-1)^i * x^(2*i+1) / (2*i+1)!)
public class Sin extends Function {
    @Override
    public double getValue(double arg, double accuracy) {
        double result = 0;
        double lastResult = accuracy + 1;
        double term = 0;
        int termIdx = 0;

        while (Math.abs(result - lastResult) > accuracy) {
            lastResult = result;
            term = Math.pow(-1, termIdx) * Math.pow(arg, 2 * termIdx + 1);
            for (int i = 1; i <= 2 * termIdx + 1; i++) {
                term /= i;
            }
            termIdx++;
            result += term;
        }

        return Math.abs(result) > 1 ? result / Math.abs(result) : result;
    }
}
