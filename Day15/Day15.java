import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day15 {

    private static int[] factor = {16807, 48271};
    private static long mod = 2147483647L;

    public static void main(String[] args) {
        long[] start = new long[2];

        try {
            List<String> input = Files.readAllLines(Paths.get("day15_input.txt"));
            for (int i = 0; i < 2; i++) {
                start[i] = Integer.parseInt(input.get(i).substring("Generator A starts with ".length()));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Step 1: " + solve(Arrays.copyOf(start, 2), false));
        System.out.println("Step 2: " + solve(Arrays.copyOf(start, 2), true));

    }

    private static int solve(long[] values, boolean step2) {
        int count = 0;
        for (int i = 0, n = step2 ? 5_000_000 : 40_000_000; i < n; i++) {
            do {
                values[0] = (values[0] * factor[0]) % mod;
            } while (step2 && (values[0] % 4 != 0));
            do {
                values[1] = (values[1] * factor[1]) % mod;
            } while (step2 && (values[1] % 8 != 0));

            if ((values[0] & 65535) == (values[1] & 65535)) {
                count++;
            }
        }

        return count;
    }

}
