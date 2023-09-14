
/**
 * A0253229E
 * Lye Cheng See Lewis
 * LAB 03
 * Ramapriyan
**/
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class sim {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        // get number of test cases
        int noTests = Integer.parseInt(br.readLine());

        // loop through test cases
        for (int i = 0; i < noTests; i++) {
            // read entire line of string
            String line = br.readLine();
            // convert line read into a list of char
            List<Character> chars = line.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
            // create iterator for char in list created from input
            ListIterator<Character> listCharIterator = chars.listIterator();
            List<Character> output = new LinkedList<Character>();
            // cursor
            int count = 0;
            while (listCharIterator.hasNext()) {
                char c = listCharIterator.next();
                switch (c) {
                    case '<':
                        // if not at the start
                        if (count != 0) {
                            // remove previous character put into list
                            output.remove(count - 1);
                            count--;
                        }
                        break;
                    case '[':
                        // go to start
                        count = 0;
                        break;
                    case ']':
                        // go to current end
                        count = output.size();
                        break;
                    default:
                        output.add(count, c);
                        count++;
                }
            }
            for (Character c : output) {
                writer.print(c);
            }
            writer.println();
        }

        writer.close(); // do not forget!
    }

}