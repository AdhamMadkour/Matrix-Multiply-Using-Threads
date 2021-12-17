package Task;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static boolean check(int n, int m, int x, int y) {
        return m == x;
    }

    public static int[][] mult(int[][] M1, int[][] M2, int n, int m, int y) throws InterruptedException {
        int[][] Ans = new int[n][y];
        CountDownLatch startSignal = new CountDownLatch(1);// Start signal that prevents any thread from proceeding until the driver is ready for them to proceed
        CountDownLatch countDownLatch = new CountDownLatch(n);//wait until all threads have completed
        for (int i = 0; i < n; ++i) {       // for first matrix row
            new Thread(new ThreadCalc(startSignal, countDownLatch, M1, M2, Ans, i, y, m)).start();
        }
        startSignal.countDown();
        countDownLatch.await();
        return Ans;
    }

    public static int[][] inMatrix(int row, int col) {
        int[][] Matrix = new int[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                Matrix[i][j] = scanner.nextInt();
            }
        }
        return Matrix;
    }

    public static void main(String[] args) throws InterruptedException {
        int m1Row, m1Col, m2Row, m2Col;
        m1Row = scanner.nextInt();
        m1Col = scanner.nextInt();
        int[][] M1;
        M1 = inMatrix(m1Row, m1Col);
        m2Row = scanner.nextInt();
        m2Col = scanner.nextInt();
        int[][] M2;
        M2 = inMatrix(m2Row, m2Col);
        if (check(m1Row, m1Col, m2Row, m2Col)) {
            int[][] Ans;
            Ans = mult(M1, M2, m1Row, m1Col, m2Col);
            for (int i = 0; i < m1Row; ++i) {
                for (int j = 0; j < m2Col; ++j) {
                    System.out.print(Ans[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("\nError !! Can't Multiply " + m1Row + " X " + m1Col + " Matrix by " + m2Row + " X " + m2Col + " Matrix");
        }
    }
}