package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;

import java.util.HashMap;

public class Room {

    private String[] description;
    private HashMap<String, Room> exits;

    Room(String[] description) {
        this.description = description;
        exits = new HashMap<>();
    }

    Room addExit(String name, Room exit) {
        exits.put(name, exit);
        return this;
    }

    String getRoomInfo() {
        StringBuilder sb;
        switch (SportyZombies.language) {
            case EN:
                sb = new StringBuilder("Available exits: ");
                break;
            case DE:
                sb = new StringBuilder("Verfügbare Ausgänge: ");
                break;
            default:
                sb = new StringBuilder("Available exits: ");
        }
        for (String exit : getExits()) {
            sb.append(exit).append(", ");
        }
        sb.replace(sb.lastIndexOf(","), sb.length(), "");
        return getDescription() + "\n" + sb.toString();
    }

    private String getDescription() {
        switch (SportyZombies.language) {
            case EN:
                return description[0];
            case DE:
                return description[1];
            default:
                return description[0];
        }
    }

    Room getRoom(String exit) {
        return exits.get(exit);
    }

    String[] getExits() {
        return exits.keySet().toArray(new String[0]);
    }
}
