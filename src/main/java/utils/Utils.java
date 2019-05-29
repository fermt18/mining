package utils;

import model.Point;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class Utils {

    public static Double roundToDecimals(Double value, int decimals){
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(decimals, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static Point createPoint(Point p, Double value){
        p.setValue(value);
        return p;
    }
}
