import java.io.*;
import java.lang.*;
import java.util.*;

public class laptopstickers {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        int[] info = reader.readArray(3);
        int cols = info[0];
        int rows = info[1];
        int noStickers = info[2];
        char[][] preview = new char[rows][cols];
        for (int i = 0; i < noStickers; i++){
          int sticker = 97 + i;
          int[] info2 = reader.readArray(4);
          /*
            info2[0] - lenght of sticker (cols)
            info2[1] - height of sticker (rows)
            info2[2] - start r of sticker
            info2[3] - start h of sticker
          */
          // loop from the starting cordinate of item,
          // end at start + length
          
          for (int j = info2[3], k = info2[3] + info2[1]; j < k; j++){
            if (j < rows){
              for (int l = info2[2], m = info2[2] + info2[0]; l < m; l++) {
                if (l < cols){
                  preview[j][l] = (char) sticker;
                }
              }
            }
          }
        }
        
        for (int i = 0; i < rows; i++){
          for (int k = 0; k < cols; k++){
            writer.print(preview[i][k] == '\0' ? '_' : preview[i][k]);
          }
          writer.println();
        }
        
      writer.close(); // do not forget!
    }
    
    

    static class FastScanner {
        BufferedReader br = new BufferedReader(
                            new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }
        
        String nextLine() {
            String st = "";
            try {
                st = br.readLine();
            }catch (IOException e) {
                    e.printStackTrace();
                }
            return st;
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
