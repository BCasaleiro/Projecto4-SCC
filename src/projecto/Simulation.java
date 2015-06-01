package projecto;

import desmoj.core.dist.LinearCongruentialRandomGenerator;

public class Simulation {
    
    private int n;
    private int inc;
    private double alpha;
    private boolean cona[];
    
    public Simulation(int n, int inc, double alpha) { 
        this.n = n;
        this.alpha = alpha;
        this.cona = new boolean[42];
    }
    
    public void start() {
        for (int i = 0; i < 42; i++) {
            cona[i] = puta(new LinearCongruentialRandomGenerator(i), alpha, n);
        }
    }
    
    private boolean puta(LinearCongruentialRandomGenerator dist, double myAlpha, int size) {
        KSTest ks = new KSTest();
        double values[] = new double[size];
        double alpha = myAlpha;
        
        for (int k = 0; k < size; k++) {
            values[k] = dist.nextDouble();
        }
        
        return ks.kst(values, alpha);
    }
    
}
