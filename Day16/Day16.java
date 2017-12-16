import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day16 {

    public static void main(String[] args) {
        String[] in;

        try (BufferedReader br = new BufferedReader(new FileReader("day16_input.txt"))) {
            in = br.readLine().split(",");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p");

        System.out.println("Step 1: " + String.join("", move(in, new ArrayList<>(list))));
        System.out.println("Step 2: " + step2(in, new ArrayList<>(list)));
    }

    private static String step2(String[] in, List<String> list) {
        Set<List<String>> seen = new LinkedHashSet<>();
        for (int i = 0; i < 1_000_000_000; i++) {
            if (!seen.add(new ArrayList<>(list))) {
                break;
            }
            list = move(in, list);
        }

        return String.join("", seen.stream().skip(1_000_000_000 % seen.size()).findFirst().get());
    }

    private static List<String> move(String[] in, List<String> list) {
        for (String s : in) {
            if (s.startsWith("s")) {
                Collections.rotate(list, Integer.parseInt(s.substring(1)));
            } else {
                int i1, i2;
                String[] split = s.substring(1).split("/");
                if (s.startsWith("x")) {
                    i1 = Integer.parseInt(split[0]);
                    i2 = Integer.parseInt(split[1]);
                } else {
                    i1 = list.indexOf(split[0]);
                    i2 = list.indexOf(split[1]);
                }
                Collections.swap(list, i1, i2);
            }
        }
        return list;
    }
}
