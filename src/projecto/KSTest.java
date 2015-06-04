package projecto;

import java.util.Arrays;

public class KSTest {
    
    private int size;
    
    public boolean kst(double num[], double dalpha) {
        
        size = num.length;
        
        Arrays.sort(num);

        double d1[] = new double[num.length];
        double d2[] = new double[num.length];
        double n = (double) num.length;

        for (int i = 0; i < num.length; i++) {
            d1[i] = (i / n) - num[i];
        }

        for (int i = 0; i < num.length; i++) {
            d2[i] = (num[i] - (i - 1) / n);
        }

        double d1max = d1[0];
        for (int i = 0; i < num.length; i++) {
            if (d1max <= d1[i]) {
                d1max = d1[i];
            }
        }

        System.out.println("D+ = " + d1max);

        double d2max = d2[0];
        for (int i = 0; i < num.length; i++) {
            if (d2max <= d2[i]) {
                d2max = d2[i];
            }
        }

        System.out.println("D- = " + d2max);

        double dplus = d1max;
        double dminus = d2max;
        double d;

        if (dplus > dminus) {
            d = dplus;
            System.out.println("D = " + d);
        } else {
            d = dminus;
            System.out.println("D = " + d);
        }
        
        double cd = ( Math.sqrt(size) + 0.12 + ( 0.11/Math.sqrt(size) ) ) * d;

        if (1.358 > cd) {
            System.out.println("Given numbers uniformity accepted");
            return true;
        } else {
            System.out.println("Given numbers uniformity rejected");
            return false;
        }
    }
}
