package Week4;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class PostfixExam {

    static int n;

    public static void main(String[] args) throws IOException {
         write(postfix(new FileInputStream(add() + "input.txt")));
    }


    public static String postfix(InputStream inputStream)
    {
        Scanner scanner = new Scanner(inputStream);
        n = scanner.nextInt();
        Stack<Integer> stack = new Stack<>();
        scanner.nextLine();
        for(int i = 0; i < n; i++)
        {
            if(scanner.hasNextInt()){
                stack.push(scanner.nextInt());
            }
            else{
                int a = stack.pop();
                int b = stack.pop();
                char c = scanner.next().charAt(0);
                switch (c) {
                    case ('+'):
                        stack.push(a + b);
                        break;
                    case ('-'):
                        stack.push(b - a);
                        break;
                    case ('/'):
                        stack.push(b / a);
                        break;
                    case ('*'):
                        stack.push(a * b);
                        break;
                }
            }
        }
        return stack.pop().toString();
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
