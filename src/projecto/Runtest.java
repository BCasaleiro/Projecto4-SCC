package projecto;

public class Runtest {

    public Runtest () {
        
    }
    
    int[] n = new int[6];
    double[][] a = new double[][]{  {4529.4, 9044.9, 13568, 18091, 22615, 27892},
                                    {9044.9, 18097, 27139, 36187, 45234, 55789},
                                    {13568, 27139, 40721, 54281, 67852, 83685},
                                    {18091, 36187, 54281, 72414, 90470, 111580},
                                    {22615, 45234, 67852, 90470, 113262, 139476},
                                    {27892, 55789, 83685, 111580, 139476, 172860}};
    double[] b = new double[]{((double) 1 / 6), ((double) 5 / 24), ((double) 11 / 120), ((double) 19 / 720), ((double) 29 / 5040), ((double) 1 / 840)};

    public boolean runTest(double num[]) {
        getRunner(num);
        return formula(num);
    }

    public void getRunner(double num[]) {
        int aux2 = 0;
        int aux = 1;
        int currPos = 0;
        for (int i = 0; i < num.length; i++) {
            if (i < num.length - 2) {
                while (num[i] < num[i + 1]) {
                    aux++;

                    if (i + 1 == num.length - 1) {
                        updateRun(aux);
                        aux2 = 1;
                        return;
                    } else {
                        currPos = i + 1;
                        i++;
                        i = currPos;
                    }

                }

                updateRun(aux);
                aux = 1;
            } else {
                updateRun(aux);
            }
        }

    }

    public void updateRun(int aux) {
        switch (aux) {
            case 1:
                n[0]++;
                break;
            case 2:
                n[1]++;
                break;
            case 3:
                n[2]++;
                break;
            case 4:
                n[3]++;
                break;
            case 5:
                n[4]++;
                break;
            default:
                n[5]++;
                break;
        }
    }

    public boolean formula(double num[]) {
        for (int i = 0; i < n.length; i++) {
            //System.out.println("Número de elementos com " + (i + 1) + ": " + n[i]);
        }
        double R = 0;

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                R = R + (a[i][j] * (this.n[i] - num.length * b[i]) * (this.n[j] - num.length * b[j]));
            }
        }
        R = R * ((double) 1 / (double) num.length);
        System.out.println("Resultado final: " + R);
        
        //Comparar com o teste do Qui-Quadrado para k=6 e alpha=0.05 -> 12.6
        if (R < 12.6) {
            System.out.println("Não se rejeita a hipótese de independência ao nível 0.05");
            return true;
        } else {
            System.out.println("Rejeita-se a hipótese de independência ao nível 0.05");
            return false;
        }
    }
}
