import java.io.*;
import java.lang.*;
import java.util.*;

class Island implements Comparable<Island> {
    int no;
    int size;

    public Island(int no, int size) {
        this.no = no;
        this.size = size;
    }

    @Override
    public int compareTo(Island o) {
        return this.size - o.size;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.no, this.size);
    }

}

public class conquest {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int noIsland = reader.nextInt();
        int noBridges = reader.nextInt();
        ArrayList<ArrayList<Integer>> AL = new ArrayList<>();
        ArrayList<Island> islands = new ArrayList<>();
        for (int i = 0; i < noIsland; i++) {
            AL.add(new ArrayList<>());
        }

        for (int i = 0; i < noBridges; i++) {
            int start = reader.nextInt() - 1;
            int end = reader.nextInt() - 1;
            AL.get(start).add(end);
            AL.get(end).add(start);
        }

        boolean[] visited = new boolean[noIsland];
        for (int i = 0; i < noIsland; i++) {
            islands.add(new Island(i, reader.nextInt()));
            visited[i] = false;
        }

        int total = islands.get(0).size;
        visited[0] = true;

        PriorityQueue<Island> pq = new PriorityQueue<>();
        for (int i : AL.get(0)) {
            pq.add(islands.get(i)); // add all islands linked to 1
        }

        while (!pq.isEmpty() && pq.peek().size < total) {
            Island island = pq.poll();
            if (!visited[island.no]) {
                visited[island.no] = true;
                total += island.size;
                for (int i : AL.get(island.no)) {
                    if (!visited[i]) { // if island not visited yet
                        pq.add(islands.get(i));
                    }
                }
            }
        }

        writer.println(total);
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
