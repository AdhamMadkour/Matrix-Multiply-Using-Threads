package Task;

public class ThreadCalc extends Thread {
    public ThreadCalc(int[][] M1, int[][] M2, int[][] Ans, int i, int j, int m) {
        // Constructor that will calculate single cell in the matrix
        for (int k = 0; k < m; ++k) {
            Ans[i][j] += M1[i][k] * M2[k][j];
        }
    }

    @Override
    public void run() {
    }
}
