import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class ExamLast {

    static String a;
    static String b;

    static PrintWriter wr;

    static String input = "input.txt";
    static String output = "output.txt";

    static long x = 9091;
    static long y = 99990001;

    public static void main(String[] args) throws IOException {

        Path path = Paths.get(input);
        Scanner sc = new Scanner(path).useLocale(Locale.US);
        b = sc.nextLine();
        a = sc.nextLine();

        wr = new PrintWriter(new FileWriter(output));


        ArrayList<Long> arrayList = new ArrayList<>(0);
        fillHashesArrayList(arrayList, a, b.length());

        long secondHash = hash(b);

        ArrayList<Integer> subIndex = new ArrayList<>(0);
        for (int i = 0; i < arrayList.size(); i++){
            if(arrayList.get(i) != secondHash){
                continue;
            }
            subIndex.add(i + 1);
        }

        wr.println(subIndex.size());
        for(int i = 0; i < subIndex.size(); i++){
            wr.print(subIndex.get(i) + " ");
        }
        wr.close();

    }

    public static long hash(String s){
        long ans = 0;
        for (int i = 0; i < s.length(); i++){
            ans *= x;
            ans += s.charAt(i);
            ans %= y;
        }
        return ans;
    }

    public static long modPaw(long basis, int power, long modulus){
        long ans = 1;
        for (int i = 0; i < power; i++){
            ans = (ans * basis) % modulus;
        }
        return ans;
    }

    public static void fillHashesArrayList(ArrayList<Long> arrayList, String s, int len){
        String firstPart = "";
        if(s.length() >= len) {
            firstPart = s.substring(0, len);
        }
        long maxXPower = modPaw(x, len, y);
        arrayList.add(hash(firstPart));
        for(int i = 1; i < s.length() - len + 1; i++){
            long value = arrayList.get(arrayList.size() - 1) * x - s.charAt(i - 1) * maxXPower + s.charAt(i + len - 1);
            arrayList.add((value % y + y) % y);
        }
    }
}

