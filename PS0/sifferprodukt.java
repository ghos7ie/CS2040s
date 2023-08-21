import java.util.Scanner;

// i have no idea why it is produkt???
public class sifferprodukt{
    public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      int input = sc.nextInt();
      sc.close();
      System.out.println(produkt(input));
    }
    
    public static int produkt(int input){
      // stops when the given input is at 10 or less
      if (input < 10){
        return input;
      }
      int produkt = 1;
      while (input > 0){
        // start from last digit since order does not matter
        // % 10 is used to get last digit
        produkt *= input % 10 == 0? 1 : input%10;
        input = input/10;
      }
      // will continue to loop with the new number
      return produkt(produkt);
    }
}
