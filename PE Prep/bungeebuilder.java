// RAYSON'S METHOD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bungeebuilder {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter writer = new PrintWriter(System.out);

        StringTokenizer tk = new StringTokenizer(reader.readLine());
        
        int mountains = Integer.parseInt(tk.nextToken());

        ArrayList<Integer> heights = new ArrayList<>();

        tk = new StringTokenizer(reader.readLine());
        for (int i = 0; i < mountains; i++) {
            int height = Integer.parseInt(tk.nextToken());
            heights.add(height);
        }
        
        int distance = 0;
        int min = heights.get(0);
        int max = heights.get(0);

        for (int i = 0; i < mountains; i++) {
            int currMountain = heights.get(i);
            min = Math.min(min, currMountain);

            if (currMountain <= max) {
                distance = Math.max(distance, currMountain - min);
            } else {
                // new peak encountered, can build bridge from previous peak to new peak, calculate new distance
                distance = Math.max(distance, max - min);
                min = currMountain;
                max = currMountain;
            }
        }

        writer.println(distance);
        writer.close();
    }
}