import java.util.Scanner;

public class codetosavelives {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // get number of test cases
        int t = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < t; i++){
            // get first test case and add the 2 sets of numbers together
            int x = Integer.parseInt(sc.nextLine().replaceAll("\\s+",""));
            // must parse the numbers first after removing the space between
            int y = Integer.parseInt(sc.nextLine().replaceAll("\\s+",""));
            // then add the added number to output array
            String output = String.valueOf(x+y);
            for (int j = 0, k = output.length(); j < k; j++ ){
              System.out.print(output.charAt(j) + " ");
            }
            System.out.println();
        }
        sc.close();
    }
}
