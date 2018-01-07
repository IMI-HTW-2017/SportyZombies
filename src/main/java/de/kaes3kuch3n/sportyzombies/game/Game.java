package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;
import de.kaes3kuch3n.sportyzombies.io.InputReader;
import de.kaes3kuch3n.sportyzombies.io.Output;

import java.util.Map;

public class Game {

    private InputReader in;
    private Output out;

    private Player player;

    private boolean running;

    public Game() {
        in = new InputReader();
        out = new Output();
        player = new Player();
        setuplocations();
    }

    public void run() {
        showWelcomeMessage();
        running = true;

        while (running) {
            processInput(in.getInput());
        }

        showLeaveMessage();
    }

    private void setuplocations() {
        Location soccerFieldN = new Location("location.locations.soccerfieldn.description");
        Location soccerFieldS = new Location("location.locations.soccerfields.description");
        Location trackN = new Location("location.locations.trackn.description");
        Location trackNW = new Location("location.locations.tracknw.description");
        Location trackNE = new Location("location.locations.trackne.description");
        Location trackS = new Location("location.locations.tracks.description");
        Location trackSW = new Location("location.locations.tracksw.description");
        Location trackSE = new Location("location.locations.trackse.description");

        soccerFieldN.addExit("north", "location.locations.soccerfieldn.exits.north", trackN);
        soccerFieldN.addExit("south", "location.locations.soccerfieldn.exits.south", soccerFieldS);
        soccerFieldN.addExit("west", "location.locations.soccerfieldn.exits.west", trackNW);
        soccerFieldN.addExit("east", "location.locations.soccerfieldn.exits.east", trackNE);

        soccerFieldS.addExit("north", "location.locations.soccerfields.exits.north", soccerFieldN);
        soccerFieldS.addExit("south", "location.locations.soccerfields.exits.south", trackS);
        soccerFieldS.addExit("west", "location.locations.soccerfields.exits.west", trackSW);
        soccerFieldS.addExit("east", "location.locations.soccerfields.exits.east", trackSE);

        trackN.addExit("south", "location.locations.trackn.exits.south", soccerFieldN);
        trackN.addExit("west", "location.locations.trackn.exits.west", trackNW);
        trackN.addExit("east", "location.locations.trackn.exits.east", trackNE);

        trackNW.addExit("north", "location.locations.tracknw.exits.north", trackN);
        trackNW.addExit("south", "location.locations.tracknw.exits.south", trackSW);
        trackNW.addExit("east", "location.locations.tracknw.exits.east", soccerFieldN);

        trackNE.addExit("north", "location.locations.trackne.exits.north", trackN);
        trackNE.addExit("south", "location.locations.trackne.exits.south", trackSE);
        trackNE.addExit("west", "location.locations.trackne.exits.west", soccerFieldN);

        trackS.addExit("north", "location.locations.tracks.exits.north", soccerFieldS);
        trackS.addExit("west", "location.locations.tracks.exits.west", trackSW);
        trackS.addExit("east", "location.locations.tracks.exits.east", trackSE);

        trackSW.addExit("north", "location.locations.tracksw.exits.north", trackNW);
        trackSW.addExit("south", "location.locations.tracksw.exits.south", trackS);
        trackSW.addExit("east", "location.locations.tracksw.exits.west", soccerFieldS);

        trackSE.addExit("north", "location.locations.trackse.exits.north", trackNE);
        trackSE.addExit("south", "location.locations.trackse.exits.south", trackS);
        trackSE.addExit("west", "location.locations.trackse.exits.west", soccerFieldS);

        player.setCurrentLocation(soccerFieldN);
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
        for (Map.Entry e : player.getCurrentLocation().getLocalizedExits().entrySet()) {
            if (e.getValue().toString().equalsIgnoreCase(exit)) {
                player.setCurrentLocation(player.getCurrentLocation().getNextLocation(e.getKey().toString()));
                out.write(player.getCurrentLocation().getLocationInfo());
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
        out.write(player.getCurrentLocation().getLocationInfo());
    }

    private void showLeaveMessage() {
        out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.leavemessage"));
    }

}
