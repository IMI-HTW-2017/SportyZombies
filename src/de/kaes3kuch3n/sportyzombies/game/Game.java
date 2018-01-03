package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;
import de.kaes3kuch3n.sportyzombies.io.InputReader;
import de.kaes3kuch3n.sportyzombies.io.Output;

public class Game {

    private InputReader in;
    private Output out;

    private boolean running;
    private Room currentRoom;

    public Game() {
        in = new InputReader();
        out = new Output();
    }

    public void run() {
        setup();
        showWelcomeMessage();
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
            case GO:
                go(input.getArgs()[0]);
                break;
            case UNKNOWN:
                commandInvalid();
                break;
        }
    }

    private void setup() {
        Room entrance = new Room(new String[] {
                "You are standing at the entrance of an abandoned theatre.",
                "Du stehst am Eingang eines verlassenen Theaters."
        });
        Room cashPoint = new Room(new String[] {
                "You are standing at the cash point. An old poster of the musical Cats is hanging behind the counter.",
                "Du stehst an der Kasse. Ein altes Poster vom Musical Cats hängt hinter dem Tresen."
        });
        Room popcornStand = new Room(new String[] {
                "You are now at the old popcorn stand. There is still popcorn left in one of the boxes.",
                "Du bist nun am Popcorn-Stand. Es ist immer noch etwas Popcorn in einer der Boxen."
        });

        entrance.addExit("inside", cashPoint);
        cashPoint.addExit("outside", entrance)
                 .addExit("forwards", popcornStand);
        popcornStand.addExit("back", cashPoint);

        currentRoom = entrance;
    }

    private void go(String exit) {
        for (String e : currentRoom.getExits()) {
            if (e.equals(exit)) {
                currentRoom = currentRoom.getRoom(exit);
                out.write(currentRoom.getRoomInfo());
            }
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

    private void showWelcomeMessage() {
        switch (SportyZombies.language) {
            case EN:
                out.write("Welcome to SportyZombies!\nGood luck and have fun!\n");
                break;
            case DE:
                out.write("Willkommen zu SportyZombies!\nViel Glück und viel Spaß!\n");
                break;
        }
        out.write(currentRoom.getRoomInfo());
    }

    private void showExitMessage() {
        switch (SportyZombies.language) {
            case EN:
                out.write("\nThank you for playing.\nGoodbye!");
                break;
            case DE:
                out.write("\nVielen Dank für's Spielen.\nAuf Wiedersehen!");
                break;
        }
    }

}
