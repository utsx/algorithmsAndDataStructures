package Week3;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class DigitalSort {

    static int n;
    static int k;
    static int m;
    static Word[] words;


    public static void main(String[] args) {
        read(System.in);
    }

    public static void read(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        n = scanner.nextInt();
        k = scanner.nextInt();
        m = scanner.nextInt();
        words = new Word[n];
        for (int i = 0; i < n; i++){
            words[i] = new Word(i + 1);
        }
        scanner.nextLine();
        for(int i = 0; i < n; i++){
            String line = scanner.nextLine();
            for(int j = 0; j < n; j++){
                words[j].addCharacter(line.charAt(j));
            }
        }
        System.out.println(Arrays.toString(words));
    }
}

class Word{

    String word;
    int index;

    public Word(int index){
        this.word = "";
        this.index = index;
    }

    public void addCharacter(Character character){
        word += character;
    }

    @Override
    public String toString() {
        return "Word{" +
                "word='" + word + '\'' +
                ", index=" + index +
                '}';
    }
}
