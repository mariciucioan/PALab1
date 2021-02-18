package com.mariciucioan.PALab1optional;

// max working n = 22000
public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int n=0;
        try {
            n = Integer.parseInt(args[0]);
            if(n%2!=0)
                throw new NumberFormatException("The argument must be odd.");
        } catch (NumberFormatException e) {
            System.out.println("The argument must be an odd integer.");
            System.exit(0);
        }

        RandomMatrix matrix = new RandomMatrix(n, n<=50);

        matrix.showComponents();
        matrix.makePartialTree(n<=50);

        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time: " + timeElapsed +
                " nanoseconds (" + (float) timeElapsed/1000000000 + " seconds)");
    }
}
