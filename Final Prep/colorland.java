import java.io.*;
import java.lang.*;
import java.util.*;

class MinimumDraw implements Comparable<MinimumDraw> {
    int colorIndex;
    int min;

    @Override
    public int compareTo(MinimumDraw o) {
        return this.min - o.min;
    }

}

public class colorland {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int length = reader.nextInt();
        String last = "";
        String[] colorSet = new String[length];
        HashMap<String, Integer> colorIndex = new HashMap<>(); // store index of color
        ArrayList<ArrayList<Integer>> 
        int index = 0;
        for (int i = 0; i < length; i++) {
            String color = reader.next();
            colorSet[i] = color;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();



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
