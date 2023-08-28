package PS02;
import java.io.*;
import java.util.*;

public class jobbyte {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // taking inputs
        int persons = reader.nextInt();
        int[] positions = reader.readArray(persons);
        int swap = 0;
        for (int i = 0; i < persons; i++){
            // bubble sort variant
            // using selection sort will be O(n^2) which will hit TLE!
            while (positions[i] != i+1){
                int value = positions[i];
                int temp = positions[value-1];
                positions[i] = temp;
                positions[value-1] = value;
                swap++;
            }
        }
        writer.println(swap);
        writer.close();
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
}
