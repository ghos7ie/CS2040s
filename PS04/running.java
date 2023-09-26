
/**
 * A0253229E
 * Lye Cheng See Lewis
 * LAB 03
 * Ramapriyan
 * kaploeb
**/

import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.stream.Collectors;

public class running {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        // l - lap times recorded
        long l = reader.nextLong();
        // k - laps to finish
        long k = reader.nextLong();
        // s - start numbers handed out
        long s = reader.nextLong();

        HashMap<Long, Runner> runnersTiming = new HashMap<>();

        while (l-- > 0) {
            long runner = Long.parseLong(reader.next());
            String[] timing = reader.next().split("\\.");
            int mm = Integer.parseInt(timing[0]);
            int ss = Integer.parseInt(timing[1]);
            Runner actual = runnersTiming.get(runner);
            if (actual == null) {
                Runner run = new Runner(runner, mm, ss);
                runnersTiming.put(runner, run);
            } else {
                actual.addTime(mm, ss);
                actual.noLaps++;
            }
        }

        runnersTiming.values()
                .stream()
                .filter(r -> r.noLaps == k)
                .sorted()
                .forEach(r -> writer.println(r.id));

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

class Runner implements Comparable<Runner> {
    int noLaps;
    long id;
    int mm;
    int ss;

    public Runner(long id, int mm, int ss) {
        this.id = id;
        this.mm = mm;
        this.ss = ss;
        this.noLaps = 1;
    }

    public void addTime(int mm, int ss) {
        this.mm += mm;
        this.ss += ss;
        /*
         * 67 sec --> 7 sec
         */
        if (this.ss > 60) {
            this.ss -= 60;
            this.mm++;
        }
    }

    @Override
    // has to check if finished appropriate number of laps first
    public int compareTo(Runner o) {
        int i = Integer.compare(this.mm, o.mm);
        if (i == 0) {
            // since same minutes, compare ss
            int j = Integer.compare(this.ss, o.ss);
            if (j == 0) {
                return Long.compare(this.id, o.id);
            } else {
                return j;
            }
        } else {
            return i;
        }
    }
}
