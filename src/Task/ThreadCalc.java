package Task;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class ThreadCalc implements Runnable {
    private static int[][] M1;
    private static int[][] M2;
    private static int[][] Ans;
    private static int y, m;
    private final int i; //Cannot be passed by reference
    private final CountDownLatch latch;
    private final CountDownLatch startSignal;

    public ThreadCalc(CountDownLatch startSignal, CountDownLatch latch, int[][] M1, int[][] M2, int[][] Ans, int i, int y, int m) {
        // Constructor that will calculate single row in the matrix
        ThreadCalc.M1 = M1;
        ThreadCalc.M2 = M2;
        ThreadCalc.Ans = Ans;
        ThreadCalc.y = y;
        ThreadCalc.m = m;
        this.i = i;
        this.startSignal = startSignal;
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            startSignal.await();
            for (int j = 0; j < y; ++j) {
                for (int k = 0; k < m; ++k) {
                    Ans[i][j] += M1[i][k] * M2[k][j];
                }
            }
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
