import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day8 {

    private static Map<String, Integer> registers = new HashMap<>();

    public static void main(String[] args) {
        List<String[]> input;

        try {
            input = Files.readAllLines(Paths.get("day8_input.txt")).stream().map(x -> x.split("\\s+")).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        int maxVal = Integer.MIN_VALUE;
        for (String[] x : input) {
            int regValue = registers.getOrDefault(x[0], 0);
            if (check(x)) {
                if (x[1].equals("inc")) {
                    regValue += Integer.parseInt(x[2]);
                } else if (x[1].equals("dec")) {
                    regValue -= Integer.parseInt(x[2]);
                }
                registers.put(x[0], regValue);
                if (regValue > maxVal) {
                    maxVal = regValue;
                }
            }
        }

        String max = input.get(0)[0];
        for (String x : registers.keySet()) {
            if (registers.get(x) > registers.get(max)) {
                max = x;
            }
        }

        System.out.println("Step 1: " + registers.get(max));
        System.out.println("Step 2: " + maxVal);

    }

    private static boolean check(String[] x) {
        int regValue = registers.getOrDefault(x[4], 0);
        String operator = x[5];
        int num = Integer.parseInt(x[6]);

        switch (operator) {
            case "<":
                return regValue < num;
            case ">":
                return regValue > num;
            case ">=":
                return regValue >= num;
            case "<=":
                return regValue <= num;
            case "==":
                return regValue == num;
            case "!=":
                return regValue != num;
        }

        return false;
    }

}
