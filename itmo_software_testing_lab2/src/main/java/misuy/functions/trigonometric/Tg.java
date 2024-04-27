package misuy.functions.trigonometric;

import misuy.functions.Function;

// tg(x) = sin(x) / cos(x)
public class Tg extends Function {
    @Override
    public double getValue(double arg, double accuracy) {
        return new Sin().getValue(arg, accuracy) / new Cos().getValue(arg, accuracy);
    }
}
