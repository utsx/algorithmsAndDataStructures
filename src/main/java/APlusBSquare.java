import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class APlusBSquare {
    public static void main(String[] args) throws IOException {
        long[] readArgs = myReader(new FileInputStream("input.txt"));
        write("output.txt",
                String.valueOf(calculate(readArgs[0], readArgs[1], "squareAdd")));
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

    public static long calculate(long a, long b, String command){
        Command targetCommand = CommandsFactory.getCommand(command).orElseThrow(
                IllegalArgumentException::new);
        return targetCommand.execute(a, b);
    }

    public static long[] myReader(InputStream in){
        Scanner scanner = new Scanner(in);
        long[] args = new long[2];
        args[0] = scanner.nextLong();
        args[1] = scanner.nextLong();
        return args;
    }

    public static void write(String name, String result) throws IOException {
        FileWriter fileWriter = new FileWriter(name);
        fileWriter.write(result);
        fileWriter.flush();
    }

    public interface Command{
        public long execute(long a, long b);
    }

    public static class AddCommand implements Command{
        @Override
        public long execute(long a, long b) {
            return a + b;
        }
    }
    public static class SquareAddCommand implements Command{

        @Override
        public long execute(long a, long b) {
            return a + b * b;
        }
    }

}
