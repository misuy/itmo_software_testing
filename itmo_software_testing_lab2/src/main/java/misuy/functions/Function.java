package misuy.functions;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class Function {
    public void valuesToCsv(OutputStream stream, double argStart, double argEnd, double argStep, double accuracy) throws IOException {
        StringBuilder builder = new StringBuilder();
        builder.append("x,f(x)\n");
        double arg = argStart;
        while (arg <= argEnd) {
            builder.append(arg);
            builder.append(",");
            builder.append(this.getValue(arg, accuracy));
            builder.append("\n");
            arg += argStep;
        }
        stream.write(builder.toString().getBytes(StandardCharsets.UTF_8));
    }

    abstract public double getValue(double arg, double accuracy);
}
