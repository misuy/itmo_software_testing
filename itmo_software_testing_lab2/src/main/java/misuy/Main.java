package misuy;

import misuy.functions.logarithmic.Ln;
import misuy.functions.trigonometric.Cos;
import misuy.functions.trigonometric.Sin;
import misuy.functions.trigonometric.Tg;

import java.io.FileOutputStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            FileOutputStream file = new FileOutputStream("test_sin");
            new Ln().valuesToCsv(file, 0.0000001, 100, 1, 0.000000001);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
