package projecto;

public class Matrix {

    private int[][] d_2;
    private int[][][] d_3;
    private int d;
    private int k;

    public Matrix(int dimensions, int k) {
        this.d = dimensions;
        this.k = k;
        this.setup();
    }

    public void fill(Tuple[] tuples) {
        for (int i = 0; i < tuples.length; i++) {
            this.increment(tuples[i]);
        }
    }

    private void increment(Tuple tuple) {
        if (tuple.getSize() == 2) {
            //System.out.println((int)(tuple.getValue(0)*(double)k) + " , " + (int)(tuple.getValue(1)*k));
            d_2[(int) (tuple.getValue(0) * (double) k)][(int) (tuple.getValue(1) * (double) k)]++;
        } else {
            d_3[(int) (tuple.getValue(0) * (double) k)][(int) (tuple.getValue(1) * (double) k)][(int) (tuple.getValue(2) * (double) k)]++;
        }
    }

    public int getCounter(int... coords) {
        if (d == 2) {
            return d_2[coords[0]][coords[1]];
        } else {
            return d_3[coords[0]][coords[1]][coords[2]];
        }
    }

    public void setup() {
        if (d == 2) {
            d_2 = new int[k][k];
        } else {
            d_3 = new int[k][k][k];
        }
    }
}
