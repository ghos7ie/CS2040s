// this code hits TLE at test case 48
// just saving this for future reference!

import java.util.Scanner;

class falcondive {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int rows = sc.nextInt();
    int cols = sc.nextInt();
    String temp = sc.nextLine();
    char c = temp.charAt(2);

    char[][] image1 = new char[rows][cols];
    char[][] image2 = new char[rows][cols];
    char[][] output = new char[rows][cols];
    boolean found = false;
    int colShift = 0;
    int rowShift = 0;
    
    // reading first image
    for (int i = 0; i < rows; i++) {
      String row = sc.nextLine();
      image1[i] = row.toCharArray();
      if (!found && row.indexOf(c) != -1){
        colShift = row.indexOf(c);
        rowShift = i;
        found = true;
      }
    }
    
    sc.nextLine();
    found = false;
    // reading second image
    for (int i = 0; i < rows; i++) {
      String row = sc.nextLine();
      image2[i] = row.toCharArray();
      if (!found && row.indexOf(c) != -1){
        // calculating the shift
        colShift = row.indexOf(c) - colShift;
        rowShift = i - rowShift;
        found = true;
      }
    }
    sc.close();
    
    for (int row = 0; row < rows; row++){
      for (int col = 0; col < cols; col++){
        // if the current pixel in image 2 is c
        if (image2[row][col] == c){
          int newRow = row + rowShift;
          int newCol = col + colShift;
          // check if the shift is within the bounds of output
          if (isValid(newRow, newCol, rows, cols)){
            // set c to the new location in output
            output[newRow][newCol] = c;
            // set current pixel to the pixel in image 1
            output[row][col] = image1[row][col];
          }
          else{
            // since not valid
            // cannot set, so just set current pixel to image 1
            output[row][col] = image1[row][col];
          }
        } else{
          // if the pixel in output array is not populated alr
          if(output[row][col] != c){
            // populate with image2's pixel
            output[row][col] = image2[row][col];
          }
        }  
      }
    }
    
    print2DArray(output, rows, cols);
  }

  public static void print2DArray(char[][] charArray, int rows, int cols) {
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        System.out.print(charArray[i][j]);
      }
      System.out.println();
    }
  }
  
  public static boolean isValid(int row, int col, int maxRow, int maxCol){
    if (row >= maxRow || row <= -1){
      return false;
    }
    if  (col >= maxCol || col <= -1){
      return false;
    }
    return true;
  }
}

