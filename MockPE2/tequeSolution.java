// obtained from https://github.com/yizhenpong/cs2040/blob/main/Teque.java
import java.io.*;
import java.util.*;


public class Teque{
    public static void main(String[] args) {
        Kattio io = new Kattio(System.in, System.out);
        int operations = io.getInt();
        QueueArr teq = new QueueArr(operations);

        for (int i = 0; i < operations; i++){
            String command = io.getWord();
            int num = io.getInt();

            if (command.equals("push_back")) {
                teq.push_back(num);
            }
            else if (command.equals("push_front")) {
                teq.push_front(num);
            }
            else if (command.equals("push_middle")) {
                teq.push_middle(num);
            }
            else if (command.equals("get")) {
                io.println(teq.get(num));
            }
            else {
                io.println("Command not found");
            }
            //System.out.println(Integer.toString(teq.num_front) + " " + Integer.toString(teq.num_back));
            //teq.printArr();
        }
        io.close();
    }
}


class QueueArr {
    public int[] arr;
    public int front, back, front_middle, back_middle, num_front, num_back;
    public int capacity;

    public QueueArr(int n) {
        arr = new int[3*n]; // create array of integers
        front = n-1; // the queue is empty
        back = 2*n;
        front_middle = n-1;
        back_middle = 2*n;
        capacity = 3*n;
        num_front = 0;
        num_back = 0;
    }

    public void printArr() {
        System.out.print("Queue array: ");
        for (int i = 0; i < capacity;i++){
            if (i == front) {
                System.out.print("front: ");
            }
            if (i == front_middle) {
                System.out.print("front_middle: ");
            }
            if (i == back_middle) {
                System.out.print("back_middle: ");
            }
            if (i == back) {
                System.out.print("back: ");
            }
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public void push_middle(int x) {
        if (num_back == num_front){
            if (num_front == 0){
                front++;
            }
            front_middle++;
            arr[front_middle] = x;
            num_front++;
        }
        else if (num_back > num_front){
            //retrieve back middle, put at front middle,
            //put x in back middle
            int temp = arr[back_middle];
            arr[back_middle] = x;
            if (num_front == 0){
                front++;
            }
            front_middle++;
            arr[front_middle] = temp;
            num_front++;
        }
        else{
            if (num_back == 0){
                back--;
            }
            back_middle--;
            arr[back_middle] = x;
            num_back++;
        }
    }

    public void push_front(int x){
        if (num_back >= num_front){
            if (num_front == 0){
                front_middle--;
            }
            front--;
            arr[front] = x;
            num_front++;
        }
        else {

            front--;
            arr[front] = x;
            int temp = arr[front_middle];

            front_middle--;
            if (num_back == 0){
                back--;
            }
            back_middle--;
            arr[back_middle] = temp;
            num_back++;
        }
    }

    public void push_back(int x){
        if (num_back <= num_front){
            if (num_back == 0){
                back_middle++;
            }
            back++;
            arr[back] = x;
            num_back++;
        }
        else {
            back++;
            arr[back] = x;
            int temp = arr[back_middle];
            back_middle++;
            if (num_front == 0){
                front++;
            }
            front_middle++;
            arr[front_middle] = temp;
            num_front++;
        }
    }

    public int get(int index){
        if (index < num_front){
            return (arr[front + index]);
        }
        else {
            index = index - num_front;
            return(arr[back_middle+index]);
        }
    }

}
