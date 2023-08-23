/*
There are still faster methods since someone I know managed to pass the test cases while using scanner
*/
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class falcondive {
  public static void main(String[] args) {

    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try {
      String input = reader.readLine();
      String[] token = input.split(" ");
      int rows = Integer.parseInt(token[0]);
      int cols = Integer.parseInt(token[1]);
      char c = token[2].charAt(1);

      char[][] image1 = new char[rows][cols];
      char[][] image2 = new char[rows][cols];
      char[][] output = new char[rows][cols];
      boolean found = false;
      int colShift = 0;
      int rowShift = 0;

      // reading first image
      for (int i = 0; i < rows; i++) {
        String row = reader.readLine();
        image1[i] = row.toCharArray();
        int index = row.indexOf(c);
        if (!found && index != -1) {
          // System.out.println("X: " + row.indexOf(c));
          // System.out.println("Y: " + i);
          colShift = index;
          rowShift = i;
          found = true;
        }
      }

      reader.readLine();
      found = false;
      // reading second image
      for (int i = 0; i < rows; i++) {
        String row = reader.readLine();
        image2[i] = row.toCharArray();
        int index = row.indexOf(c);
        if (!found && index != -1) {
          // System.out.println("X: " + row.indexOf(c));
          // System.out.println("Y: " + i);
          colShift = index - colShift;
          rowShift = i - rowShift;
          found = true;
        }
      }

      // "copy" image 2 over to final image < - might take too long???
      // problem is i need to start shift everything and touch every element no?

      // figure out how much is the shift

      // 1. using image 2 as the base
      // 2. looping through each char
      // 3. if the char is the character, try to shift
      // then check image 1 for what the bg is
      // 4. if not char, just paste

      // how to shift?
      // 1. check if shift is valid (so not out of bounds in output array)
      // 2. valid then put there first
      // 3. if not valid, then ignore

      for (int row = 0; row < rows; row++) {
        for (int col = 0; col < cols; col++) {
          if (image2[row][col] == c) {
            int newRow = row + rowShift;
            int newCol = col + colShift;
            if (isValid(newRow, newCol, rows, cols)) {
              // since the new location of the pixel is valid in output array
              // set it the c
              output[newRow][newCol] = c;
              output[row][col] = image1[row][col];
            } else {
              // since the new location is a no goto
              // set current pixel of output to the pxiel in image 1
              output[row][col] = image1[row][col];
            }
          } else {
            // if the output is not populated with silhouette yet
            if (output[row][col] != c) {
              // fill with current pixel of image2
              output[row][col] = image2[row][col];
            }
          }
        }
      }
      print2DArray(output, rows, cols);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        reader.close();
      } catch (Exception e) {
        e.printStackTrace();
      }

    }
  }

  public static void print2DArray(char[][] charArray, int rows, int cols) {
    PrintWriter pw = new PrintWriter(System.out);
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        pw.print(charArray[i][j]);
      }
      pw.println();
    }
    pw.flush();
    pw.close();
  }

  public static boolean isValid(int row, int col, int maxRow, int maxCol) {
    // System.out.println(row);
    // System.out.println(col);
    // System.out.println(maxRow);
    // System.out.println(maxCol);
    if (row >= maxRow || row <= -1) {
      return false;
    }
    if (col >= maxCol || col <= -1) {
      return false;
    }
    return true;
  }
}
