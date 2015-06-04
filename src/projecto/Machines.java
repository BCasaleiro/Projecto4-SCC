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
    
    List <Double> machine1 = new ArrayList <Double>();
    List <Double> machine2 = new ArrayList <Double>();
    List <Double> machines = new ArrayList <Double>();
    int sum1=0;
    int sum2=0;
    double alpha;
    int groups;
    int df;
    double value;
    
    Machines(double alpha, int groups) throws IOException{
        this.alpha=alpha;
        this.groups=groups;
        this.df = groups-1;
        this.value = 3.84146;
        
        
        File file1 = new File("Machine1.txt");
        File file2 = new File("Machine2.txt");
        readFiles(file1, file2);
        sortMachines();
        getSums();
        testStatistic();
        //System.out.println(sum1 + " " + sum2);
        //printMachines();
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
        /*
        for(int i = 0;i<machine1.size();i++)
        {
            System.out.println(i + " " + machine1.get(i));
        }
        
        System.out.println("------------------------------------");
        
         for(int i = 0;i<machine2.size();i++)
        {
            System.out.println(i + " " + machine2.get(i));
        }*/
        for(int i = 0;i<machines.size();i++)
        {
            System.out.println(i + " " + machines.get(i));
        }
    }
}
