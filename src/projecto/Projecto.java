package projecto;

import java.io.IOException;

public class Projecto {
    
    public static void main(String[] args) throws IOException {
        //Simulation (int n, int groups, double alpha, boolean ex1a, boolean ex1b, boolean ex1c, boolean ex1d, boolean ex2, boolean ex3a, boolean ex3b)
        Simulation simulation = new Simulation(600000, 2, 0.05, false, false, false, false, false, false);
        simulation.start(); 
    }
}
