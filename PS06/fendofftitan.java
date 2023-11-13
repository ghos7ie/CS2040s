import java.io.*;
import java.lang.*;
import java.util.*;

class Road implements Comparable<Road> { // edge
    int end;
    int length;
    int obstacle;

    public Road(int end, int length, int obstacle) {
        this.end = end;
        this.length = length;
        this.obstacle = obstacle;
    }

    @Override
    public int compareTo(Road o) {
        if (this.obstacle == o.obstacle) {
            if (this.length == o.length) {
                return this.end - o.end;
            } else {
                return this.length - o.length;
            }
        } else {
            return this.obstacle - o.obstacle;
        }
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", end, length, obstacle);
    }
}

class Village implements Comparable<Village> { // vertex
    int villageNo;
    int distance;

    public Village(int villageNo, int distance) {
        this.villageNo = villageNo;
        this.distance = distance;
    }

    @Override
    public int compareTo(Village o) {
        if (this.distance == o.distance) {
            return this.villageNo - o.villageNo;
        } else {
            return this.distance - o.distance;
        }
    }
}

class Distance {
    int distance;
    int shamanCount; // stores number of shamans encountered so far
    int titanCount; // stores number of titans encountered so far

    public Distance(int distance, int shamanCount, int titanCount) {
        this.distance = distance;
        this.shamanCount = shamanCount;
        this.titanCount = titanCount;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.distance, this.shamanCount, this.titanCount);
    }
}

public class fendofftitan {
    public static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int noVillages = reader.nextInt();
        int noRoads = reader.nextInt();
        int startVillage = reader.nextInt() - 1;
        int destinationVillage = reader.nextInt() - 1;

        // AL for containing roads leading out of start villages (aka paths)
        ArrayList<ArrayList<Road>> AL = new ArrayList<>();
        for (int i = 0; i < noVillages; i++) {
            AL.add(new ArrayList<>());
        }

        while (noRoads-- > 0) {
            int start = reader.nextInt() - 1;
            int end = reader.nextInt() - 1;
            int length = reader.nextInt();
            // 0 - none, 1 - shaman, 2 - titan
            int obstacle = reader.nextInt();

            // 2 way since is road
            AL.get(start).add(new Road(end, length, obstacle));
            AL.get(end).add(new Road(start, length, obstacle));
        }

        if (AL.get(destinationVillage).size() == 0) {
            writer.println("IMPOSSIBLE"); // early termination if destination has no roads
        } else {
            // sort all the roads first
            for (ArrayList<Road> R : AL) {
                Collections.sort(R);
            }

            // initialise distance array
            Distance[] dist = new Distance[noVillages];
            for (int i = 0; i < noVillages; i++) {
                dist[i] = new Distance(INF, INF, INF);
            }

            dist[startVillage].distance = 0;
            dist[startVillage].shamanCount = 0;
            dist[startVillage].titanCount = 0;

            TreeSet<Village> tsVillage = new TreeSet<>();
            for (int i = 0; i < noVillages; i++) {
                tsVillage.add(new Village(i, dist[i].distance));
            }

            while (!tsVillage.isEmpty()) {
                Village top = tsVillage.pollFirst();
                int start = top.villageNo;
                for (Road road : AL.get(start)) {
                    /*
                     * 3 criterias for distance "improving" by order of importance
                     * 1. titan count goes down
                     * 2. shaman count goes down
                     * 3. distance goes down
                     */
                    int shaman = road.obstacle == 1 ? 1 : 0;
                    int titan = road.obstacle == 2 ? 1 : 0;
                    int end = road.end;
                    int distance = road.length;
                    if (dist[start].distance + distance >= dist[end].distance) {
                        continue;
                    }
                    // if (dist[start].titanCount + titan >= dist[end].titanCount) {
                    //     continue;
                    // }
                    // if (dist[start].shamanCount + shaman >= dist[end].shamanCount) {
                    //     continue;
                    // }
                    tsVillage.remove(new Village(end, dist[end].distance));
                    dist[end].distance = dist[start].distance + distance;
                    dist[end].shamanCount = dist[start].shamanCount + shaman;
                    dist[end].titanCount = dist[start].titanCount + titan;
                    tsVillage.add(new Village(end, dist[end].distance));
                }
            }
            writer.println(dist[destinationVillage]);
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
