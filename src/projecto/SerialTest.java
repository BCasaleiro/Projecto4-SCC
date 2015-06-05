package projecto;

public class SerialTest {

    private int dimensions;
    private Tuple[] tuples;

    public SerialTest() {
        this.dimensions = 2;
    }

    public void run(double[] array) {
        tuples = createTuples(array);
        double k = 30;
        double kd = Math.pow(k, dimensions);
        double n = tuples.length;
        double n_kd = n / kd;
        double sum = 0;
        Matrix matrix = new Matrix(dimensions, (int) k);
        matrix.fill(tuples);

        if (dimensions == 2) {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    sum += Math.pow((double) matrix.getCounter(i, j) - n_kd, 2);
                }
            }
        } else {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    for (int g = 0; g < k; g++) {
                        sum += Math.pow((double) matrix.getCounter(i, j, g) - n_kd, 2);
                    }
                }
            }
        }
        sum *= kd / n;
        System.out.println("The serial test for " + dimensions + " dimensions was: " + sum);
    }

    private Tuple[] createTuples(double array[]) {
        int nTuples = array.length / dimensions;
        Tuple[] tuples = new Tuple[nTuples];
        for (int i = 0; i < nTuples; i++) {
            double[] temp = new double[dimensions];
            for (int j = 0; j < dimensions; j++) {
                temp[j] = array[i * dimensions + j];
            }
            tuples[i] = new Tuple(temp);
        }
        //System.out.println(tuples.length + " tuples generated");
        return tuples;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

}
