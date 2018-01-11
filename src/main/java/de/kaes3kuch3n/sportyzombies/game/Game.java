package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;
import de.kaes3kuch3n.sportyzombies.io.InputReader;
import de.kaes3kuch3n.sportyzombies.io.Output;

public class Game {

    private InputReader in;
    private Output out;

    private Player player;

    private boolean running;

    public Game() {
        in = new InputReader();
        out = new Output();
        player = new Player();
        setup();
    }

    public void run() {
        showWelcomeMessage();
        running = true;

        while (running) {
            processInput(in.getInput());
        }

        showLeaveMessage();
    }

    private void setup() {
        Location soccerFieldN = new Location("location.locations.soccerfieldn.description");
        Location soccerFieldS = new Location("location.locations.soccerfields.description");
        Location trackN = new Location("location.locations.trackn.description");
        Location trackNW = new Location("location.locations.tracknw.description");
        Location trackNE = new Location("location.locations.trackne.description");
        Location trackS = new Location("location.locations.tracks.description");
        Location trackSW = new Location("location.locations.tracksw.description");
        Location trackSE = new Location("location.locations.trackse.description");

        soccerFieldN.addExit("location.locations.soccerfieldn.exits.north", trackN);
        soccerFieldN.addExit("location.locations.soccerfieldn.exits.south", soccerFieldS);
        soccerFieldN.addExit("location.locations.soccerfieldn.exits.west", trackNW);
        soccerFieldN.addExit("location.locations.soccerfieldn.exits.east", trackNE);

        soccerFieldS.addExit("location.locations.soccerfields.exits.north", soccerFieldN);
        soccerFieldS.addExit("location.locations.soccerfields.exits.south", trackS);
        soccerFieldS.addExit("location.locations.soccerfields.exits.west", trackSW);
        soccerFieldS.addExit("location.locations.soccerfields.exits.east", trackSE);

        trackN.addExit("location.locations.trackn.exits.south", soccerFieldN);
        trackN.addExit("location.locations.trackn.exits.west", trackNW);
        trackN.addExit("location.locations.trackn.exits.east", trackNE);

        trackNW.addExit("location.locations.tracknw.exits.north", trackN);
        trackNW.addExit("location.locations.tracknw.exits.south", trackSW);
        trackNW.addExit("location.locations.tracknw.exits.east", soccerFieldN);

        trackNE.addExit("location.locations.trackne.exits.north", trackN);
        trackNE.addExit("location.locations.trackne.exits.south", trackSE);
        trackNE.addExit("location.locations.trackne.exits.west", soccerFieldN);

        trackS.addExit("location.locations.tracks.exits.north", soccerFieldS);
        trackS.addExit("location.locations.tracks.exits.west", trackSW);
        trackS.addExit("location.locations.tracks.exits.east", trackSE);

        trackSW.addExit("location.locations.tracksw.exits.north", trackNW);
        trackSW.addExit("location.locations.tracksw.exits.south", trackS);
        trackSW.addExit("location.locations.tracksw.exits.west", soccerFieldS);

        trackSE.addExit("location.locations.trackse.exits.north", trackNE);
        trackSE.addExit("location.locations.trackse.exits.south", trackS);
        trackSE.addExit("location.locations.trackse.exits.west", soccerFieldS);

        player.setCurrentLocation(soccerFieldN);

        Item letter = new Item("items.letter.name", "items.letter.description", "items.letter.use");
        Item key = new Item("items.key.name", "items.key.description", "items.key.use");
        Item boulder = new Item("items.boulder.name", "items.boulder.description", "items.boulder.use", true, 50);
        Item door = new Item("items.door.name", "items.door.description", "items.door.use",false);

        key.setUseWith(door);

        soccerFieldN.addItems(letter, key, boulder);
        soccerFieldS.addItems(door);
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
            case USE:
                use(cmd.getArgs());
                break;
            case TAKE:
                take(cmd.getArgs()[0]);
                break;
            case UNKNOWN:
                showCommandInvalidMessage();
                break;
        }
    }

    private void go(String exit) {
        for (String e : player.getCurrentLocation().getExits()) {
            if (SportyZombies.getLanguageLoader().getLocalizedString(e).equalsIgnoreCase(exit)) {
                player.setCurrentLocation(player.getCurrentLocation().getNextLocation(e));
                out.write(player.getCurrentLocation().getLocationInfo());
                return;
            }
        }
        out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.cantgothere"));
    }

    //TODO: Fix differentiation between use and useWith, add error for 2nd item not available
    private void use(String[] args) {
        if (args.length == 0)
            out.write("game.use.what");
        else if (args.length == 1) {
            if (player.hasItem(args[0]))
                out.write(SportyZombies.getLanguageLoader().getLocalizedString(player.useItem(args[0])));
            else out.write(SportyZombies.getLanguageLoader().getLocalizedString("items.noitem"));
        } else if (args.length == 2 && Command.isUseWord(args[1]))
            out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.use.onwhat"));
        else if (args.length == 3 && Command.isUseWord(args[1])) {
            if (player.hasItem(args[0])) {
                if (player.getCurrentLocation().containsItem(args[2]))
                    out.write(SportyZombies.getLanguageLoader().getLocalizedString(player.useItemOnLocItem(args[0],
                            player.getCurrentLocation().getItem(args[2]))));
                else if (player.hasItem(args[2]))
                    out.write(SportyZombies.getLanguageLoader().getLocalizedString(player.useItemOnInvItem(args[0], args[2])));
            } else out.write(SportyZombies.getLanguageLoader().getLocalizedString("items.noitem"));
        }
    }

    private void take(String item) {
        if (player.getCurrentLocation().containsItem(item)) {
            Item i = player.getCurrentLocation().getItem(item);
            if (i.isTakeable() && player.pickupItem(i))
                out.write(SportyZombies.getLanguageLoader().getLocalizedString(i.getName()) + " " +
                        SportyZombies.getLanguageLoader().getLocalizedString("game.taken"));
            else
                out.write(SportyZombies.getLanguageLoader().getLocalizedString("game.overloaded"));
        }
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
