import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day4 {

    public static void main(String[] args) {
        List<String> input;

        try {
            input = Files.readAllLines(Paths.get("day4_input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(solve(input, false));
        System.out.println(solve(input, true));

    }

    private static int solve(List<String> input, boolean step2) {
        int count = 0;

        for (String line : input) {
            Set<String> words = new HashSet<>();
            String[] pass = line.split("\\s+");

            boolean valid = true;
            for (String x : pass) {
                if (step2) {
                    char[] array = x.toCharArray();
                    Arrays.sort(array);
                    x = new String(array);
                }

                if (!words.add(x)) {
                    valid = false;
                    break;
                }
            }

            if(valid) count++;

        }

        return count;
    }

}
