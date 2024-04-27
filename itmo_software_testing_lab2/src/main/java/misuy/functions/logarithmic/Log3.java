package misuy.functions.logarithmic;

import misuy.functions.Function;

public class Log3 extends Function {
    @Override
    public double getValue(double arg, double accuracy) {
        return new Ln().getValue(arg, accuracy) / new Ln().getValue(3, accuracy);
    }
}
