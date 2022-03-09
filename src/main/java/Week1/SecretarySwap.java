package Week1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class SecretarySwap {
    public static void main(String[] args) throws IOException {
        int[] readArgs = myReader(new FileInputStream("input.txt"));
        int n = readArgs[0];
        Citizen[] array = getArray(readArgs);
        sort(array, "insertionSort");
        Arrays.stream(array).forEach(i -> System.out.println(i.toString()));
        write("output.txt",
                removeOutSymbols(findSwapIndexes(array)) + "\n");

    }

    public static void sort(Citizen[] array, String name){
        Sorting sorting = SortingFactory.getSorting(name).
                orElseThrow(IllegalArgumentException::new);
        sorting.sort(array);
    }

    public static String findSwapIndexes(Citizen[] array){
        String ans = "";
        for (int i = 0; i < array.length - 1; i++){
            if(array[i].getId() != i + 1){
                int tmp_ind = array[i].getId();
                swap(array, i, array[i].getId() - 1);
                int sum_ind = array[i].getId() + tmp_ind;
                if(tmp_ind < array[i].getId()){
                    tmp_ind = array[i].getId();
                }
                ans += "Swap elements at indices " + (sum_ind - tmp_ind) + " and " + tmp_ind + ".\n";
                i--;
            }
        }
        ans += "No more swaps needed.";
        return ans;
    }

    public static int[] findIdOfMostPoorMiddleAndRich(Citizen[] array){
        int[] ans = new int[3];
        ans[0] = array[0].getId();
        ans[1] = array[array.length / 2].getId();
        ans[2] = array[array.length - 1].getId();
        return ans;
    }

    public static String removeOutSymbols(String line){
        return line.replaceAll("\\[", "").replaceAll("]", "")
                .replaceAll(",", "");
    }

    public static Citizen[] getArray(int[] array){
        Citizen[] buffer = new Citizen[array.length - 1];
        for (int i = 1; i < array.length; i++){
            buffer[i - 1] = new Citizen(array[i], i);
        }
        return buffer;
    }

    public static int[] myReader(InputStream in){
        Scanner scanner = new Scanner(in);
        scanner.useLocale(Locale.UK);
        int n = scanner.nextInt();
        scanner.nextLine();
        int[] args = new int[n + 1];
        args[0] = n;
        for(int i = 1; i <= n; i++){
            args[i] = scanner.nextInt();
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

    public static void swap(Citizen[] array, int i, int j){
        Citizen buffer = array[i];
        array[i] = array[j];
        array[j] = buffer;
    }

    public static class Citizen{
        private int prosperity;
        private int id;

        public Citizen(final int prosperity,
                       final int id){
            this.prosperity = prosperity;
            this.id = id;
        }

        public int getProsperity() {
            return prosperity;
        }

        public void setProsperity(int prosperity) {
            this.prosperity = prosperity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Citizen{" +
                    "prosperity=" + prosperity +
                    ", id=" + id +
                    '}';
        }
    }

    public interface Sorting {
        void sort(Citizen[] array);
    }

    public static class InsertionSorting implements Sorting {

        @Override
        public void sort(Citizen[] array) {
            for(int i = 1; i < array.length; i++){
                int j = i - 1;
                while (j >= 0 && array[j].getProsperity() > array[j + 1].getProsperity()){
                    swap(array, j, j + 1);
                    j--;
                }
            }
        }
    }
}
