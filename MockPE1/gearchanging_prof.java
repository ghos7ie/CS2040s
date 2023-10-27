import java.util.*;

class gearchanging {
  public static void main (String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt(), M = sc.nextInt(), P = sc.nextInt();
    ArrayList<Integer> C = new ArrayList<>();
    for (int i = 0; i < N; ++i){
      C.add(sc.nextInt());
    }
    
    ArrayList<Integer> D = new ArrayList<>();
    for (int i = 0; i < M; ++i){
      D.add(sc.nextInt());
    }

    ArrayList<Double> ratios = new ArrayList<>();
    for (int i = 0; i < N; ++i){
      for (int j = 0; j < M; ++j) {
        ratios.add((double) C.get(i) / D.get(i));
      }
    }

    Collections.sort(ratios);

    boolean rideOn = true;
    for (int i = 0; i < ratios.size() - 1; ++i) {
      if ((ratios.get(i+1) - ratios.get(i)) / ratios.get(i) > P/100.0) {
        rideOn = false;
        break;
      }
    }
    System.out.println(rideOn ? "Ride on!" : "Time to change gears!");
  }
}
