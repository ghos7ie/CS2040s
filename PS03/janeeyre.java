
/**
 * A0253229E
 * Lye Cheng See Lewis
 * LAB 03
 * Ramapriyan
**/

import java.io.*;
import java.lang.*;
import java.util.*;

public class janeeyre {
    public static void main(String[] args) throws IOException {
        FastScanner reader = new FastScanner();
        PrintWriter writer = new PrintWriter(System.out);

        // your code goes here
        // n - number of unread books
        int noUnread = reader.nextInt();
        // m - number of books to receive
        int noReceive = reader.nextInt();
        // k - pages in JaneEyre
        int jePages = reader.nextInt();
        // create Jane book
        Book jane = new Book("Jane Eyre", jePages);
        // set PQ to max, since Java is default to min
        PriorityQueue<Book> unreadQueue = new PriorityQueue<>(noUnread);
        unreadQueue.add(jane);
        // Linkedlist for books that her friends will give her
        PriorityQueue<Book> addQueue = new PriorityQueue<>(noReceive,
                (a, b) -> Integer.compare(a.timeReceived, b.timeReceived));

        while (noUnread-- > 0) {
            String text = reader.nextLine();
            String bookName = getBookName(text);
            // since have book name already, just split by space, since the pages will be at
            // the end
            String[] token = text.split(" ");
            int length = token.length;
            Book book = new Book(bookName, Integer.parseInt(token[length - 1]));
            unreadQueue.add(book);
        }

        while (noReceive-- > 0) {
            String text = reader.nextLine();
            String bookName = getBookName(text);
            // since have book name already, just split by space, since the pages will be at
            // the end
            String[] token = text.split(" ");
            int length = token.length;
            Book book = new Book(bookName, Integer.parseInt(token[length - 1]), Integer.parseInt(token[0]));
            addQueue.add(book);
        }

        // 1. long timer to keep track of time
        // 2. she will read finish the first book in unreadQueue
        // 3. add pages to timer
        // 4. check if any books in addQueue needs to be added

        long time = 0;
        Book book = unreadQueue.poll();
        while (book.title != "Jane Eyre") {
            time += book.pages;
            // if there are books received and
            // time is currently past or equal to receivedTime
            while (addQueue.size() > 0 && addQueue.peek().timeReceived <= time) {
                // add that book to unreadQueue
                Book addBook = addQueue.poll();
                unreadQueue.add(addBook);
            }
            // now set book to new book in order
            book = unreadQueue.poll();
        }
        // to add Jane Eyre pages
        time += book.pages;
        writer.println(time);
        writer.close(); // do not forget!
    }

    // deal with edge cases: book has " in name
    static String getBookName(String input) {
        // + 1 to get the next character after the "
        int start = input.indexOf("\"") + 1;
        // dunnid - 1 because end index is exclusive in substring
        int end = input.lastIndexOf("\"");
        return input.substring(start, end);
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

class Book implements Comparable<Book> {
    String title;
    int pages;
    int timeReceived;

    Book(String title, int pages) {
        this.title = title;
        this.pages = pages;
        this.timeReceived = 0;
    }

    Book(String title, int pages, int timeReceived) {
        this.title = title;
        this.pages = pages;
        this.timeReceived = timeReceived;
    }

    @Override
    public int compareTo(Book o) {
        // if this book's starting character is smaller than o's
        // means it should come first
        return this.title.compareTo(o.title);

    }

}