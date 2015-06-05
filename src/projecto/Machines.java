/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Machines {
    
    private List <Double> machine1 = new ArrayList <Double>();
    private List <Double> machine2 = new ArrayList <Double>();
    private List <Double> machines = new ArrayList <Double>();
    private String file1;
    private String file2;
    private int sum1;
    private int sum2;
    private double alpha;
    private int groups;
    private int df;
    private double value;
    
    public Machines (double alpha, int groups, String file1, String file2) {
        this.alpha=alpha;
        this.groups=groups;
        this.df = groups-1;
        this.sum1 = 0;
        this.sum2 = 0;
        this.value = 3.84146;
        this.file1 = file1;
        this.file2 = file2;
    }
    
    public void startA() throws IOException {
        File fileTemp1 = new File(file1);
        File fileTemp2 = new File(file2);
        readFiles(fileTemp1, fileTemp2);
        sortMachines();
        getSums();
        testStatistic();
    }
    
    public void startB() throws IOException {
        File fileTemp1 = new File(file1);
        File fileTemp2 = new File(file2);
        
        readFiles(fileTemp1, fileTemp2);
        
        weibullDistribution(machine1);
        weibullDistribution(machine2);
    }
    
    void testStatistic()
    {
        double H;
        //primeiro valor calculado no wolframalpha
        //H=(0.0000748129675810473815461346633416458852867830423940 * ((Math.pow(42757,2)/200) +(Math.pow(37443,2)/200))) - (3 * 401);
        H=(((double)12/(400*401)) * ((Math.pow(42757,2)/200) +(Math.pow(37443,2)/200))) - (3 * 401);

        
        System.out.println("H: " + H);
        
        if(H>value)
        {
            System.out.println("Not homogeneous at alpha=0.05");
        }
        else{
            System.out.println("Homogeneous at alpha=0.05");
        }
    }
    
    
    void getSums(){
        for(int i = 0;i<machine1.size();i++)
        {
            sum1+=machines.indexOf(machine1.get(i))+1;
        }
        for(int i = 0;i<machine2.size();i++)
        {
            sum2+=machines.indexOf(machine2.get(i))+1;
        }
    }
    void sortMachines()
    {
        machines.addAll(machine1);
        machines.addAll(machine2);
        
        double aux;
        for(int i = 0;i<machines.size();i++)
        {
            for(int j = 0;j<machines.size()-1;j++){
                if(machines.get(j)>machines.get(j+1))
                {
                    aux = machines.get(j);
                    machines.set(j, machines.get(j+1));
                    machines.set(j+1, aux);
                    
                }
            }
        }
    }
    //Machines1.txt e Machines2.txt
    void readFiles(File file1, File file2) throws IOException
    {
        try(FileReader fr = new FileReader(file1);
            BufferedReader br = new BufferedReader(fr))
        {
            while(br.ready()){
                machine1.add(Double.parseDouble((String)br.readLine()));
                
            }   
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        try(FileReader fr = new FileReader(file2);
            BufferedReader br = new BufferedReader(fr))
        {
            while(br.ready()){
                machine2.add(Double.parseDouble(br.readLine()));
                
            }   
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    void printMachines()
    {
        for(int i = 0;i<machines.size();i++) {
            System.out.println(i + " " + machines.get(i));
        }
    }
    
    void weibullDistribution(List<Double> machine) {

        double A = 0;
        //Calcular o A
        for (int i = 0; i < machine.size(); i++) {
            A += Math.log(machine.get(i));
        }

        A = A / machine.size();

        double aux1 = 0;
        double aux2 = 0;

        for (int i = 0; i < machine.size(); i++) {
            aux1 += Math.pow(Math.log(machine.get(i)), 2);
            aux2 += Math.log(machine.get(i));
        }

        aux2 = Math.pow(aux2, 2);

        double a0;
        a0 = aux1 - aux2 / machine.size();
        a0 = a0 * (6 / (Math.pow(Math.PI, 2)));
        a0 = a0 * ((double) 1 / (machine.size() - 1));
        a0 = (double) 1 / Math.sqrt(a0);

        System.out.println("a0: " + a0);

        double ak = a0;

        double ak1 = 0;

        //critério de paragem: ak - ak+1 < 10^-3
        double temp = ak;
        int l = 0;
        while (temp - ak1 > Math.pow(10, -3)) {
            double bK = 0, cK = 0, hK = 0;

            if (l++ != 0) {
                ak = ak1;
            }

            for (int j = 0; j < machine.size(); j++) {
                bK += Math.pow(machine.get(j), ak);
            }

            //Calcular CK
            for (int j = 0; j < machine.size(); j++) {
                cK += (Math.pow(machine.get(j), ak) * Math.log(machine.get(j)));
            }

            //Calcular o H
            for (int j = 0; j < machine.size(); j++) {
                hK += Math.pow(machine.get(j), ak) * Math.pow(Math.log(machine.get(j)), 2);
            }

            //Calcular o ak+1
            ak1 = ak + (A + (1 / ak) - (cK / bK)) / ((1 / Math.pow(ak, 2)) + ((bK * hK - Math.pow(cK, 2)) / Math.pow(bK, 2)));

            temp = ak;

        }
        System.out.println("Alpha: " + ak1);

        double beta = 0;
        for (int i = 0; i < machine.size(); i++) {
            beta += (Math.pow(machine.get(i), ak1));
        }
        beta = beta / machine.size();
        beta = Math.pow(beta, (1 / ak1));

        System.out.println("Beta: " + beta);


    }

    double[] toDouble(List<Double> lista) {
        double[] newList = new double[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            newList[i] = lista.get(i);
        }
        return newList;
    }
}
