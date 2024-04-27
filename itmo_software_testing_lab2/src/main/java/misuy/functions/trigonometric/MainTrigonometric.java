package misuy.functions.trigonometric;

import misuy.functions.Function;

public class MainTrigonometric extends Function {
    @Override
    public double getValue(double arg, double accuracy) {
        return Math.pow(new Tg().getValue(arg, accuracy) * new Sec().getValue(arg, accuracy) * (new Cos().getValue(arg, accuracy) - new Tg().getValue(arg, accuracy)), 3);
    }
}
