
// 80/100 implementation
import java.io.*;
import java.lang.*;
import java.util.*;

class Cat implements Comparable<Cat> {
    int arrivalIndex;
    int infectionLevel;
    String name;

    public Cat(int arrivalIndex, int infectionLevel, String name) {
        this.arrivalIndex = arrivalIndex;
        this.infectionLevel = infectionLevel;
        this.name = name;
    }

    @Override
    public int compareTo(Cat o) {
        if (this.infectionLevel == o.infectionLevel) {
            return this.arrivalIndex - o.arrivalIndex;
        } else {
            return o.infectionLevel - this.infectionLevel;
        }
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.name, this.infectionLevel);
    }
}

public class doctorkattis {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int lines = reader.nextInt();
        PriorityQueue<Cat> catPQ = new PriorityQueue<>();
        HashMap<String, Cat> catMap = new HashMap<>();
        int index = 1;

        while (lines-- > 0) {
            int command = reader.nextInt();
            if (command == 0) { // arrive
                String name = reader.next();
                int infectionLevel = reader.nextInt();
                Cat katz = new Cat(index, infectionLevel, name);
                catPQ.add(katz);
                catMap.put(name, katz);
                index++;
            } else if (command == 1) { // update
                String name = reader.next();
                Cat cat = catMap.get(name);
                Cat newKatz = new Cat(cat.arrivalIndex, cat.infectionLevel + reader.nextInt(), cat.name);
                catPQ.add(newKatz);
                catMap.put(name, newKatz);
            } else if (command == 2) {// treated and left
                String name = reader.next();
                Cat cat = catMap.get(name);
                catMap.remove(name);
                catPQ.remove(cat);
            } else if (command == 3) {// query
                if (catPQ.isEmpty()) {
                    writer.println("The clinic is empty");
                } else {
                    Cat cat = catPQ.peek();
                    while (!catPQ.isEmpty() && !catMap.containsKey(cat.name)) {
                        catPQ.poll();
                        cat = catPQ.peek();
                    }
                    if (catPQ.isEmpty()) {
                        writer.println("The clinic is empty");
                    }
                }
            }
        }
        writer.close(); // do not forget!
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        // this method reads until the first space
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        // this method reads the entire line
        String nextLine() {
            String s = "";
            try {
                return s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }
    }
}
