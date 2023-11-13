// obtained from https://github.com/RussellDash332/kattis/blob/6020829a5fd1666f8994a591cb7f1efdb5a0ca98/src/Magic%20Sequence/MagicSequence.java
import java.io.*;
import java.util.*;

public class magicsequence {
    public static void main(String[] args) throws IOException {
        Reader sc = new Reader();
        PrintWriter writer = new PrintWriter(System.out);
        int tc = sc.nextInt();
        while (tc-- > 0) {
            int n = sc.nextInt();
            long a = sc.nextLong(), b = sc.nextLong(), c = sc.nextLong(), x = sc.nextLong(), y = sc.nextLong();
            int bucks = 31623; // 10**4.5, best case, only two iterations

            long curr = a;
            long[] seq = new long[n];

            for (int i = 0; i < n; i++) {
                seq[i] = curr;
                curr = (curr * b + a) % c;
            }

            // First iteration
            int[] cnt1 = new int[bucks];
            for (long i : seq)
                cnt1[(int) i % bucks]++;
            for (int i = 1; i < bucks; i++)
                cnt1[i] += cnt1[i - 1];
            long[] seq2 = new long[n];
            int pos1 = n - 1, el1 = 0;
            while (pos1 >= 0) {
                el1 = (int) seq[pos1];
                seq2[cnt1[el1 % bucks] - 1] = el1;
                cnt1[el1 % bucks]--;
                pos1--;
            }

            // Second iteration
            int[] cnt2 = new int[bucks];
            for (long i : seq2)
                cnt2[(int) i / bucks]++;
            for (int i = 1; i < bucks; i++)
                cnt2[i] += cnt2[i - 1];
            long[] seq3 = new long[n];
            int pos2 = n - 1, el2 = 0;
            while (pos2 >= 0) {
                el2 = (int) seq2[pos2];
                seq3[cnt2[el2 / bucks] - 1] = el2;
                cnt2[el2 / bucks]--;
                pos2--;
            }

            // Final extension
            long v = 0;
            for (long i : seq3)
                v = (v * x + i) % y;
            writer.println(v);
        }
        writer.flush();
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ') {
                c = read();
            }
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0,
                                 BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}