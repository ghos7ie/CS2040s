/**
 * A0253229E
 * Lye Cheng See Lewis
 * LAB 03
 * Ramapriyan
 * kannafriendship
**/
import java.io.*;
import java.lang.*;
import java.util.*;

class Interval implements Comparable<Interval> {
    long start;
    long end;

    public Interval(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public long count() {
        return this.end - this.start + 1;
    }

    @Override
    public int compareTo(Interval o) {
        if (this.start < o.start) {
            return -1;
        } else if (this.start == o.start) {
            return Long.compare(this.end, o.end);
        } else {
            return 1;
        }
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.start, this.end);
    }
}

public class kannafriendship {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        // number of points on track
        long n = reader.nextLong();
        // number of queries
        int q = reader.nextInt();
        TreeSet<Interval> intervalSet = new TreeSet<>();
        long count = 0;
        while (q-- > 0) {
            int caseNo = reader.nextInt();
            switch (caseNo) {
                case 1:
                    if (count == n) {
                        reader.nextLong();
                        reader.nextLong();
                        continue;
                    }
                    Interval current = new Interval(reader.nextLong(), reader.nextLong());
                    Interval existing = intervalSet.floor(current);
                    if (Objects.isNull(existing)) { // nothing earlier than current
                        existing = intervalSet.ceiling(current);
                        if (Objects.isNull(existing)) { // nothing ahead
                            count += current.count();
                            intervalSet.add(current);
                        } else {
                            if (existing.start > current.end) { // no touching, just add
                                count += current.count();
                                intervalSet.add(current);
                            } else if (existing.start == current.end) { // just merge
                                count -= existing.count();
                                existing.start = current.start;
                                count += existing.count();
                            } else { // overlap occurs
                                if (existing.end > current.end) { // expand current and add
                                    count -= existing.count();
                                    intervalSet.remove(existing);
                                    current.end = existing.end;
                                    intervalSet.add(current);
                                    count += current.count();
                                } else { // remove existing and keep checking for overlaps
                                    count -= existing.count();
                                    intervalSet.remove(existing);
                                    existing = intervalSet.ceiling(existing);
                                    while (!Objects.isNull(existing) && current.end >= existing.start) {
                                        count -= existing.count();
                                        intervalSet.remove(existing);
                                        if (existing.end > current.end) {
                                            current.end = existing.end;
                                        }
                                        existing = intervalSet.ceiling(existing);
                                    }
                                    intervalSet.add(current);
                                    count += current.count();
                                }
                            }
                        }
                    } else { // existing starts ealier than current
                        if (existing.end < current.start) { // no touching
                            existing = intervalSet.ceiling(current); // check if it touches the next interval inside
                            if (Objects.isNull(existing)) { // nothing ahead
                                intervalSet.add(current);
                                count += current.count();
                            } else { // something ahead
                                if (existing.start > current.end) { // no touching, just add
                                    count += current.count();
                                    intervalSet.add(current);
                                } else if (existing.start == current.end) { // just merge
                                    count -= existing.count();
                                    existing.start = current.start;
                                    count += existing.count();
                                } else { // overlap occurs
                                    if (existing.end > current.end) { // expand current and add
                                        count -= existing.count();
                                        intervalSet.remove(existing);
                                        current.end = existing.end;
                                        intervalSet.add(current);
                                        count += current.count();
                                    } else { // remove existing and keep checking for overlaps
                                        count -= existing.count();
                                        intervalSet.remove(existing);
                                        existing = intervalSet.ceiling(existing);
                                        while (!Objects.isNull(existing) && current.end >= existing.start) {
                                            count -= existing.count();
                                            intervalSet.remove(existing);
                                            if (existing.end > current.end) {
                                                current.end = existing.end;
                                            }
                                            existing = intervalSet.ceiling(existing);
                                        }
                                        intervalSet.add(current);
                                        count += current.count();
                                    }
                                }
                            }
                        } else { // merge
                            if (existing.end <= current.end) {
                                count -= existing.count();
                                current.start = existing.start;
                                intervalSet.remove(existing);
                                existing = intervalSet.ceiling(existing);
                                while (!Objects.isNull(existing) && current.end >= existing.start) {
                                    count -= existing.count();
                                    intervalSet.remove(existing);
                                    if (existing.end > current.end) {
                                        current.end = existing.end;
                                    }
                                    existing = intervalSet.ceiling(existing);
                                }
                                intervalSet.add(current);
                                count += current.count();
                            }
                        }
                    }
                    break;
                case 2:
                    writer.println(count);
                    break;
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