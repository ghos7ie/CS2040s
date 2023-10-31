import java.io.*;
import java.lang.*;
import java.util.*;

public class proofs {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int lines = reader.nextInt();
        HashSet<String> concluded = new HashSet<>();
        boolean correct = true;
        for (int i = 1; i <= lines; i++) {
            String s = reader.nextLine();
            StringTokenizer st = new StringTokenizer(s, " ");
            boolean conclusion = false;
            while (st.hasMoreTokens()) {
                String a = st.nextToken();
                if (a.equals("->")) {
                    conclusion = true;
                } else {
                    if (conclusion) {
                        concluded.add(a);
                    } else {
                        if (!concluded.contains(a)) {
                            correct = false;
                        }
                    }
                }
            }
            if (!correct) {
                writer.println(i);
                break; // System.exit(0) doesn't work for some reason
            }
        }
        if (correct) {
            writer.println("correct");
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
