package Week2;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NumberOfInversions {
    static InputStream in;

    static {
        try {
            in = new FileInputStream("src/main/resources/input.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter("src/main/resources/output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Long[] arguments = read();
        Long n = arguments[0];
        ArrayList<Long> array = createNumberArray(getArray(arguments));
    }

    public static ArrayList<Long> createNumberArray(ArrayList<Long> arrayList){
        ArrayList<Long> ans = new ArrayList<>();
        for (int i = 1; i < arrayList.size(); i++){
            ans.add(arrayList.get(i));
        }
        return ans;
    }

    public static Long[] read() {
        Scanner scanner = new Scanner(in);
        long n = scanner.nextLong();
        scanner.nextLine();
        Long[] args = new Long[Math.toIntExact(n + 1)];
        args[0] = n;
        for (int i = 1; i <= n; i++) {
            args[i] = scanner.nextLong();
        }
        return args;
    }


    public static ArrayList<Long> mergeSort(ArrayList<Long> a) {
        int n = a.size();
        if (a.size() == 1) {
            return a;
        }
        ArrayList<Long> l = getList(0, n / 2 - 1, a);
        ArrayList<Long> r = getList(n / 2, n - 1, a);
        l = mergeSort(l);
        r = mergeSort(r);
        return merge(l, r);
    }

    public static ArrayList<Long> getList(int start, int finish, ArrayList<Long> list){
        ArrayList<Long> buffer = new ArrayList<>();
        for (int i = start; i <= finish; i++){
            buffer.add(list.get(i));
        }
        return buffer;
    }


    public static void write(FileWriter fileWriter, String result) throws IOException {
        fileWriter.write(result);
    }

    public static ArrayList<Long> getArray(Long[] array) {
        return new ArrayList<>(Arrays.asList(array).subList(1, array.length));
    }

    public static ArrayList<Long> merge(ArrayList<Long> a, ArrayList<Long> b) {
        int i = 0, j = 0;
        ArrayList<Long> ans = new ArrayList<>();
        while (i < a.size()
                || j < b.size()) {
            if ((j == b.size())
                    || (i < a.size() && a.get(i) < b.get(j))) {
                ans.add(a.get(i));
                i++;
            } else {
                ans.add(b.get(j));
                j++;
            }
        }
        return ans;
    }


}
