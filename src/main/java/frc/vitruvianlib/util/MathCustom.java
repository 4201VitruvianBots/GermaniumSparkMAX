package frc.vitruvianlib.util;

public class MathCustom {
    public static double Clamp(double input, double min, double max) {
        if(input > max) input = max;
        if(input < min) input = min;
        return input;
    }
}