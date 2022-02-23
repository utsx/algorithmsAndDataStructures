import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class InsertionSort {
    public static void main(String[] args) throws IOException {
        long[] readArgs = myReader(new FileInputStream("input.txt"));
        long n = readArgs[0];
        long[] array = getArray(readArgs);
        write("output.txt",
                removeOutSymbols(Arrays.toString(sort(array, "insertionSort")))
                        + "\n" + removeOutSymbols(Arrays.toString(array)));

    }

    public static long[] sort(long[] array, String name){
        Sorting sorting = SortingFactory.getSorting(name).
                orElseThrow(IllegalArgumentException::new);
        return sorting.sort(array);
    }

    public static String removeOutSymbols(String line){
        return line.replaceAll("\\[", "").replaceAll("]", "")
                .replaceAll(",", "");
    }

    public static long[] getArray(long[] array){
        long[] buffer = new long[array.length - 1];
        System.arraycopy(array, 1, buffer, 0, array.length - 1);
        return buffer;
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

    public static void write(String name, String result) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        fileWriter.write(result);
        fileWriter.flush();
    }

    public static class SortingFactory {
        static Map<String, Sorting> sortingMap = new HashMap<>();

        static {
            sortingMap.put("insertionSort", new InsertionSorting());
        }

        public static Optional<Sorting> getSorting(String name){
            return Optional.ofNullable(sortingMap.get(name));
        }
    }

    public static void swap(long[] array, int i, int j){
        long buffer = array[i];
        array[i] = array[j];
        array[j] = buffer;
    }


    public interface Sorting {
        long[] sort(long[] array);
    }

    public static class InsertionSorting implements Sorting {

        @Override
        public long[] sort(long[] array) {
            long[] indexes = new long[array.length];
            indexes[0] = 1;
            for(int i = 1; i < array.length; i++){
               int j = i - 1;
               while (j >= 0 && array[j] > array[j + 1]){
                   swap(array, j, j + 1);
                   j--;
               }
               indexes[i] = j + 2;
            }
            return indexes;
        }
    }
}
