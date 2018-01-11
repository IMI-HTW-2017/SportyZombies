package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;

import java.util.Arrays;
import java.util.HashSet;

public class Command {

    private static final HashSet<String> USEWORDS = new HashSet<>(Arrays.asList("commands.usewords.on", "commands.usewords.with"));

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

    public static boolean isUseWord(String word) {
        for (String s : USEWORDS) {
            if (SportyZombies.getLanguageLoader().getLocalizedString(s).equalsIgnoreCase(word))
                return true;
        }
        return false;
    }
}
