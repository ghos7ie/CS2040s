import java.io.*;
import java.lang.*;
import java.util.*;

class Road { // edges
    int end;
    long length;
    int obstacle;

    public Road(int end, long length, int obstacle) {
        this.end = end;
        this.length = length;
        this.obstacle = obstacle;
    }

    @Override
    public String toString() {
        String o = "NA";
        if (obstacle == 1) {
            o = "shaman";
        } else if (obstacle == 2) {
            o = "titan";
        }
        return String.format("%s %s %s", this.end + 1, this.length, o);
    }

}

class Village implements Comparable<Village> { // vertex
    int no;
    long distance;
    int shaman;
    int titan;

    public Village(int no, long distance, int shaman, int titan) {
        this.no = no;
        this.distance = distance;
        this.shaman = shaman;
        this.titan = titan;
    }

    @Override
    public int compareTo(Village o) {
        if (this.titan == o.titan) {
            if (this.shaman == o.shaman) {
                return Long.compare(this.distance, o.distance);
            } else {
                return this.shaman - o.shaman;
            }
        } else {
            return this.titan - o.titan;
        }
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s %s)", (this.no + 1), this.distance, this.shaman, this.titan);
    }
}

class Distance {
    long distance;
    int shaman;
    int titan;

    public Distance(long distance, int shaman, int titan) {
        this.distance = distance;
        this.shaman = shaman;
        this.titan = titan;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.distance, this.shaman, this.titan);
    }
}

public class fendofftitan {
    public static long INF = 1000000000000L;

    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int noVillages = reader.nextInt();
        int noRoads = reader.nextInt();
        int startV = reader.nextInt() - 1;
        int destinationV = reader.nextInt() - 1;

        // AL for containing roads leading out of start villages (aka paths)
        ArrayList<ArrayList<Road>> AL = new ArrayList<>();
        for (int i = 0; i < noVillages; i++) {
            AL.add(new ArrayList<>());
        }

        while (noRoads-- > 0) {
            int start = reader.nextInt() - 1;
            int end = reader.nextInt() - 1;
            long length = reader.nextInt();
            // 0 - none, 1 - shaman, 2 - titan
            int obstacle = reader.nextInt();

            // 2 way since is road
            AL.get(start).add(new Road(end, length, obstacle));
            AL.get(end).add(new Road(start, length, obstacle));
        }

        if (AL.get(destinationV).size() == 0) {
            writer.println("IMPOSSIBLE");
        } else {
            Distance[] distArr = new Distance[noVillages];
            for (int i = 0; i < noVillages; i++) {
                distArr[i] = new Distance(INF, 1000000000, 1000000000);
            }
            distArr[startV] = new Distance(0, 0, 0);

            PriorityQueue<Village> pqVillage = new PriorityQueue<>();
            pqVillage.offer(new Village(startV, 0, 0, 0));

            while (!pqVillage.isEmpty()) {
                Village current = pqVillage.poll();
                if (current.no == destinationV) {
                    break;
                }
                // only take the village with the most updated distance value
                if (current.distance == distArr[current.no].distance && current.shaman == distArr[current.no].shaman
                        && current.titan == distArr[current.no].titan) {
                    for (Road r : AL.get(current.no)) {
                        int shaman = r.obstacle == 1 ? 1 : 0;
                        shaman += current.shaman;
                        int titan = r.obstacle == 2 ? 1 : 0;
                        titan += current.titan;
                        long distance = r.length + current.distance;
                        // what constitutes as not improving
                        if (distArr[r.end].titan > titan
                                || (distArr[r.end].titan == titan && distArr[r.end].shaman > shaman)
                                || (distArr[r.end].titan == titan && distArr[r.end].shaman == shaman && distArr[r.end].distance > distance)) {
                            distArr[r.end] = new Distance(distance, shaman, titan);
                            pqVillage.offer(new Village(r.end, distance, shaman, titan));
                        }
                    }
                }
            }
            if (distArr[destinationV].distance == INF) {
                writer.println("IMPOSSIBLE");
            } else {
                writer.println(distArr[destinationV]);
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
    }
}
