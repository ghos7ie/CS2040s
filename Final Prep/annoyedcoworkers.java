import java.io.*;
import java.lang.*;
import java.util.*;

class Coworker implements Comparable<Coworker> {
    long annoyanceLvl;
    long increase;
    long nextAnnoyanceLvl;

    public Coworker(long annoyanceLvl, long increase) {
        this.annoyanceLvl = annoyanceLvl;
        this.increase = increase;
        this.nextAnnoyanceLvl = annoyanceLvl + increase;
    }

    @Override
    public int compareTo(Coworker o) {
        // must compare with the next level of annoyance, not current
        if (this.nextAnnoyanceLvl == o.nextAnnoyanceLvl) {
            return Long.compare(this.increase, o.increase);
        } else {
            return Long.compare(this.nextAnnoyanceLvl, o.nextAnnoyanceLvl);
        }
    }

    @Override
    public String toString() {
        return String.format("(a: %s, d: %s)", this.annoyanceLvl, this.increase);
    }
}

public class annoyedcoworkers {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int noHelp = reader.nextInt();
        int noCowokers = reader.nextInt();
        PriorityQueue<Coworker> pq = new PriorityQueue<>();
        long max = 0; // keep track, PQ will pop least annoyed
        while (noCowokers-- > 0) {
            long annoyanceLvl = reader.nextLong();
            long increase = reader.nextLong();
            if (annoyanceLvl > max) {
                max = annoyanceLvl;
            }
            pq.add(new Coworker(annoyanceLvl, increase));
        }

        while (noHelp-- > 0) {
            Coworker co = pq.poll();
            co.annoyanceLvl += co.increase;
            if (co.annoyanceLvl > max) {
                max = co.annoyanceLvl;
            }
            // must readd, beacuse of co.nextAnnoyanceLvl, only updated during creation of
            // new coworker
            pq.add(new Coworker(co.annoyanceLvl, co.increase));
        }

        writer.println(max);

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
