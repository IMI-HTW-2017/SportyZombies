package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;
import de.kaes3kuch3n.sportyzombies.io.InputReader;
import de.kaes3kuch3n.sportyzombies.io.Output;

import java.util.Map;

public class Game {

    private InputReader in;
    private Output out;

    private boolean running;
    private Room currentRoom;

    public Game() {
        in = new InputReader();
        out = new Output();
        setupRooms();
    }

    public void run() {
        showWelcomeMessage();
        running = true;

        while (running) {
            processInput(in.getInput());
        }

        showLeaveMessage();
    }

    private void setupRooms() {
        Room soccerFieldN = new Room("room.rooms.soccerfieldn.description");
        Room soccerFieldS = new Room("room.rooms.soccerfields.description");
        Room trackN = new Room("room.rooms.trackn.description");
        Room trackNW = new Room("room.rooms.tracknw.description");
        Room trackNE = new Room("room.rooms.trackne.description");
        Room trackS = new Room("room.rooms.tracks.description");
        Room trackSW = new Room("room.rooms.tracksw.description");
        Room trackSE = new Room("room.rooms.trackse.description");

        soccerFieldN.addExit("north", "room.rooms.soccerfieldn.exits.north", trackN);
        soccerFieldN.addExit("south", "room.rooms.soccerfieldn.exits.south", soccerFieldS);
        soccerFieldN.addExit("west", "room.rooms.soccerfieldn.exits.west", trackNW);
        soccerFieldN.addExit("east", "room.rooms.soccerfieldn.exits.east", trackNE);

        soccerFieldS.addExit("north", "room.rooms.soccerfields.exits.north", soccerFieldN);
        soccerFieldS.addExit("south", "room.rooms.soccerfields.exits.south", trackS);
        soccerFieldS.addExit("west", "room.rooms.soccerfields.exits.west", trackSW);
        soccerFieldS.addExit("east", "room.rooms.soccerfields.exits.east", trackSE);

        trackN.addExit("south", "room.rooms.trackn.exits.south", soccerFieldN);
        trackN.addExit("west", "room.rooms.trackn.exits.west", trackNW);
        trackN.addExit("east", "room.rooms.trackn.exits.east", trackNE);

        trackNW.addExit("north", "room.rooms.tracknw.exits.north", trackN);
        trackNW.addExit("south", "room.rooms.tracknw.exits.south", trackSW);
        trackNW.addExit("east", "room.rooms.tracknw.exits.east", soccerFieldN);

        trackNE.addExit("north", "room.rooms.trackne.exits.north", trackN);
        trackNE.addExit("south", "room.rooms.trackne.exits.south", trackSE);
        trackNE.addExit("west", "room.rooms.trackne.exits.west", soccerFieldN);

        trackS.addExit("north", "room.rooms.tracks.exits.north", soccerFieldS);
        trackS.addExit("west", "room.rooms.tracks.exits.west", trackSW);
        trackS.addExit("east", "room.rooms.tracks.exits.east", trackSE);

        trackSW.addExit("north", "room.rooms.tracksw.exits.north", trackNW);
        trackSW.addExit("south", "room.rooms.tracksw.exits.south", trackS);
        trackSW.addExit("east", "room.rooms.tracksw.exits.west", soccerFieldS);

        trackSE.addExit("north", "room.rooms.trackse.exits.north", trackNE);
        trackSE.addExit("south", "room.rooms.trackse.exits.south", trackS);
        trackSE.addExit("west", "room.rooms.trackse.exits.west", soccerFieldS);

        currentRoom = soccerFieldN;
    }

    private void processInput(Command cmd) {
        switch (cmd.getCommand()) {
            case QUIT:
                running = false;
                break;
            case HELP:
                showHelpMessage();
                break;
            case GO:
                go(cmd.getArgs()[0]);
                break;
            case UNKNOWN:
                showCommandInvalidMessage();
                break;
        }
    }

    private void go(String exit) {
        for (Map.Entry e : currentRoom.getLocalizedExits().entrySet()) {
            if (e.getValue().toString().equalsIgnoreCase(exit)) {
                currentRoom = currentRoom.getNextRoom(e.getKey().toString());
                out.write(currentRoom.getRoomInfo());
                return;
            }
        }
        out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.cantgothere"));
    }

    private void showHelpMessage() {
        out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.helpmessage"));
    }

    private void showCommandInvalidMessage() {
        out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.commandinvalidmessage"));
    }

    private void showWelcomeMessage() {
        out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.welcomemessage"));
        out.write(currentRoom.getRoomInfo());
    }

    private void showLeaveMessage() {
        out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.leavemessage"));
    }

}
