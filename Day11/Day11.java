import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day11 {

    public static void main(String[] args) {
        String[] in;

        try (BufferedReader br = new BufferedReader(new FileReader("day11_input.txt"))) {
            in = br.readLine().split(",");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int x = 0;
        int y = 0;
        int max = 0;
        int dist = 0;
        for (String d : in) {
            switch (d) {
                case "n":
                    y += 2;
                    break;
                case "ne":
                    y++;
                    x++;
                    break;
                case "se":
                    y--;
                    x++;
                    break;
                case "s":
                    y -= 2;
                    break;
                case "sw":
                    y--;
                    x--;
                    break;
                case "nw":
                    y++;
                    x--;
                    break;
            }
            dist = (Math.abs(x) + Math.abs(y)) / 2;
            max = Math.max(max, dist);
        }

        System.out.println("Step 1: " + dist);
        System.out.println("Step 2: " + max);
    }

}
