package Week2;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MergeSort {

    static  FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter("output.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Long[] arguments = read();
        Long n = arguments[0];
        ArrayList<Number> array = createNumberArray(getArray(arguments));
        write(fileWriter,
                mergeSort(array, 0).stream()
                        .map(String::valueOf).collect(Collectors.joining(" ")));
        fileWriter.flush();
    }


    public static class Number{

        private long id;
        private final Long value;

        public Number(final long id,
                      final Long value){
            this.id = id;
            this.value = value;
        }

        public void swapId(Number number){
            long buffer = number.getId();
            number.setId(this.id);
            this.id = buffer;
        }

        public void setId(long id){
            this.id = id;
        }

        public Long getValue() {
            return value;
        }

        public long getId() {
            return id;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    public static ArrayList<Number> createNumberArray(ArrayList<Long> arrayList){
        ArrayList<Number> ans = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++){
            ans.add(new Number(i + 1,
                    arrayList.get(i)));
        }
        return ans;
    }

    public static Long[] read() throws FileNotFoundException {
        InputStream in = new FileInputStream("input.txt");
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

    public static void write(FileWriter fileWriter, String result) throws IOException {
        fileWriter.write(result);
    }

    public static ArrayList<Long> getArray(Long[] array) {
        return new ArrayList<>(Arrays.asList(array).subList(1, array.length));
    }

    public static ArrayList<Number> mergeSort(ArrayList<Number> a, long start) {
        int n = a.size();
        if (a.size() == 1) {
            return a;
        }
        ArrayList<Number> l = getList(0, n / 2 - 1, a);
        ArrayList<Number> r = getList(n / 2, n - 1, a);
        l = mergeSort(l, start);
        r = mergeSort(r, start + l.size());
        return merge(l, r, start);
    }

    public static ArrayList<Number> getList(int start, int finish, ArrayList<Number> list){
        ArrayList<Number> buffer = new ArrayList<>();
        for (int i = start; i <= finish; i++){
            buffer.add(list.get(i));
        }
        return buffer;
    }

    public static ArrayList<Number> merge(ArrayList<Number> a, ArrayList<Number> b, long start) {
        int i = 0, j = 0;
        ArrayList<Number> ans = new ArrayList<>();
        while (i < a.size()
                || j < b.size()) {
            if ((j == b.size())
                    || (i < a.size() && a.get(i).getValue() < b.get(j).getValue())) {
                ans.add(a.get(i));
                i++;
            } else {
                ans.add(b.get(j));
                j++;
            }
        }
        try {
            write(fileWriter, start + 1 + " " + (start + ans.size()) +
                    " " + ans.get(0).getValue() + " " + ans.get(ans.size() - 1).getValue() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    }
