package app.data;

public class PasswordHash {
    
    public long fw;
    public long rv;

    private static final long K = 768167;
    private static final long M = 1895747993;

    public PasswordHash(String password) {
        this.fw = PasswordHash.hash(password);
        this.rv = PasswordHash.hash(new StringBuilder(password).reverse().toString());
    }

    public PasswordHash(long fw, long rv) {
        this.fw = fw;
        this.rv = rv;
    }

    public static long hash(String s) {
        long r = 0;
        long kp = 1;
        for (char c : s.toCharArray()) {
            int v = c;
            r = (r + v * kp) % M;
            kp = (kp * K) % M;
        }
        return r;
    }

    public boolean check(String password) {
        long cfw = PasswordHash.hash(password);
        long crv = PasswordHash.hash(new StringBuilder(password).reverse().toString());

        return (cfw == fw) && (crv == rv);
    }
}
