import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day10 {

    public static void main(String[] args) {
        String in;

        try (BufferedReader br = new BufferedReader(new FileReader("day10_input.txt"))) {
            in = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int[] step1 = step1(Arrays.stream(in.split(",")).map(Integer::parseInt).collect(Collectors.toList()), 1);
        String step2 = step2(in);

        System.out.println("Step 1: " + step1[0] * step1[1]);
        System.out.println("Step 2: " + step2);
    }

    public static String step2(String input) {
        List<Integer> in = new ArrayList<>();
        for (char x : input.toCharArray()) {
            in.add((int) x);
        }
        in.addAll(Arrays.asList(17,31,73,47,23));

        int[] num = step1(in, 64);
        List<Integer> dense = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            int tmp = 0;
            for (int j = i * 16; j < i * 16 + 16; j++) {
                tmp ^= num[j];
            }
            dense.add(tmp);
        }

        StringBuilder sb = new StringBuilder();
        for (int d : dense) {
            sb.append(String.format("%02x", d));
        }

        return sb.toString();
    }

    private static int[] step1(List<Integer> in, int times) {
        int[] num = IntStream.range(0, 256).toArray();
        int curr = 0;
        int skip = 0;

        for (int t = 0; t < times; t++) {
            for (Integer len : in) {
                int[] cpy = Arrays.copyOf(num, num.length);
                for (int i = curr, j = curr + len - 1; i < curr + len; i++, j--) {
                    num[i % num.length] = cpy[j % cpy.length];
                }
                curr = (curr + len + skip++);
            }
        }

        return num;
    }

}
