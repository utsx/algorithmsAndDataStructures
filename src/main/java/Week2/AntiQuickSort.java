package Week2;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AntiQuickSort {
    static long[] array;
    static long[] ans;
    static long n;
    public static void main(String[] args) throws IOException {
        n = read();
        array = new long[Math.toIntExact(n)];
        for (int i = 0; i < n; i++){
            array[i] = i + 1;
        }
        generateArray(array);
        write(Arrays.stream(array)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
        //quickSort(0, n - 1);
    }

    public static long read() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("input.txt");
        Scanner scanner = new Scanner(inputStream);
        return scanner.nextLong();
    }

    public static void write(String result) throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(result);
        fileWriter.flush();
    }

    public static void generateArray(long[] array){
        for(int i = 2; i < n; i++){
            long buffer = array[i];
            array[i] =  array[i / 2];
            array[i / 2] = buffer;
        }
    }

    public static void quickSort(long l, long r){
        int i = Math.toIntExact(l), j = Math.toIntExact(r);
        long buffer;
        long key = array[Math.toIntExact((l + r) / 2)];
        while (i <= j) {
            while (array[i] < key) {
                i++;
            }
            while (key < array[j]) {
                j--;
            }
            if (i <= j) {
                buffer = array[i];
                array[i] = array[j];
                array[j] = buffer;
                i++;
                j--;
            }
        }
        if (l < j){
            quickSort(l, j);
        }
        if(r > i){
            quickSort(i, r);
        }
    }
}
