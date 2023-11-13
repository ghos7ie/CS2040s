import java.io.*;
import java.lang.*;
import java.util.*;

class Tree { // vertex
    double x;
    double y;

    public Tree(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

class Connection implements Comparable<Connection> { // edge
    int start;
    int end;
    double distance;

    public Connection(int start, int end, double distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    @Override
    public int compareTo(Connection o) {
        return Double.compare(this.distance, o.distance);
    }

}

public class treehouses {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int noTrees = reader.nextInt();
        int noWalkable = reader.nextInt(); // already connected
        int noConnected = reader.nextInt(); // already connected

        UnionFind ufds = new UnionFind(noTrees);
        Tree[] treesArray = new Tree[noTrees];

        for (int i = 0; i < noTrees; i++) {
            treesArray[i] = new Tree(Double.parseDouble(reader.next()), Double.parseDouble(reader.next()));
        }

        for (int i = 1; i < noWalkable; i++) {
            ufds.unionSet(0, i); // unionSet already runs isSameSet()
        }

        for (int i = 0; i < noConnected; i++) {
            int start = reader.nextInt() - 1;
            int end = reader.nextInt() - 1;
            ufds.unionSet(start, end);
        }

        PriorityQueue<Connection> pq = new PriorityQueue<>();
        // create edges to all trees
        for (int i = 0; i < noTrees; i++) {
            for (int j = i + 1; j < noTrees; j++) {
                double x = treesArray[j].x - treesArray[i].x;
                double y = treesArray[j].y - treesArray[i].y;
                double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); // get straight line distance
                pq.add(new Connection(i, j, distance));
            }
        }

        double distance = 0.0;
        while (!pq.isEmpty()) {
            Connection conn = pq.poll();
            // have to check if same set here
            // to add up distance
            if (!ufds.isSameSet(conn.start, conn.end)) {
                ufds.unionSet(conn.start, conn.end);
                distance += conn.distance;
            }
        }

        writer.format("%.6f", distance);
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

// Union-Find Disjoint Sets Library written in OOP manner, using both path
// compression and union by rank heuristics
class UnionFind { // OOP style
    private ArrayList<Integer> p, rank, setSize;
    private int numSets;

    public UnionFind(int N) {
        // IF USED AND TLE, CAN TRY CHANGING ARRAYLIST TO ARRAY!
        p = new ArrayList<>(N);
        rank = new ArrayList<>(N);
        setSize = new ArrayList<>(N);
        numSets = N;
        for (int i = 0; i < N; i++) {
            p.add(i);
            rank.add(0);
            setSize.add(1);
        }
    }

    public int findSet(int i) {
        if (p.get(i) == i)
            return i;
        else {
            int ret = findSet(p.get(i));
            p.set(i, ret);
            return ret;
        }
    }

    public Boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public void unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            numSets--;
            int x = findSet(i), y = findSet(j);
            // rank is used to keep the tree short
            if (rank.get(x) > rank.get(y)) {
                p.set(y, x);
                setSize.set(x, setSize.get(x) + setSize.get(y));
            } else {
                p.set(x, y);
                setSize.set(y, setSize.get(y) + setSize.get(x));
                if (rank.get(x) == rank.get(y))
                    rank.set(y, rank.get(y) + 1);
            }
        }
    }

    public int numDisjointSets() {
        return numSets;
    }

    public int sizeOfSet(int i) {
        return setSize.get(findSet(i));
    }
}
