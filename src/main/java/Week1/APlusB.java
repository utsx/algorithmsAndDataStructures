package Week1;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class APlusB {
    public static void main(String[] args) throws FileNotFoundException {
        int[] readArgs = myReader(new FileInputStream("input.txt"));
        try {
            write("output.txt",
                    String.valueOf(calculate(readArgs[0], readArgs[1], "add")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class CommandsFactory{
        static Map<String, Command> commandMap = new HashMap<>();

        static {
            commandMap.put("add", new AddCommand());
            commandMap.put("squareAdd", new SquareAddCommand());
        }

        public static Optional<Command> getCommand(String command){
            return Optional.ofNullable(commandMap.get(command));
        }
    }

    public static int calculate(int a, int b, String command){
        Command targetCommand = CommandsFactory.getCommand(command).orElseThrow(
                IllegalArgumentException::new);
        return targetCommand.execute(a, b);
    }

    public static int[] myReader(InputStream in){
        Scanner scanner = new Scanner(in);
        int[] args = new int[2];
        args[0] = scanner.nextInt();
        args[1] = scanner.nextInt();
        return args;
    }

    public static void write(String name, String result) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        fileWriter.write(result);
        fileWriter.flush();
    }

    public interface Command{
        public int execute(int a, int b);
    }

    public static class AddCommand implements Command{
        @Override
        public int execute(int a, int b) {
            return a + b;
        }
    }
    public static class SquareAddCommand implements Command{

        @Override
        public int execute(int a, int b) {
            return (a + b) * (a + b);
        }
    }

}
