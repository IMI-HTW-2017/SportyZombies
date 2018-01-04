package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;

public class Command {

    private Commands command;
    private String[] args;

    public Command(String command, String[] args) {
        this.command = stringToCommand(command);
        this.args = args;
    }

    private Commands stringToCommand(String command) {
        for (Commands cmd : Commands.values()) {
            if (cmd.equals(Commands.UNKNOWN)) continue;
            if (command.equalsIgnoreCase(SportyZombies.getLanguageLoader().getLocalizedString(cmd.getLocalizedCommandNamePath()))) {
                return cmd;
            }
        }
        return Commands.UNKNOWN;
    }

    Commands getCommand() {
        return command;
    }

    String[] getArgs() {
        return args;
    }

}
