import java.util.Scanner;

public class internationaldates {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String date = sc.next();
        // break up the string by /
        String[] parts = date.split("/");
        // now loop through each part
        if (Integer.parseInt(parts[0]) > 12) {
            System.out.println("EU");
        } else if (Integer.parseInt(parts[1]) > 12) {
            System.out.println("US");
        } else {
            System.out.println("either");
        }
        sc.close();
    }
}
