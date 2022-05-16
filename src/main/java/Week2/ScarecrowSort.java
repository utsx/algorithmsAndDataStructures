package Week2;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ScarecrowSort{

    static PrintWriter wr;
    static int[] arr;

    static int n;
    static int k;


    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";


        Path path = Paths.get(inputFile);
        Scanner sc = new Scanner(path).useLocale(Locale.UK);

        wr = new PrintWriter(new FileWriter(outputFile));

        n = sc.nextInt();
        k = sc.nextInt();

        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        sc.close();

        if(k == 1){
            wr.print("YES");
            wr.close();
            return;
        }

        pugalo_sort();

        for (int i = 1; i < n; i++) {
            if(arr[i] < arr[i-1]){
                wr.print("NO");
                wr.close();
                return;
            }
        }
        wr.print("YES");

//        System.out.println(Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")));

        wr.close();
    }

    private static void pugalo_sort() {
        boolean process = true;
        while(process){
            process = false;
            for (int i = 0; i < n - k; i++) {
                if (arr[i] > arr[i + k]) {
                    int temp = arr[i];
                    arr[i] = arr[i + k];
                    arr[i + k] = temp;
                    process = true;
                }
            }
        }


    }


}
