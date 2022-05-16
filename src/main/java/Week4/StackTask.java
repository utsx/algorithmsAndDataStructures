package Week4;

import java.io.*;
import java.util.*;

public class StackTask {

    static long n;


    public static void main(String[] args) throws IOException {
        StringBuilder ans = new StringBuilder();
        InputStream in = new FileInputStream(add() + "input.txt");
        Scanner scanner = new Scanner(in);
        n = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Integer> arrayList = new ArrayList<>();
        int count = 0;
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] myArgs = line.split(" ");
            if(Objects.equals(myArgs[0], "+")){
                count++;
                Integer buffer  = Integer.valueOf(myArgs[1]);
                arrayList.add(buffer);
            }
            else{
                ans.append(arrayList.get(count - 1)).append("\n");
                arrayList.remove(count - 1);
                count--;
            }
        }
        write(ans.toString());
    }

    public static String add(){
        String line = "";
        //line += "src/main/resources/";
        return line;
    }

    public static void write(String result) throws IOException {
        FileWriter fileWriter = new FileWriter(add() + "output.txt");
        fileWriter.write(result);
        fileWriter.flush();
    }

}
