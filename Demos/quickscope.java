
// 90/100 implementation -- GOOD ENOUGH :))
// add a cache to keep track
import java.io.*;
import java.util.*;

class Scope implements Comparable<Scope> {
    int scopeNo;
    HashMap<String, String> variables;

    public Scope(int scopeNo) {
        this.scopeNo = scopeNo;
        this.variables = new HashMap<>();
    }

    @Override
    public int compareTo(Scope o) {
        return this.scopeNo - o.scopeNo;
    }
}

public class quickscope {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int lines = reader.nextInt();
        // map each scope into the hashmap
        TreeSet<Scope> scopeMap = new TreeSet<>();
        HashMap<String, Stack<String>
        int scopeNo = 0;
        scopeMap.add(new Scope(scopeNo));
        while (lines-- > 0) {
            String operation = reader.next();
            if (operation.equals("DECLARE")) {
                String var = reader.next();
                String type = reader.next();
                Scope scope = scopeMap.last();
                if (scope.variables.containsKey(var)) {
                    writer.println("MULTIPLE DECLARATION");
                    break;
                } else {
                    scope.variables.put(var, type);
                }
            } else if (operation.equals("TYPEOF")) {
                String var = reader.next();
                boolean printed = false;
                TreeSet<Scope> copy = (TreeSet<Scope>) scopeMap.clone();
                while (!copy.isEmpty() && !printed) {
                    Scope scope = copy.pollLast();
                    if (scope.variables.containsKey(var)) {
                        writer.println(scope.variables.get(var));
                        printed = true;
                    }
                }
                if (!printed) {
                    writer.println("UNDECLARED");
                }

            } else if (operation.equals("{")) {
                ++scopeNo;
                scopeMap.add(new Scope(scopeNo));
            } else if (operation.equals("}")) {
                scopeMap.pollLast();
                scopeNo--;
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