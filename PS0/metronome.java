// Problem A
import java.util.Scanner;

public class metronome {

     public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int input = sc.nextInt();
        sc.close();
        
        double output = (double) input/4;
        System.out.println(output);
    }
}
