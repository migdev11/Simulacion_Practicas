package model;

public class LCGModel {
    public static final long A = 1664525L;
    public static final long C = 1013904223L;
    public static final long M = 1L << 32;

    private long seed;

    public LCGModel() {
        this(12345L);
    }

    public LCGModel(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long nextValue() {
        seed = (A * seed + C) % M;
        return seed;
    }

    public long[] generate(int n) {
        long[] values = new long[n];
        for (int i = 0; i < n; i++) {
            values[i] = nextValue();
        }
        return values;
    }

    public double normalize(long value) {
        return (double) value / M;
    }

    public void reset(long seed) {
        this.seed = seed;
    }
}
