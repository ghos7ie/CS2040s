import java.util.Scanner;
import java.util.Arrays;

class electionparadox {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int regionCount = Integer.parseInt(sc.nextLine());
        String numbersStr = sc.nextLine();
        // split the numbers by space and 
        // then put them into int array
        String[] numbersArr = numbersStr.split(" ", -1);
        sc.close();
        int[] population  = new int[regionCount];
        for(int i = 0; i < regionCount; i++){
          population[i] = Integer.parseInt(numbersArr[i]);
        }
        Arrays.sort(population);
        // check how many they need to win
        int winCount = (regionCount/2) + 1;
        int voteCount = 0;
        // first loop settles minimum need for opposition to win
        // i.e. voteCount will add the minimum to lose
        // use winCount to count number of votes they lose
        for (int i = 0; i < winCount; i++){
          // divide by 2 only since decimals will be truncated
          // e.g. 3/2 = 1.5, but will be 1
          // the party will get 1 vote
          // voteCount += population[i]/2;
          // System.out.println(i + " " + population[i]/2);
        }
        for (int i = winCount; i < regionCount; i++){
          voteCount += population[i];
          // System.out.println(i + " " + (population[i]));
        }
        System.out.println(voteCount);
    }
}  
