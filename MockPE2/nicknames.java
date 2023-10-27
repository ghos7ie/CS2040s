import java.io.*;
import java.lang.*;
import java.util.*;

public class nicknames {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);
        int n = reader.nextInt();
        HashMap<Character, List<List<Character>>> hm = new HashMap<>();
        // convert string to list of characters
        while (n-- > 0) {
            String insert = reader.next();
            Character c = insert.charAt(0);
            if (!hm.containsKey(c)) {
                List<List<Character>> y = new ArrayList<>();
                hm.put(c, y);
            }
        }
        n = reader.nextInt();
        while (n-- > 0) {

        }
        // your code goes here

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
