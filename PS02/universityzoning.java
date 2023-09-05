package PS02;

import java.io.*;
import java.lang.*;
import java.util.*;

public class universityzoning {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // read first line
        // ignore rows and cols cuz won't use
        reader.nextLong();
        reader.nextLong();
        int noFac = reader.nextInt();
        int noStudents = reader.nextInt();
        int noCompliant = reader.nextInt();

        // create Array of Coordinates for each faculty
        List<Faculty> faculties = new ArrayList<>();

        // reading coordinates assigned to each faculty
        for (int i = 0; i < noFac; i++) {
            int noCells = reader.nextInt();
            List<Coordinate> coordinates = new ArrayList<>();
            while (noCells-- > 0) {
                long row = reader.nextLong();
                long col = reader.nextLong();
                Coordinate coord = new Coordinate(row, col);
                // store assigned coordinates into a list
                coordinates.add(coord);
            }
            // sort this list and then create a faculty object with it
            Collections.sort(coordinates);
            Faculty fac = new Faculty(coordinates);
            // faculties are added in order so
            // faculty 1 is in 0 index, 2 in 1 index....
            faculties.add(fac);
        }

        // now for Students
        for (int i = 0; i < noStudents; i++) {
            long row = reader.nextLong();
            long col = reader.nextLong();
            Coordinate coord = new Coordinate(row, col);
            int studentNo = reader.nextInt();
            int facultyNo = reader.nextInt();
            Student stu = new Student(studentNo, coord);
            // store sutdent in their respective faculty objects
            faculties.get(facultyNo - 1).students.add(stu);
        }

        // sort the students in each faculty
        // so that it is easier to get their respective assigned cell
        for (Faculty f : faculties) {
            Collections.sort(f.students);
        }

        // compiling manhattan distances for all students in their respective faculties
        // when they move towards their intended locations
        for (int i = 0; i < noFac; i++) {
            Faculty current = faculties.get(i);
            current.complianceTarget = reader.nextInt();
            // while getting compliance target
            // calculate manhattan distance for each student in faculty
            for (int j = 0, k = current.students.size(); j < k; j++) {
                Student s = current.students.get(j);
                Coordinate assignedCell = current.assignedCoordinates.get(j);
                long distanceToAssigned = s.currentPosition.manhattanDistance(assignedCell);
                current.distances.add(distanceToAssigned);
            }
            Collections.sort(current.distances);
        }

        // step here is to gather the minimum steps needed for each faculty to hit their
        // compliance target
        long[] stepCompiled = new long[noFac];
        for (int i = 0; i < noFac; i++) {
            Faculty current = faculties.get(i);
            long steps = 0;
            for (int j = 0, k = current.complianceTarget; j < k; j++) {
                steps += current.distances.get(j);
            }
            stepCompiled[i] = steps;
        }

        // sort stepCompiled array
        Arrays.sort(stepCompiled);

        // now add up actual steps based on compliance target
        // i.e. only add those that are smallest to hit overall target

        long stepsActual = 0;
        for (int i = 0; i < noCompliant; i++) {
            stepsActual += stepCompiled[i];
        }
        writer.println(stepsActual);

        writer.close(); // do not forget!
    }

    static class FastScanner {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");

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
    }
}

class Coordinate implements Comparable<Coordinate> {
    public long row;
    public long col;

    Coordinate(long row, long col) {
        this.row = row;
        this.col = col;
    }

    public long manhattanDistance(Coordinate o) {
        return Math.abs(this.row - o.row) + Math.abs(this.col - o.col);
    }

    @Override
    public int compareTo(Coordinate o) {
        // if coordinate is in a earlier row, means 1
        // if coordinate is in same row, but earlier col, means 1
        if (this.row < o.row) {
            return -1;
        } else if (this.row == o.row && this.col < o.col) {
            return -1;
        } else if (this.row == o.row && this.col == o.col) {
            return 0;
        } else {
            return 1;
        }
    }
}

class Faculty {
    public List<Coordinate> assignedCoordinates;
    public List<Student> students = new ArrayList<>();
    public int complianceTarget = 0;
    public List<Long> distances = new ArrayList<>();

    Faculty(List<Coordinate> assignedCoordinates) {
        this.assignedCoordinates = assignedCoordinates;
    }

}

class Student implements Comparable<Student> {
    public int studentNo;
    public Coordinate currentPosition;

    Student(int studentNo, Coordinate currentPosition) {
        this.studentNo = studentNo;
        this.currentPosition = currentPosition;
    }

    @Override
    public int compareTo(Student s) {
        if (this.studentNo < s.studentNo) {
            return -1;
        } else {
            return 1;
        }
    }
}