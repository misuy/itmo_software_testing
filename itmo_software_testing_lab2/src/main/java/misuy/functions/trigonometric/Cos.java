package misuy.functions.trigonometric;

import misuy.functions.Function;

// sin(x)^2 + cos(x)^2 = 1 => cos(x) = (1 - sin(x)^2)^(1/2)
public class Cos extends Function {
    @Override
    public double getValue(double arg, double accuracy) {
        double argMod2PI = Math.abs(arg) - Math.floor(Math.abs(arg / (2 * Math.PI))) * (2 * Math.PI);
        double result = Math.sqrt(1 - Math.pow(new Sin().getValue(arg, accuracy), 2));
        return (argMod2PI > (Math.PI / 2)) & (argMod2PI < (3 * Math.PI / 2)) ? -result : result;
    }
}
