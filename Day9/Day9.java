import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day9 {

    public static void main(String[] args) {
        char[] in;

        try (BufferedReader br = new BufferedReader(new FileReader("day9_input.txt"))) {
            in = br.readLine().toCharArray();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        int level = 0;
        int score = 0;
        int cnt = 0;
        boolean garbage = false;
        for (int i = 0; i < in.length; i++) {
            if (in[i] == '!') {
                i++;
                continue;
            }

            if (garbage) {
                if (in[i] == '>') {
                    garbage = false;
                } else {
                    cnt++;
                }
                continue;
            }

            switch (in[i]) {
                case '{':
                    level++;
                    break;
                case '}':
                    score += level;
                    level--;
                    break;
                case '<':
                    garbage = true;
                    break;
            }


        }

        System.out.println("Step 1: " + score);
        System.out.println("Step 2: " + cnt);

    }

}
