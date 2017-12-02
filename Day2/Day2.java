import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day2 {

    public static void main(String[] args) {
        List<String> input;

        try {
            input = Files.readAllLines(Paths.get("day2_input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Instructions are nowhere to be found");
            return;
        }

        System.out.println("Step 1: " + solve(input, false));
        System.out.println("Step 2: " + solve(input, true));

    }

    private static int solve(List<String> input, boolean step2) {
        int checksum = 0;
        for (String line : input) {
            int[] values = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).sorted().toArray();

            int max = values[values.length - 1];
            int min = values[0];

            if (!step2) {
                checksum += max - min;
                continue;
            }

            for (int i = values.length - 1; i >= 0; i--) {
                for (int j = i - 1; j >= 0; j--) {
                    if (values[i] % values[j] == 0) {
                        checksum += values[i] / values[j];
                    }
                }
            }


        }

        return checksum;
    }

}
