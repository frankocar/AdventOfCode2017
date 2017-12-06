import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day1 {

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>();

        try (Scanner sc = new Scanner(Files.newInputStream(Paths.get("day1_input.txt")))){
            sc.useDelimiter("");
            while (sc.hasNext()) {
                input.add(sc.nextInt());
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Step 1 captcha is: " + solve(input, false));
        System.out.println("Step 2 captcha is: " + solve(input, true));

    }

    private static long solve(List<Integer> input, boolean step2) {
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            int next = (i + (step2 ? input.size() / 2 : 1)) % input.size();

            if (input.get(i) == input.get(next)) {
                sum += input.get(i);
            }
        }

        return sum;
    }

}
