import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        // массив w
        int[] w = new int[5];
        // массив x
        double[] x = new double[14];
        // массив f 5x14
        double[][] f = new double[5][14];

        // счет нечетных чисел для массива w
        int nech = 15;

        // заполнение массива w
        for (int i = 0; i < w.length; i++){
            w[i] = nech;
            nech -= 2;
        }

        // заполнение массива x
        for (int i = 0; i < x.length; i++){
            x[i] = (Math.random() * (14 + 10 + 1) - 10);
        }

        // проверка, выполнение выражений и заполнение массива f
        for (int i = 0; i < w.length; i++){
            for (int j = 0; j < 14; j++){
                if (w[i] == 7){
                    f[i][j] = ifSeven(x[j]);
                }
                else if (w[i] >= 11 && w[i] <= 13){
                    f[i][j] = ifElevenThirteen(x[j]);
                }
                else {
                    f[i][j] = ifOther(x[j]);
                }
            }
        }
        matrix(f);
    }
    // методы для вычисления элементов массива
    public static double ifSeven(double x){
        return Math.sin(Math.sin(x/2));
    }

    public static double ifElevenThirteen(double x){
        return Math.asin(Math.cos(Math.pow(Math.pow(x,3) / (Math.pow(x, 3) + 1),3)));
    }

    public static double ifOther(double x){
        return Math.cos(Math.asin(0.25 * (1 / Math.pow(Math.E, Math.abs(x)))));
    }

    // вывод матрицы
    public static void matrix(double[][] mas){
        for (int i = 0; i < mas.length; i++){
            for (int j = 0; j < mas[i].length; j++){
                System.out.printf("%.3f" + " ",mas[i][j]);
            }
            System.out.println();
        }
    }
}
