package projecto;

import desmoj.core.dist.LinearCongruentialRandomGenerator;

public class Simulation {
    
    private int n;
    private double alpha;
    private boolean ex1Acepteds[];
    private boolean ex2Acepteds[];
    
    public Simulation(int n, int inc, double alpha) { 
        this.n = n;
        this.alpha = alpha;
        this.ex1Acepteds = new boolean[42];
        this.ex2Acepteds = new boolean[42];
    }
    
    public void start() {
        for (int i = 0; i < 42; i++) {
            ex1Acepteds[i] = exercicio1(new LinearCongruentialRandomGenerator(i), alpha, n);
            ex2Acepteds[i] = exercicio2(new LinearCongruentialRandomGenerator(i), new LinearCongruentialRandomGenerator(i), alpha, n);
        }
    }
    
    private boolean exercicio1(LinearCongruentialRandomGenerator dist, double myAlpha, int size) {
        KSTest ks = new KSTest();
        double values[] = new double[size];
        double alpha = myAlpha;
        
        for (int k = 0; k < size; k++) {
            values[k] = dist.nextDouble();
        }
        
        return ks.kst(values, alpha);
    }
    
    private boolean exercicio2 (LinearCongruentialRandomGenerator dist1, LinearCongruentialRandomGenerator dist2, double myAlpha, int size) {
        KSTest ks = new KSTest();
        double values[] = new double[size];
        double alpha = myAlpha;
        
        for (int i = 0; i < size; i++) {
            values[i] = Math.max(dist1.nextDouble(), dist2.nextDouble());
        }
        
        return ks.kst(values, alpha);
    } 
    
}


