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
            System.out.println("Instructions are nowhere to be found");
            return;
        }
        
        System.out.println("Step 1 captcha is: " + step1(input));
        System.out.println("Step 2 captcha is: " + step2(input));

    }

    private static long step1(List<Integer> input) {
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            if (input.get(i) == input.get(i == input.size() - 1 ? 0 : i + 1)) {
                sum += input.get(i);
            }
        }

        return sum;
    }

    private static long step2(List<Integer> input) {
        long sum = 0;
        for (int i = 0; i < input.size(); i++) {
            int next = (i + input.size() / 2) % input.size();

            if (input.get(i) == input.get(next)) {
                sum += input.get(i);
            }
        }

        return sum;
    }

}
