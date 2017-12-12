import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Day12 {

    private static Map<Integer, Set<Integer>> map = new HashMap<>();

    private static Set<Integer> seen = new HashSet<>();
    private static Set<Set<Integer>> groups = new HashSet<>();

    public static void main(String[] args) {
        List<String> input;

        try {
            input = Files.readAllLines(Paths.get("day12_input.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        for (String line : input) {
            String[] split = line.split(" <-> ");
            int x = Integer.parseInt(split[0]);
            List<Integer> y = Arrays.stream(split[1].split(", ")).map(Integer::parseInt).collect(Collectors.toList());

            Set<Integer> set = map.computeIfAbsent(x, k -> new HashSet<>());
            set.addAll(y);
            map.put(x, set);
        }

        Set<Integer> tmp = findGroup(0);
        System.out.println("Step 1: " + tmp.size());


        for (int x : map.keySet()) {
            groups.add(findGroup(x));
        }
        System.out.println("Step 2: " + groups.size());

    }

    private static Set<Integer> findGroup(int start) {
        if (seen.contains(start)) {
            return seen;
        }
        seen.add(start);
        for (int x : map.get(start)) {
            findGroup(x);
        }
        return seen;
    }

}
