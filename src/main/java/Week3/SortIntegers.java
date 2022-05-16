//package Week3;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class SortIntegers {

    static int n, m;
    static int[] a;
    static int[] c;
    static int maxVal = 0;

    public static void main(String[] args) throws IOException {
        read();
        int[] sortArray = new int[c.length];
        countingSort(c, sortArray);
        write(print10th(sortArray));
    }

    public static void read() throws FileNotFoundException {
        InputStream in = new FileInputStream(add() + "input.txt");
        Scanner scanner = new Scanner(in);

        n = scanner.nextInt();
        a = new int[n];
        m = scanner.nextInt();
        c = new int[n * m];

        scanner.nextLine();
        for (int i = 0; i < n; i++){
            a[i] = scanner.nextInt();
        }

        scanner.nextLine();
        int counter = 0;
        for (int i = 0; i < m; i++){
            int buffer = scanner.nextInt();
            for (int j = 0; j < n; j++){
                   c[counter] = a[j] * buffer;
                   maxVal = Math.max(c[counter], maxVal);
                   counter++;
            }
        }
    }

    public static String print10th(int[] sortedArray){
        int ans = 0;
        for(int i = 0; i < n * m; i++){
            if(i % 10 == 0) {
                ans += sortedArray[i];
            }
        }
        return String.valueOf(ans);
    }

    public static String add(){
        String line = "";
        line += "src/main/resources/";
        return line;
    }

    public static void countingSort(int[] array, int[] sortedArray) {
        long[] buffer = new long[Math.toIntExact(maxVal + 1)];
        for(int j = 0; j < array.length; j++) {
            buffer[array[j]]++;
        }
        for(int i = 1; i < maxVal + 1; i++) {
            buffer[i] += buffer[i - 1];
        }
        for(int j = array.length - 1; j >= 0; j--) {
            buffer[array[j]]--;
            sortedArray[Math.toIntExact(buffer[array[j]])] = array[j];
        }
    }


    public static void write(String result) throws IOException {
        FileWriter fileWriter = new FileWriter(add() + "output.txt");
        fileWriter.write(result);
        fileWriter.flush();
    }
}
