import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

//quick and dirty unrefined solution
public class Day7 {

    static private Map<String, Program> progs = new HashMap<>();

    public static void main(String[] args) {
        step2(step1(load("day7_input.txt")));
    }

    private static void step2(Program bottom) {
        Program node = bottom;
        int nodeAbove = 0;
        int others = 0;
        while (true) {
            Map<Integer, List<String>> tmp = new HashMap<>();
            for (String x : node.above) {
                int w = getWeightAbove(progs.get(x));
                tmp.computeIfAbsent(w, k -> new ArrayList<>()).add(x);
            }

            boolean found = false;
            for (int x : tmp.keySet()) {
                if (tmp.get(x).size() == 1) {
                    node = progs.get(tmp.get(x).get(0));
                    nodeAbove = x;
                    found = true;
                    break;
                }
            }
            if (!found) break;

            Set<Integer> keySet = new HashSet<>(tmp.keySet());
            keySet.remove(nodeAbove);
            others = keySet.iterator().next();
        }

        System.out.println("Step 2: " + (others - nodeAbove + node.weight));
    }

    private static Program step1(Set<String> supporting) {
        Program bottom = null;
        for (String x : progs.keySet()) {
            if (!supporting.contains(x)) {
                System.out.println("Step 1: " + x);
                bottom = progs.get(x);
                break;
            }
        }
        return bottom;
    }

    private static Set<String> load(String path) {
        List<String> input = null;
        try {
            input = Files.readAllLines(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Instructions are nowhere to be found");
            System.exit(1);
        }

        Set<String> supporting = new HashSet<>();
        for (String line : input) {
            String[] split1 = line.split(" -> ");
            String name = split1[0].split("\\s+")[0];
            int weight = Integer.parseInt(split1[0].split("\\s+")[1].substring(1).replace(")", ""));
            List<String> above;
            if (split1.length == 1) {
                above = new ArrayList<>();
            } else {
                above = Arrays.asList(split1[1].split(", "));
                supporting.addAll(above);
            }

            progs.put(name, new Program(name, weight, above));
        }

        return supporting;
    }

    //unused, prints a tree just as a graphical representation
    private static void printTree(Program start, int indent) {
        StringBuilder sb = new StringBuilder(indent);
        for (int i = 0; i < indent; i++){
            sb.append(" ");
        }
        System.out.println(sb.toString() + start.name + " (" + getWeightAbove(start) + ")");
        for (String x : start.above) {
            printTree(progs.get(x), indent + 2);
        }
    }

    private static int getWeightAbove(Program program) {
        int w = 0;
        if (program.above.isEmpty()) {
            return program.weight;
        }

        for (String s : program.above) {
            w += getWeightAbove(progs.get(s));
        }

        return w + program.weight;

    }

    private static class Program {
        
        String name;
        int weight;
        List<String> above;

        public Program(String name, int weight, List<String> above) {
            this.name = name;
            this.weight = weight;
            this.above = above;

            if (above == null) {
                this.above = new ArrayList<>();
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Program program = (Program) o;
            return name == program.name &&
                    weight == program.weight &&
                    Objects.equals(above, program.above);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    } 
    
}
