public class Main {
    static final int n = 17;
    static int[] w;
    static double[] x;
    static double[][] w1; 

    static void print(double[][] a) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.printf("%7.5f", a[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {   
        exercizeFirst();
        exercizeSecond();
        exercizeThird();
        print(w1);
    }

    static void exercizeFirst() {
        w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = 17 - i;
        }
    } 

    static void exercizeSecond() {
        x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = (Math.random() * n) - 13;
        }
    }

    static void exercizeThird() {
        w1 = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w1[i][j] = count(w1[i][j], i, j);
            }
        }
    }

    static double count(double e_w1, int i, int j) {
        e_w1 = switch (w[i]) {
            case 3 -> firstFunction(x[j]);
            case 1, 9, 10, 12, 13, 14, 15, 16 -> secondFunction(x[j]);
            default -> thirdFunction(x[j]);
        };
        return e_w1;
    }

    static double firstFunction(double x) {
        double upperPower = Math.tan(Math.pow(x, 2*x));
        double lowerPower = Math.pow((3*(Math.pow(Math.tan(x),2)+1)), upperPower);
        double denominator = Math.exp(lowerPower);
        double answer = Math.asin(1.0/denominator);
        return answer;
    }

    static double secondFunction(double x) {
        double arg = (x - 4.5) / 17.0;
        double upperPower = Math.pow(Math.atan(arg), 1.0/3.0) / 2.0;
        double lowerPower = ((2.0/3.0) + Math.pow(x, 1.0/3.0)) / 0.5;
        double inside = Math.pow(Math.asin(arg), lowerPower);
        double answer = Math.pow(inside, upperPower);
        return answer;
    } 

    static double thirdFunction(double x) {
        double lowerPower = x / 8.0;
        double arg = Math.pow(Math.pow(Math.asin((x - 4.5) / 17.0), 3.0) / 4.0, lowerPower);
        double denominator = Math.asin(0.4*Math.exp(-Math.abs(x)));
        double nominatorinatorDenominatorinator = Math.pow(x/(3.0 - x), 2);
        double nominator = 1 - ((0.5 - Math.sin(Math.exp(x)))/4.0)/nominatorinatorDenominatorinator;
        double argumentPower = nominator / denominator;
        double answer = Math.pow((arg), argumentPower);
        return answer;
    } 
}