
// 6% VA OQ 3 will be easier than this
import java.io.*;
import java.lang.*;
import java.util.*;

class Score implements Comparable<Score> {
    int index;
    int score;

    public Score(int index, int score) {
        this.index = index;
        this.score = score;
    }

    @Override
    public int compareTo(Score o) {
        return this.score - o.score;
    }
}

public class racinggame {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int operations = reader.nextInt();
        ArrayList<Score> scoreboard = new ArrayList<>();
        ArrayList<Integer> updateList = new ArrayList<>();
        int toPop = 0;
        for (int i = 0; i < operations; i++) {
            int operation = reader.nextInt();
            if (operation == 1) { // add to scoreboard
                scoreboard.add(new Score(toPop, reader.nextInt()));
            } else if (operation == 2) { // add number to all scores
                updateList.add(reader.nextInt());
                toPop++;
            } else if (operation == 3) { // output Z-th fastest time
                Collections.sort(scoreboard);
                Score score = scoreboard.get(reader.nextInt() - 1);
                
                // writer.println("Score: " + score.score +", " + score.index);
                // writer.println("toPop: " + toPop);
                // writer.println("update: " + updateList);
                int index = score.index;
                writer.println("S1 " + score.score);
                for (int j = index; j < toPop; j++) {
                    writer.println(updateList.get(j));
                    score.score += updateList.get(j);
                }
                writer.println("S " + score.score);
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
