package Week9;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class FastSearchSubStringInString {

    static String a;
    static String b;

    static PrintWriter wr;

    static long x = 9091;
    static long p = 99990001;

    public static void main(String[] args) throws IOException {
        read(new FileInputStream(add() + "input.txt"));
        wr = new PrintWriter(new FileWriter(add() + "output.txt"));


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

    public static void read(InputStream in) throws FileNotFoundException {
        Scanner scanner = new Scanner(in);
        b = scanner.nextLine();
        a = scanner.nextLine();
    }

    public static void write(FileWriter fileWriter, String result) throws IOException {
        fileWriter.write(result);
    }

    public static long hash(String s){
        long ans = 0;
        for (int i = 0; i < s.length(); i++){
            ans *= x;
            ans += s.charAt(i);
            ans %= p;
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
        long maxXPower = modPaw(x, len, p);
        arrayList.add(hash(firstPart));
        for(int i = 1; i < s.length() - len + 1; i++){
            long value = arrayList.get(arrayList.size() - 1) * x - s.charAt(i - 1) * maxXPower + s.charAt(i + len - 1);
            arrayList.add((value % p + p) % p);
        }
    }

    public static String add(){
        String line = "";
        //line += "src/main/resources/";
        return line;
    }
}

