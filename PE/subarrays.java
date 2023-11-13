
// 6% VA OQ 3 will be easier than this
import java.io.*;
import java.lang.*;
import java.util.*;

public class subarrays {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int length = reader.nextInt();
        int K = reader.nextInt(); // special number
        long B = reader.nextLong(); // number that the subarray must add up to using the formula

        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            arrayList.add(reader.nextInt());
        }
        boolean found = false;
        for (int i = 0; i < length; i++) {
            if (found) {
                break;
            }
            for (int j = i; j < length; j++) {
                if (!found && checkFormula(arrayList, i, j, K) == B) {
                    writer.println(i + " " + j);
                    found = true;
                }
            }
        }
        if (!found) {
            writer.println(-1);
        }
        writer.close(); // do not forget!
    }

    static long checkFormula(ArrayList<Integer> array, int start, int end, int k) {
        long sum = 0;
        for (int i = start; i < end + 1; i++) {
            sum += array.get(i);
        }
        long len = end - start + 1;
        len *= k;
        sum -= len;
        return sum;
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
