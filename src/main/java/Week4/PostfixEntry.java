package Week4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.Stack;

public class PostfixEntry {

    static Stack<Integer> stack = new Stack();
    static int n;

    public static void main(String[] args) throws FileNotFoundException {
        InputStream in = new FileInputStream("src/main/resources/input.txt");
        Scanner scanner = new Scanner(in);
        n = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < n; i++){
            if(scanner.hasNextInt()){
                stack.push(scanner.nextInt());
            }
            else
            {
                int a = stack.pop();
                int b = stack.pop();
                char c = scanner.next().charAt(0);
                switch (c) {
                    case ('+') -> stack.push(a + b);
                    case ('-') -> stack.push(b - a);
                    case ('/') -> stack.push(b / a);
                    case ('*') -> stack.push(a * b);
                }
            }
        }
        System.out.println(stack.pop());
    }
}
