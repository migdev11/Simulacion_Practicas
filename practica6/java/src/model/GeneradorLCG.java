package model;

public class GeneradorLCG {
    private long semilla;
    private final long a;
    private final long c;
    private final long m;

    public GeneradorLCG() {
        this(12345L, 1664525L, 1013904223L, 1L << 32);
    }

    public GeneradorLCG(long semilla, long a, long c, long m) {
        this.semilla = semilla;
        this.a = a;
        this.c = c;
        this.m = m;
    }

    public double[] generar(int n) {
        if (n <= 0) {
            return new double[0];
        }

        double[] valores = new double[n];
        long x = semilla;

        for (int i = 0; i < n; i++) {
            x = (a * x + c) % m;
            valores[i] = (double) x / (double) m;
        }

        semilla = x;
        return valores;
    }
}
