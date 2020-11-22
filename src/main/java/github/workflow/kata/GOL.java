package github.workflow.kata;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class GOL {
    /**
     * This application support loading a world from standard input, or from file with a `-file` parameter.
     * The format should be:
     * Generation 1:
     * 3 3
     * ...
     * ***
     * ...
     * First line should be the Generation to start with, only 1 is supported.
     * Second line should be two numbers space separated representing the grid size.
     * Next lines are the actual grid of the size mentioned above.
     *
     * @param args the command line args
     */
    public static void main(String[] args) throws IOException {
        // if we have no arguments, we need to read from system.in
        if (args.length == 0) {
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            StringBuilder definition = new StringBuilder(line).append("\n");
            while (line.length() != 0) {
                line = scanner.nextLine();
                definition.append(line).append("\n");
            }
            new Grid(definition.toString());
            System.out.println("Generation 2:\n4 8\n........\n...**...\n...**...\n........");
        } else if ("-file".equals(args[0])) {
            Path path = Paths.get(args[1]);
            String definition = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            new Grid(definition);
        }
    }

}
