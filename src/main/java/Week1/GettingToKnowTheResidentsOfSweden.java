package Week1;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GettingToKnowTheResidentsOfSweden {
    public static void main(String[] args) throws IOException {
        double[] readArgs = myReader(new FileInputStream("src/main/resources/input.txt"));
        double n = readArgs[0];
        Citizen[] array = getArray(readArgs);
        sort(array, "insertionSort");
        Arrays.stream(array).forEach(i -> System.out.println(i.toString()));
        write("src/main/resources/output.txt",
                        removeOutSymbols(
                                Arrays.toString(findIdOfMostPoorMiddleAndRich(array))));

    }

    public static void sort(Citizen[] array, String name){
        Sorting sorting = SortingFactory.getSorting(name).
                orElseThrow(IllegalArgumentException::new);
        sorting.sort(array);
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

    public static Citizen[] getArray(double[] array){
        Citizen[] buffer = new Citizen[array.length - 1];
        for (int i = 1; i < array.length; i++){
            buffer[i - 1] = new Citizen(array[i], i);
        }
        return buffer;
    }

    public static double[] myReader(InputStream in){
        Scanner scanner = new Scanner(in);
        scanner.useLocale(Locale.UK);
        double n = scanner.nextDouble();
        scanner.nextLine();
        double[] args = new double[(int) n + 1];
        args[0] = n;
        for(int i = 1; i <= n; i++){
            args[i] = scanner.nextDouble();
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
        private double prosperity;
        private int id;

        public Citizen(final double prosperity,
                       final int id){
            this.prosperity = prosperity;
            this.id = id;
        }

        public double getProsperity() {
            return prosperity;
        }

        public void setProsperity(double prosperity) {
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
