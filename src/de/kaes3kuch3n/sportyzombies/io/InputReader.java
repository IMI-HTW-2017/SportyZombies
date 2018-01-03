package de.kaes3kuch3n.sportyzombies.io;

import de.kaes3kuch3n.sportyzombies.game.Command;

import java.util.Arrays;
import java.util.Scanner;

public class InputReader {

    private Scanner scanner;

    public InputReader() {
        scanner = new Scanner(System.in);
    }

    public Command getInput() {
        System.out.println("> ");
        String[] input = scanner.nextLine().trim().toLowerCase().split(" ");
        return new Command(input[0], Arrays.copyOfRange(input, 1, input.length));
    }

}
