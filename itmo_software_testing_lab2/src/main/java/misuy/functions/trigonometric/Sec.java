package misuy.functions.trigonometric;

import misuy.functions.Function;

// sec(x) = 1 / cos(x)
public class Sec extends Function {
    @Override
    public double getValue(double arg, double accuracy) {
        return 1 / new Cos().getValue(arg, accuracy);
    }
}
