//Ugly, brute force solution
public class Day3 {

    private static final int INPUT = 265149;

    public static void main(String[] args) {
        step1();
        step2();
    }

    //really ugly, but not worth changing it since I'm not aware of a better algorithm,
    //and code quality wouldn't improve significantly without a major rewrite
    private static void step2() {
        int[][] values = new int[127][127]; //allocates an unnecessarily huge array, should be changed to a dynamic collection
        int x = 0;
        int y = 0;
        values[63][63] = 1; //starts from the middle cell in an array

        int i = 0;
        int tmp;
        while (true) {
            switch (i % 4) {
                case 0:
                    tmp = -x + 1;
                    for (int j = x + 1; j <= tmp; j++) {
                        values[y + 63][j + 63] = calcValue(j, y, values);
                        if (check(values[y + 63][j + 63])) return;
                    }
                    x = tmp;
                    break;
                case 1:
                    tmp = -y + 1;
                    for (int j = y + 1; j <= tmp; j++) {
                        values[j + 63][x + 63] = calcValue(x, j, values);
                        if (check(values[j + 63][x + 63])) return;
                    }
                    y = tmp;
                    break;
                case 2:
                    tmp = -x;
                    for (int j = x - 1; j >= tmp; j--) {
                        values[y + 63][j + 63] = calcValue(j, y, values);
                        if (check(values[y + 63][j + 63])) return;
                    }
                    x = tmp;
                    break;
                case 3:
                    tmp = -y;
                    for (int j = y - 1; j >= tmp; j--) {
                        values[j + 63][x + 63] = calcValue(x, j, values);
                        if (check(values[j + 63][x + 63])) return;
                    }
                    y = tmp;
                    break;
            }
            i++;
        }
    }

    private static boolean check(int l) {
        if (l > INPUT) {
            System.out.println("Step 2: " + l);
            return true;
        }
        return false;
    }

    private static int calcValue(int x, int y, int[][] values) {
        return values[y + 1 + 63][x - 1 + 63] + values[y + 63][x - 1 + 63] +
                values[y - 1 + 63][x - 1 + 63] + values[y + 1 + 63][x + 63] +
                values[y - 1 + 63][x + 63] + values[y + 1 + 63][x + 1 + 63] +
                values[y + 63][x + 1 + 63] + values[y - 1 + 63][x + 1 + 63];
    }

    private static void step1() {
        int x = 1;
        int y = 1;
        int number = 3;

        int i = 2;
        int tmp;
        while (number < INPUT) {
            switch (i % 4) {
                case 0:
                    tmp = -x + 1;
                    number += Math.abs(tmp - x);
                    x = tmp;
                    if (number > INPUT) {
                        x -= (number - INPUT);
                    }
                    break;
                case 1:
                    tmp = -y + 1;
                    number += Math.abs(tmp - y);
                    y = tmp;
                    if (number > INPUT) {
                        y -= (number - INPUT);
                    }
                    break;
                case 2:
                    tmp = -x;
                    number += Math.abs(tmp - x);
                    x = tmp;
                    if (number > INPUT) {
                        x += (number - INPUT);
                    }
                    break;
                case 3:
                    tmp = -y;
                    number += Math.abs(tmp - y);
                    y = tmp;
                    if (number > INPUT) {
                        y += (number - INPUT);
                    }
                    break;
            }
            i++;
        }

        System.out.println("Step 1: " + (Math.abs(x) + Math.abs(y)));
    }

}
