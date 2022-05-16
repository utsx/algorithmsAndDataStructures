package Week4;

import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class BracketSequence {

    static int n;
    public static void main(String[] args) throws IOException {
        read(new FileInputStream(add() + "input.txt"));
    }

    public static String add(){
        String line = "";
        //line += "src/main/resources/";
        return line;
    }

    public static void write(String result, FileWriter fw) throws IOException {
        fw.write(result);
    }

    public static boolean checkBracket(String bracketSequence) {
        Stack<Character> stack = new Stack<>();
        stack.push(bracketSequence.charAt(0));
        for (int i = 1; i < bracketSequence.length(); i++)
        {
            if (bracketSequence.charAt(i) == '(' || bracketSequence.charAt(i) == '[')
            {
                stack.push(bracketSequence.charAt(i));
            }
            if (bracketSequence.charAt(i) == ')')
            {
                if (stack.size() != 0 && stack.peek() == '(')
                {
                    stack.pop();
                }
                else
                {
                    return false;
                }
            }
            if (bracketSequence.charAt(i) == ']')
            {
                if (stack.size() != 0 && stack.peek() == '[')
                {
                    stack.pop();
                }
                else
                {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public static void read(InputStream in) throws IOException {
        Scanner scanner = new Scanner(in);
        n = scanner.nextInt();
        scanner.nextLine();
        FileWriter fw = new FileWriter(add() + "output.txt");
        for (int i = 0; i < n; i++)
        {
            String line = scanner.nextLine();
            write(checkBracket(line) ? "YES\n" : "NO\n", fw);
        }
        fw.flush();
    }
}
