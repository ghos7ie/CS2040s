import java.util.Scanner;

public class coffeecupcombo{
  public static void main(String[] args){
    Scanner sc = new Scanner(System.in);
    int noOfLect = Integer.parseInt(sc.nextLine());
    String lectCoffee = sc.nextLine();
    sc.close();
    // loop through the lectures starting at 0 coffees
    int awake = 0;
    int coffeeCount = 0;
    for (int i = 0, n = noOfLect; i < n; i++){
      if (lectCoffee.charAt(i) == '1'){
        coffeeCount = 2;
        awake++;
      }
      if(lectCoffee.charAt(i) == '0'&& coffeeCount > 0){
        coffeeCount = coffeeCount - 1 < 0? 0: coffeeCount -1;
        awake++;
      }
    }
    System.out.println(awake);
  }
}
