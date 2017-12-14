import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Day13 {

    public static void main(String[] args) {
        Map<Integer, Integer> map = new TreeMap<>();

        try {
            List<String> input = Files.readAllLines(Paths.get("day13_input.txt"));

            for (String l : input) {
                String[] split = l.split(": ");
                map.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        System.out.println("Step 1: " + step1(map));
        System.out.println("Step 2: " + step2(map));

    }

    private static int scan(int depth, int delay) {
        int offset = delay % ((depth - 1) * 2);

        if (offset > depth - 1) {
            return 2 * (depth - 1) - offset;
        } else {
            return offset;
        }
    }

    private static int step2(Map<Integer, Integer> map) {
        int delay = 0;
        while(true) {
            boolean any = false;
            for (Integer l : map.keySet()) {
                if (scan(map.get(l), delay + l) == 0) {
                    any = true;
                }
            }
            if (!any) return delay;
            delay++;
        }

    }

    private static int step1(Map<Integer, Integer> map) {
        int sum = 0;

        for (Integer l : map.keySet()) {
            if (scan(map.get(l), l) == 0) {
                sum += map.get(l) * l;
            }
        }

        return sum;
    }
}
