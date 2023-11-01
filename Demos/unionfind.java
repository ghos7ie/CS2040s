
import java.io.*;
import java.lang.*;
import java.util.*;

public class unionfind {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int size = reader.nextInt();
        UF uf = new UF(size);

        int operations = reader.nextInt();
        while (operations-- > 0) {
            String op = reader.next();
            int x = reader.nextInt();
            int y = reader.nextInt();
            if (op.equals("?")) {
                boolean same = uf.isSameSet(x, y);
                if (same)
                    writer.println("yes");
                else
                    writer.println("no");

            } else {
                uf.unionSet(x, y);
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

class UF { // OOP style
    private int[] p, rank, setSize;
    private int numSets;

    public UF(int N) {
        p = new int[N];
        rank = new int[N];
        setSize = new int[N];
        numSets = N;
        for (int i = 0; i < N; i++) {
            p[i] = i;
            rank[i] = 0;
            setSize[i] = 1;
        }
    }

    public int findSet(int i) {
        if (p[i] == i)
            return i;
        else {
            int ret = findSet(p[i]);
            p[i] = ret;
            return ret;
        }
    }

    public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        int x = findSet(i), y = findSet(j);
        if (x != y) {
            numSets--;
            // rank is used to keep the tree short
            if (rank[x] > rank[y]) {
                p[y] = x;
                setSize[x] = setSize[x] + setSize[y];
            } else {
                p[x] = y;
                setSize[y] = setSize[y] + setSize[x];
                if (rank[x] == rank[y])
                    rank[y]++;
            }
        }
    }

    public int numDisjointSets() {
        return numSets;
    }

    public int sizeOfSet(int i) {
        return setSize[findSet(i)];
    }
}