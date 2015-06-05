package projecto;

import desmoj.core.dist.LinearCongruentialRandomGenerator;
import java.io.IOException;

public class Simulation {
    
    private int n;
    private int groups;
    private boolean ex1a, ex1b, ex1c, ex2, ex3a, ex3b;
    private double alpha;
    private boolean ex1aAcepteds[];
    private boolean ex2Acepteds[];
    private Machines machines;

    public Simulation(int n, int groups, double alpha, boolean ex1a, boolean ex1b, boolean ex1c, boolean ex2, boolean ex3a, boolean ex3b) {
        this.n = n;
        this.groups = groups;
        this.ex1a = ex1a;
        this.ex1b = ex1b;
        this.ex1c = ex1c;
        this.ex2 = ex2;
        this.ex3a = ex3a;
        this.ex3b = ex3b;
        this.alpha = alpha;
        this.machines = new Machines(alpha, groups, "Machine1.txt", "Machine2.txt");
    }
    
    public void start() throws IOException {
        for (int i = 0; i < 42; i++) {
            if (ex1a) {
                System.out.println("Exercise 1, KS test");
                exercicio1a(new LinearCongruentialRandomGenerator(i), alpha, n);
            }
            
            if (ex1b) {
                //System.out.println("Exercise 1, Serial tests");
                exercicio1b(new LinearCongruentialRandomGenerator(), n, i);
            }
            
            if (ex1c) {
                System.out.println("Exercise 1, Runs test");
                exercicio1c(new LinearCongruentialRandomGenerator(i), n);
            }
        }
        
        for (int i = 0; i < 42; i++) {
            if (ex2) {
                System.out.println("Exercise 2");
                exercicio2(new LinearCongruentialRandomGenerator(i), new LinearCongruentialRandomGenerator(i), alpha, n);
            }
        }
        
        if (ex3a) {
            System.out.println("Exercise 3 a)");
            machines.startA();
        }
        
        if (ex3b) {
            System.out.println("Exercise 3 b)");
            machines.startB()
;        }
    }
    
    private boolean exercicio1a(LinearCongruentialRandomGenerator dist, double myAlpha, int size) {
        KSTest ks = new KSTest();
        double values[] = new double[size];
        double alpha = myAlpha;
        
        for (int k = 0; k < size; k++) {
            values[k] = dist.nextDouble();
        }
        
        return ks.kst(values, alpha);
    }
    
    private void exercicio1b(LinearCongruentialRandomGenerator dist, int size, int i) {
        double randomArray[];
        SerialTest serialTest = new SerialTest();
        
        for (int j = 2; j <= 3; j++) {
            randomArray = generateRandomArray(i, j * size);
            serialTest.setDimensions(j);
            serialTest.run(randomArray);
        }
    }
    
    private boolean exercicio1c(LinearCongruentialRandomGenerator dist, int size) {
        Runtest run = new Runtest();
        double values[] = new double[size];
        
        for (int k = 0; k < size; k++) {
            values[k] = dist.nextDouble();
        }
        
        return run.runTest(values);
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
    
    private static double[] generateRandomArray(int seed, int nElements) {
        double nums[] = new double[nElements];
        LinearCongruentialRandomGenerator randomGenerator = new LinearCongruentialRandomGenerator();
        randomGenerator.setSeed(seed);
 
        for (int i = 0; i < nElements; i++) {
            nums[i] = randomGenerator.nextDouble();
        }
 
        return nums;
    }
    
}


