import java.util.function.DoubleUnaryOperator;

class Main {
    //Stepik code: start
    public static double integrate(DoubleUnaryOperator f, double a, double b) {
        return f.applyAsDouble(a);
    }
//Stepik code: end
}
