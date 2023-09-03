package PS02;

import java.io.*;
import java.lang.*;
import java.util.*;

public class universityzoning {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // read first line
        long rows = reader.nextLong();
        long cols = reader.nextLong();
        long noFac = reader.nextLong();
        long noStudents = reader.nextLong();
        long noCompliant = reader.nextLong();

        // create Array of Coordinates for each faculty

        // reading coordinates assigned to each faculty
        for (int i = 0; i < noFac; i++) {
            int noCells = reader.nextInt();

        }

        writer.close(); // do not forget!
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

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
    }

    class Coordinate implements Comparable<Coordinate> {
        public long row;
        public long col;

        Coordinate(long row, long col) {
            this.row = row;
            this.col = col;
        }

        long getRow() {
            return row;
        }

        long getCol() {
            return col;
        }

        @Override
        public int compareTo(Coordinate o) {
            if (this.row < o.row) {
                return 1;
            }
            
        }
    }
}
