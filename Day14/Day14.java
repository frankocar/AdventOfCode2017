import java.math.BigInteger;
import java.util.Map;
import java.util.TreeMap;

public class Day14 {

    private static Map<Integer, char[]> rowLine = new TreeMap<>();
    private static final String INPUT = "vbqugkhl";

    public static void main(String[] args) {
        System.out.println("Step 1: " + step1());
        System.out.println("Step 2: " + step2());
    }

    private static int step1() {
        int sum = 0;
        for (int i = 0; i < 128; i++) {
            String hash = hexToBin(Day10.step2(INPUT + "-" + i));
            hash = String.format("%128s", hash).replace(' ', '0');
            rowLine.put(i, hash.toCharArray());
            for (char x : hash.toCharArray()) {
                if (x == '1') {
                    sum++;
                }
            }
        }
        return sum;
    }

    private static boolean[][] seen = new boolean[128][128];

    static private void dfs(int i, int j) {
        if (seen[i][j]) return;
        if (rowLine.get(i)[j] != '1') return;
        seen[i][j] = true;
        if (i > 0) dfs(i-1, j);
        if (j > 0) dfs(i, j-1);
        if (i < 127) dfs(i+1, j);
        if (j < 127) dfs(i, j+1);

    }

    static private int step2() {
        int cnt = 0;
        for (int i = 0; i < 128; i++) {
            char[] row = rowLine.get(i);
            for (int j = 0; j < 128; j++) {
                if (seen[i][j]) {
                    continue;
                }
                if (row[j] != '1') {
                    continue;
                }
                cnt++;
                dfs(i, j);
            }
        }
        return cnt;
    }

    static private String hexToBin(String s) {
        return new BigInteger(s, 16).toString(2);
    }

}
