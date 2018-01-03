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
        Room soccerFieldN = new Room(new String[] {
                "You are standing at the northern part of the soccer field",
                "Du stehst auf der nördlichen Hälfte des Fußballfeldes."
        });
        Room soccerFieldS = new Room(new String[] {
                "You are standing at the southern part of the soccer field",
                "Du stehst auf der südlichen Hälfte des Fußballfeldes."
        });
        Room trackN = new Room(new String[] {
                "You are standing at the northern part of the running track",
                "Du stehst auf der nördlichen Abschnitt der Tartanbahn."
        });
        Room trackS = new Room(new String[] {
                "You are standing at the southern part of the running track",
                "Du stehst auf dem südlichen Abschnitt der Tartanbahn."
        });
        Room trackNW = new Room(new String[] {
                "You are standing at the northwestern part of the running track",
                "Du stehst auf dem nordwestlichen Abschnitt der Tartanbahn."
        });
        Room trackNE = new Room(new String[] {
                "You are standing at the northeastern part of the running track",
                "Du stehst auf dem nordöstlichen Abschnitt der Tartanbahn."
        });
        Room trackSW = new Room(new String[] {
                "You are standing at the southwestern part of the running track",
                "Du stehst auf dem südwestlichen Abschnitt der Tartanbahn."
        });
        Room trackSE = new Room(new String[] {
                "You are standing at the southeastern part of the running track",
                "Du stehst auf dem südöstlichen Abschnitt der Tartanbahn."
        });

        soccerFieldN.addExit("north", trackN)
                    .addExit("south", soccerFieldS)
                    .addExit("west", trackNW)
                    .addExit("east", trackNE);
        soccerFieldS.addExit("north", soccerFieldN)
                    .addExit("south", trackS)
                    .addExit("west", trackSW)
                    .addExit("east", trackSE);
        trackN.addExit("south", soccerFieldN)
              .addExit("west", trackNW)
              .addExit("east", trackNE);
        trackNW.addExit("south", trackSW)
                .addExit("east", soccerFieldN);
        trackNE.addExit("south", trackSE)
                .addExit("west", soccerFieldN);
        trackS.addExit("north", soccerFieldS)
                .addExit("west", trackSW)
                .addExit("east", trackSE);
        trackSW.addExit("north", trackNW)
                .addExit("east", soccerFieldS);
        trackSE.addExit("north", trackNE)
                .addExit("west", soccerFieldS);

        currentRoom = soccerFieldN;
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
