import java.io.*;
import java.lang.*;
import java.util.*;

class Node {
    Node front;
    Node back;
}

public class teque {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        Deque<Integer> tripe = new LinkedList<>();
        Queue<Integer> front = new LinkedList<>();
        long n = reader.nextInt();
        int middle = 0;
        while (n-- > 0) {
            String cmd = reader.next();
            switch (cmd) {
                case "push_back":
                    tripe.addLast(reader.nextInt());
                    break;
                case "push_front":
                    tripe.addFirst(reader.nextInt());
                    break;
                case "push_middle":
                    tripe.add(middle, reader.nextInt());
                    break;
                default:
                    // print element at number
                    break;
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
