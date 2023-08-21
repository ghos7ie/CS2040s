import java.util.Scanner;

class undeadoralive {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        // I HAD THE RIGHT SOLUTION, BUT I WAS USING SC.NEXT() SO IT ONLY READ UNTIL THE FIRST SPACE
        String input = sc.nextLine();
        sc.close();
        int smileCount = count(input, ":\\)");
        int frownCount = count(input, ":\\(");
        if (smileCount > 0 && frownCount > 0){
            System.out.println("double agent");
        }
        else if (smileCount > 0 && frownCount == 0){
            System.out.println("alive");
        }
        else if (smileCount == 0 && frownCount > 0){
            System.out.println("undead");
        }
        else{
            System.out.println("machine");
            
        }
    }
    
    public static int count(String input, String emoji){
        return input.split(emoji, -1).length-1;
    }
}
