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
            if (command.equals(cmd.getLocalizedName(SportyZombies.language))) {
                return cmd;
            }
        }
        return Commands.UNKNOWN;
    }

    public Commands getCommand() {
        return command;
    }

    public String[] getArgs() {
        return args;
    }
}
