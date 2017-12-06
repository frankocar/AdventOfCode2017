import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day5 {

    public static void main(String[] args) {
        List<String> input;

        try {
            input = Files.readAllLines(Paths.get("day5_input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println(solve(input, false));
        System.out.println(solve(input, true));

    }

    private static int solve(List<String> input, boolean step2) {
        int[] values = input.stream().mapToInt(Integer::parseInt).toArray();

        int loc = 0;
        int cnt = 0;

        while (loc < input.size()) {
            int oldLoc = loc;
            loc += values[loc];
            if (step2 && values[oldLoc] >= 3) {
                values[oldLoc]--;
            } else {
                values[oldLoc]++;
            }

            cnt++;
        }

        return cnt;
    }

}
