import java.util.Scanner;
import java.math.BigInteger;

public class babypanda {
    public static void main(String[] args){
      Scanner sc = new Scanner(System.in);
      // take values in as string and convert as needed later
      String noOfDays = sc.next();
      // Slimes present after [noOfDays] night
      String noOfSlimes = sc.next();
      // idea: do in binary since if it sneezes, the count will be 0b1
      // as the slimes continue to split: 0b10 > 0b100 > 0b1000
      // if the panda sneezes again: 0b101 > 0b1010 > 0b10100
      // output is number of times panda sneezed
      
      // convert to binary
      // line 18-25 can be converted into Long.toBinaryString(sc.nextLong()).chars().filter(c -> c == '1').count();
      BigInteger intNoOfSlimes = new BigInteger(noOfSlimes);
      String binarySlimes = intNoOfSlimes.toString(2); // Convert to binary
      int sneezeCount = 0;
      for(int i = 0, n = binarySlimes.length(); i < n; i++){
        if (binarySlimes.charAt(i) == '1'){
          sneezeCount++;
        }
      }
      System.out.println(sneezeCount);
    }
}
