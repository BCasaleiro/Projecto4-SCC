package projecto;

public class Tuple {
    private double[] values;
   
    public Tuple(double[] values) {
        this.values = values;
        //System.out.println("Creating tuple: " + Arrays.toString(values));
    }
   
    public double getValue(int index) {
        return values[index];
    }
 
    public boolean equals(Tuple targetTuple) {
        if (values.length != targetTuple.getSize()) {
            return false;
        }
 
        for (int i = 0; i < values.length; i++) {
            if (values[i] != targetTuple.getValue(i)) {
                return false;
            }
        }
        return  true;
    }
 
    public int getSize() {
        return this.values.length;
    }
}
