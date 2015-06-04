package projecto;

import java.io.IOException;

public class Projecto {
    
    public static void main(String[] args) throws IOException {
        Simulation simulation = new Simulation(600000, 1, 0.05);
        simulation.start();
        /*Ex. 3
        double alpha = 0.05;
        int groups = 2;
        Machines machines = new Machines(alpha, groups);*/
        
    }
}
