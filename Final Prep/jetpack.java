import java.io.*;
import java.lang.*;
import java.util.*;

public class jetpack {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int length = reader.nextInt();
        int noMoves = 0;
        char[][] map = new char[10][];
        boolean[][] bools = new boolean[10][length];

        for (int i = 0; i < 10; i++) {
            map[i] = reader.next().trim().toCharArray();
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < 10; j++) {
                if (map[j][i] == 'X') {
                    continue;
                }
                if (j == 0 | j == 9) {// top row/bottom row
                    bools[j][i] |= bools[j][i - 1];
                }
            }
        }

        writer.println(noMoves);

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
