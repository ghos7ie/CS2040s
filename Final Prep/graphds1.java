import java.io.*;
import java.lang.*;
import java.util.*;

public class graphds1 {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int noVertices = reader.nextInt();
        int noEdges = reader.nextInt();
        // true if directed, false if undirected
        boolean directed = reader.nextInt() == 2 ? true : false;
        int tree = 0, bipartite = 0, dag = 0;
        // checking for complete graph
        int complete = isComplete(directed, noVertices, noEdges);

        ArrayList<ArrayList<Integer>> AL = new ArrayList<>();
        int[] inDegree = new int[noVertices];
        for (int i = 0; i < noVertices; i++) {
            AL.add(new ArrayList<>());
            inDegree[i] = 0;
        }

        for (int i = 0; i < noEdges; i++) {
            int start = reader.nextInt();
            int end = reader.nextInt();
            AL.get(start).add(end);
            inDegree[end]++;
            if (!directed) {
                AL.get(end).add(start); // for undirected graphs
            }
        }

        boolean connected = false;
        ArrayList<Integer> rootList = new ArrayList<>();
        if (directed) {
            int root = 0;
            for (int i = 0; i < noVertices; i++) {
                if (inDegree[i] == 0) {
                    root = i;
                    rootList.add(i);
                }
            }
            connected = isConnected(AL, root);
        } else {
            connected = isConnected(AL);
        }
        // check for tree using definition: graph is connected and has n-1 edges
        if (noEdges == noVertices - 1 && connected) { // then check if tree
            tree = 1;
        }

        // check for bipartite
        if (tree == 1 || noEdges == 0) {
            bipartite = 1; // if is tree, will be bipartite. can bypass the check
        } else {
            // need to check using definition: bipartite graph is 2-colorable
            bipartite = isBipartite(AL, noVertices) ? 1 : 0;
        }
        // check for dag using definition: DAG is not cyclic and is connected
        if (directed) {
            if (tree == 1) {
                dag = 1;
            } else {
                if (rootList.size() < noVertices) {
                    dag = isDAG(AL, rootList, inDegree, noVertices) ? 1 : 0;
                }
            }
        }
        if (!directed && noEdges == 0) {
            dag = 1;
        }
        writer.printf("%s %s %s %s", tree, complete, bipartite, dag);

        writer.close(); // do not forget!
    }

    static int isComplete(boolean d, int v, int e) {
        if (!d && e == (v * (v - 1) / 2)) { // undirected
            return 1;
        }
        if (d && e == (v * (v - 1))) { // directed
            return 1;
        }
        return 0;
    }

    static boolean isConnected(ArrayList<ArrayList<Integer>> al) {
        // use dfs to check if all vertices are reachable
        boolean[] visited = new boolean[al.size()];
        for (int i = 0; i < al.size(); i++) {
            visited[i] = false;
        }

        dfs(al, visited, 0, -1); // start dfs from 0
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }

    static boolean isConnected(ArrayList<ArrayList<Integer>> al, int root) {
        // use dfs to check if all vertices are reachable
        boolean[] visited = new boolean[al.size()];
        for (int i = 0; i < al.size(); i++) {
            visited[i] = false;
        }
        dfs(al, visited, root, -1); // start dfs from 0
        for (boolean visit : visited) {
            if (!visit) {
                return false;
            }
        }
        return true;
    }

    static void dfs(ArrayList<ArrayList<Integer>> al, boolean[] visited, int current, int parent) {
        visited[current] = true;
        for (int i : al.get(current)) {
            if (!visited[i]) {
                dfs(al, visited, i, current);
            }
        }
    }

    static boolean isBipartite(ArrayList<ArrayList<Integer>> al, int v) {
        int[] colors = new int[v];
        Arrays.fill(colors, -1);

        for (int i = 0; i < v; i++) {
            if (colors[i] == -1) {
                if (!bfs(al, i, colors)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean bfs(ArrayList<ArrayList<Integer>> al, int start, int[] colors) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        colors[start] = 0;

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int i : al.get(current)) {
                if (colors[i] == -1) {
                    colors[i] = 1 - colors[current]; // set to opposite color
                    q.offer(i);
                } else if (colors[i] == colors[current]) {
                    return false; // not bipartite
                }
            }
        }
        return true;
    }

    static boolean isDAG(ArrayList<ArrayList<Integer>> al, ArrayList<Integer> roots, int[] inDegree, int v) {
        // implement topo sort: kahn's algorithm
        Queue<Integer> q = new LinkedList<>();
        for (int root : roots) {
            q.add(root); // add all vertices with 0 in degree
        }
        int count = 0; // no of visited vertices
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int next : al.get(u)) {
                if (--inDegree[next] == 0) {
                    q.add(next);
                }
            }
            count++;
        }
        if (count != v) {
            return false;
        }
        return true;
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
