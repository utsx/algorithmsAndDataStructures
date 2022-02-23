import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class InsertionSort {
    public static void main(String[] args) throws IOException {
        long[] readArgs = myReader(new FileInputStream("src/main/resources/input.txt"));
        long n = readArgs[0];

    }
    public static long[] myReader(InputStream in){
        Scanner scanner = new Scanner(in);
        long n = scanner.nextLong();
        scanner.nextLine();
        long[] args = new long[Math.toIntExact(n + 1)];
        args[0] = n;
        for(int i = 1; i <= n; i++){
            args[i] = scanner.nextLong();
        }
        return args;
    }
}
