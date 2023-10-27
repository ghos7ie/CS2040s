/**
 * A0253229E
 * Lye Cheng See Lewis
 * LAB 03
 * Ramapriyan
 * traveltheskies
**/
import java.io.*;
import java.lang.*;
import java.util.*;

class Flight {
    // sort using this
    int departureDay;
    int start;
    int destination;
    int capacity;

    public Flight(int start, int destination, int departureDay, int capacity) {
        this.start = start;
        this.destination = destination;
        this.departureDay = departureDay;
        this.capacity = capacity;
    }
}

class Departure {
    int departureDay;
    int start;
    int customers;

    public Departure(int start, int departureDay, int customers) {
        this.start = start;
        this.departureDay = departureDay;
        this.customers = customers;
    }
}

public class traveltheskies {

    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        int airportNo = reader.nextInt();
        int departureWindow = reader.nextInt();
        int flightsInWindow = reader.nextInt();
        ArrayList<ArrayList<Flight>> flightList = new ArrayList<>();
        ArrayList<ArrayList<Departure>> departureList = new ArrayList<>();
        boolean optimal = true;
        // get flight and departures by days so
        for (int i = 0; i < departureWindow; i++) {
            flightList.add(new ArrayList<>());
            departureList.add(new ArrayList<>());
        }

        while (flightsInWindow-- > 0) {
            int start = reader.nextInt() - 1;
            int destination = reader.nextInt() - 1;
            int departureDay = reader.nextInt() - 1;
            int capacity = reader.nextInt();
            Flight f = new Flight(start, destination, departureDay, capacity);
            flightList.get(departureDay).add(f);
        }

        int kn = airportNo * departureWindow;
        while (kn-- > 0) {
            int start = reader.nextInt() - 1;
            int departureDay = reader.nextInt() - 1;
            int customers = reader.nextInt();
            Departure d = new Departure(start, departureDay, customers);
            departureList.get(departureDay).add(d);
        }

        int[] airportCapacity = new int[airportNo];
        // loop through each day
        for (int i = 0; i < departureWindow; i++) {
            // add number of people flying off on day i into capacity
            ArrayList<Departure> departures = departureList.get(i);
            for (Departure d : departures) {
                airportCapacity[d.start] += d.customers;
            }
            // loop through each flight to ensure it is full
            // NOTE: customers can leave a day after they arrive too
            ArrayList<Flight> flights = flightList.get(i);
            for (Flight f : flights) {
                // check capacity at airport at current day
                // if airport capacity is more or equals to flight capacity
                // it is filled
                if (f.capacity <= airportCapacity[f.start]) {
                    airportCapacity[f.start] -= f.capacity;
                } else {
                    optimal = false;
                    break;
                }
            }

            // each customer can only fly once per day
            for (Flight f : flights) {
                airportCapacity[f.destination] += f.capacity;
            }
        }
        writer.print(optimal ? "optimal" : "suboptimal");
        writer.close(); // do not forget!
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

        // this method reads until the first space
        String next() {
            while (!st.hasMoreTokens())
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        int[] readArray(int n) {
            int[] a = new int[n];
            for (int i = 0; i < n; i++)
                a[i] = nextInt();
            return a;
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        // this method reads the entire line
        String nextLine() {
            String s = "";
            try {
                return s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }
    }
}
