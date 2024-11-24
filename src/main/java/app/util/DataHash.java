package app.util;

public class DataHash {
    
    public long hash;

    private static final long K = 768167;
    private static final long M = 1895747993;

    public DataHash(String string) {
        this.hash = DataHash.hash(string);
    }

    public DataHash(long hash) {
        this.hash = hash;
    }

    public static long hash(String string) {
        long fw = 0;
        long kp = 1;
        for (char c : string.toCharArray()) {
            int v = c;
            fw = (fw + v * kp) % M;
            kp = (kp * K) % M;
        }

        long rv = 0;
        kp = 1;
        for (char c : new StringBuilder(string).reverse().toString().toCharArray()) {
            int v = c;
            rv = (rv + v * kp) % M;
            kp = (kp * K) % M;
        }

        return fw + rv * M;
    }

    public boolean check(String string) {
        long c = DataHash.hash(string);

        return c == hash;
    }

    @Override
    public String toString() {
        return Long.toString(hash);
    }
}
