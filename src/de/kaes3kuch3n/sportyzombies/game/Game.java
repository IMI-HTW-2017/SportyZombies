package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;
import de.kaes3kuch3n.sportyzombies.io.InputReader;
import de.kaes3kuch3n.sportyzombies.io.Output;

public class Game {

    private InputReader in;
    private Output out;

    private boolean running;

    public Game() {
        in = new InputReader();
        out = new Output();
    }

    public void run() {
        running = true;

        while (running) {
            processInput(in.getInput());
        }
        showExitMessage();
    }

    private void processInput(Command input) {
        switch (input.getCommand()) {
            case QUIT:
                running = false;
                break;
            case HELP:
                showHelp();
                break;
            case UNKNOWN:
                commandInvalid();
                break;
        }
    }

    private void showHelp() {
        switch (SportyZombies.language) {
            case EN:
                out.write("English help text!");
                break;
            case DE:
                out.write("Deutscher Hilfe-Text!");
                break;
        }
    }

    private void commandInvalid() {
        switch (SportyZombies.language) {
            case EN:
                out.write("I don't understand what you want.");
                break;
            case DE:
                out.write("Ich verstehe nicht, was Du willst.");
                break;
        }
    }

    private void showExitMessage() {
        switch (SportyZombies.language) {
            case EN:
                out.write("Thank you for playing.\nGoodbye!");
                break;
            case DE:
                out.write("Vielen Dank für's Spielen.\nAuf Wiedersehen!");
                break;
        }
    }

}
