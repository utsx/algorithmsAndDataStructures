import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class CountInversions {

    static long n;
    static long counter = 0;

    public static void main(String[] args) throws IOException {
        long[] array = read();
        counter = 0;
        mergeSort(array);
        write(String.valueOf(counter));
    }

    public static long[] read() throws FileNotFoundException {
        InputStream in = new FileInputStream("input.txt");
        Scanner scanner = new Scanner(in);
        n = scanner.nextLong();
        scanner.nextLine();
        long[] array = new long[(int) n];
        for (int i = 0; i < n; i++){
            array[i] = scanner.nextLong();
        }
        return array;
    }

    public static void write(String result) throws IOException {
        FileWriter fileWriter = new FileWriter("output.txt");
        fileWriter.write(result);
        fileWriter.flush();
    }

    public static long[] merge(long[] a, long[] b){
        int i = 0, j = 0;
        int n = a.length;
        int m = b.length;
        long[] c = new long[n + m];
        while (i < n || j < m){
            if((j == m) || ((i < n) && (a[i] <= b[j]))){
                c[i + j] = a[i];
                i++;
            }
            else{
                if(i < n){
                    counter += n - i;
                }
                c[i + j] = b[j];
                j++;
            }
        }
        return c;
    }

    static long[] mergeSort(long[] array){
        int n = array.length;
        if(n == 1){
            return array;
        }
        long[] l = Arrays.copyOfRange(array, 0, n/2);
        long[] r = Arrays.copyOfRange(array, n/2, n);
        l = mergeSort(l);
        r = mergeSort(r);

        return merge(l, r);
    }
}
