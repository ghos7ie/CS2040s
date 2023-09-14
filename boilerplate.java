/*
  Shared by TA.
  Using BufferedReader and PrintWriter is a lot faster than Scanner and System.out
  Around 2 sec faster

  However, 
  this FastScanner class does not take input line by line.
  will take input like Scanner.next(), nextInt().

  If there are spaces between your input, and must read by line, just use BufferedReader

  ALSO REMEMBER TO USE 'throws IOException' in main so that don't have to do the tedious try catch loops
*/

/*
  Sample code for Buffered Reader and PrintWriter:
    import java.util.*;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

  Usage of BufferedReader:
    String line = br.readLine().strip();
    String[] firstLine =  br.readLine().split(" ");

  Usage of PrintWriter:
    pw.println(string); 
*/

import java.io.*;
import java.lang.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here

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
