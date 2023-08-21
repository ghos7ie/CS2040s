import java.util.Scanner;
import java.lang.Math;

/**
 * CONDITIONS CHECKED: CASE 1: BO3 NOT RESPECTED
 * 
 * CASE 2.1: FEDERER LOST A SET ALR, SO MATCH IS VOID CASE 2.1: FEDERER LOSE SET
 * CASE 2.2: FEDERER LOSE SET
 * 
 * CASE 3.1: EITHER HAS 2 WINS BUT STILL CONTINUE CASE 3.2: NOT ENOUGH WINS ON
 * BOTH SIDES
 * 
 * FOR FIRST 2 SETS CASE 4.1: EITHER IS MORE THAN 8 WINS CASE 4.2: IF BOTH SIDE
 * >= 6, BUT DIFF IS NOT 1 CASE 4.3: IF EITHER IS MORE THAN 7, BUT DIFF IS 3 OR
 * MORE
 * 
 * FOR LAST SET CASE 5.1: DIFF IS NOT 2 WHEN SCORE FOR BOTH IS 6 OR MORE CASE
 * 5.2: WHEN EITHER IS LESS THAN 6, BUT DIFF IS
 * 
 * CASE 6: TIE
 * 
 * CASE 7: BOTH LESS THAN 6
 **/

public class tenis {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String p1 = sc.next(), p2 = sc.next();
		sc.nextLine();
		int noOfMatches = Integer.parseInt(sc.nextLine());
		for (int i = 0; i < noOfMatches; i++) {
			// this splits 6:2 6:4 into [6:2, 6:4]
			String[] sets = sc.nextLine().split(" ");
			int noOfSets = sets.length;
			// track wins for each match
			int p1Win = 0, p2Win = 0;
			boolean valid = true;
			boolean fedLost = false;
			// CASE 1: BO3 NOT RESPECTED
			if (noOfSets != 2 && noOfSets != 3) {
				// CASE 1: noOfSets does not respect BO3
				valid = false;
			} else {
				// loop set
				setLoop: for (int j = 0; j < noOfSets; j++) {
					// step 1 : get individual score for each set
					String[] games = sets[j].split(":");
					int p1Score = Integer.parseInt(games[0]);
					int p2Score = Integer.parseInt(games[1]);
					// CASE 2.3: Once Federer loses, nothing else matters
					if (fedLost) {
						valid = false;
						break setLoop;
					}
					// CASE 2.1: Federer loses a set
					// Once he loses, the match does not count
					if (p1.equals("federer") && (p1Score < p2Score || p1Score == p2Score)) {
						valid = false;
						fedLost = true;
						break setLoop;
					}
					// CASE 2.2: Federer loses a set
					// Once he loses, the match does not count
					if (p2.equals("federer") && (p2Score < p1Score || p1Score == p2Score)) {
						valid = false;
						fedLost = true;
						break setLoop;
					}
					// CASE 6: Tie
					if (p1Score == p2Score) {
						valid = false;
						break setLoop;
					}
					// CASE 7: Both less than 6
					if (p1Score < 6 && p2Score < 6) {
						valid = false;
						break setLoop;
					}
					// CASE 4: FIRST 2 SETS
					if (j <= 1) {
						// CASE 4.1: Either one is more than 8
						if (p1Score >= 8 || p2Score >= 8) {
							valid = false;
							break setLoop;
						}
						// CASE 4.2: if both more than 6, diff cannot be more than 1
						if (p1Score >= 6 && p2Score >= 6 && Math.abs(p1Score - p2Score) != 1) {
							valid = false;
							break setLoop;
						}
						// CASE 4.3: if either is 7 or more, but diff is more than 2
						if ((p1Score >= 7 || p2Score >= 7) && Math.abs(p1Score - p2Score) >= 3) {
							valid = false;
							break setLoop;
						}
					}

					// if hit here, means that first 2 sets is okie

					// SUBSEQUENT SETS (from set 3 onwards)
					if (j >= 2) {
						// CASE 3.1: More than 2 wins, but still continue
						if (p1Win == 2 || p2Win == 2) {
							valid = false;
							break setLoop;
						}
						// CASE 5.1: Diff is not 2 when score both more than 6
						if (p1Score >= 6 && p2Score >= 6
								&& (Math.abs(p1Score - p2Score) >= 3 || Math.abs(p1Score - p2Score) == 1)) {
							valid = false;
							break setLoop;
						}
						// CASE 5.2: Diff is more than 7 when either is less than 6
						if ((p1Score < 6 || p2Score < 6) && Math.abs(p1Score - p2Score) >= 7) {
							valid = false;
							break setLoop;
						}
					}
					// COUNTING WINS
					if (p1Score > p2Score) {
						p1Win++;
					} else {
						p2Win++;
					}
				}
			}
			// CASE 3.2: BOTH NEVER HIT 2
			if (valid && p1Win < 2 && p2Win < 2) {
				valid = false;
			}

			if (valid) {
				System.out.println("da");
			} else {
				System.out.println("ne");
			}
		}
					sc.close();
	}
}
