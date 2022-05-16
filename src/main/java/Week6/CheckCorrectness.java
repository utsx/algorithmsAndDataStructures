//package Week6;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class CheckCorrectness {

    static int n;
    static Node[] tree;

    public static class Node{
        private int k;
        private int l;
        private int r;

        public Node(final int k, final int l,
                   final int r){
            this.k = k;
            this.l = l;
            this.r = r;
        }

        public int getK() {
            return k;
        }

        public void setK(final int k)
        {
            this.k = k;
        }

        public int getL() {
            return l;
        }

        public void setL(final int l) {
            this.l = l;
        }

        public void setR(final int r) {
            this.r = r;
        }

        public int getR() {
            return r;
        }

        @Override
        public String toString() {
            return "Node{" + this.k + " " +
                    this.l + " " + this.r + "}";
        }
    }

    public static String add(){
        //return "src/main/resources/";
        return "";
    }

    public static void main(String[] args) throws IOException {
        read(new FileInputStream(add() + "input.txt"));
        write();
    }

    public static void write() throws IOException {
        FileWriter fileWriter = new FileWriter(add() + "output.txt");
        String ans = "";
        if(n == 0){
            ans = "YES";
        }
        else{
            if(isCorrect(1)){
                ans = "YES";
            }
            else{
                ans = "NO";
            }
        }
        fileWriter.write(ans);
        fileWriter.flush();

    }

    public static boolean isCorrect(int i){
        Node currentNode = tree[i];

        boolean left = true;
        boolean right = true;

        if(currentNode.getL() != 0){
            if(maxKey(currentNode.getL()) >= currentNode.getK()){
                return false;
            }
            left = isCorrect(currentNode.getL());
        }
        if(currentNode.getR() != 0){
            if(minKey(currentNode.getR()) <= currentNode.getK()){
                return false;
            }
            right = isCorrect(currentNode.getR());
        }
        return (left && right);
    }

    public static long maxKey(int i){
        if(tree[i].getR() != 0){
            return maxKey(tree[i].getR());
        }
        return tree[i].getK();
    }

    public static long minKey(int i){
        if(tree[i].getL() != 0){
            return minKey(tree[i].getL());
        }
        return tree[i].getK();
    }

    public static void read(InputStream in){
        Scanner scanner = new Scanner(in);
        n = scanner.nextInt();
        tree = new Node[n + 1];
        for(int i = 1; i <= n; i++){
            tree[i] = new Node(
                    scanner.nextInt(),
                    scanner.nextInt(),
                    scanner.nextInt());
        }
    }
}
