import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day6 {

    public static void main(String[] args) {
        List<Integer> values;

        try (BufferedReader br = new BufferedReader(new FileReader("day6_input.txt"))) {
            values = Arrays.stream(br.readLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        solve(solve(values));

    }

    private static List<Integer> solve(List<Integer> values) {
        Set<List<Integer>> set = new HashSet<>();

        while (!set.contains(values)) {
            set.add(new ArrayList<>(values));

            int max = 0;
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i) > values.get(max)) {
                    max = i;
                }
            }

            int num = values.get(max);
            values.set(max, 0);
            while (num > 0) {
                values.set(++max % values.size(), values.get(max % values.size()) + 1);
                num--;
            }
        }

        System.out.println(set.size());
        return values;
    }

}
