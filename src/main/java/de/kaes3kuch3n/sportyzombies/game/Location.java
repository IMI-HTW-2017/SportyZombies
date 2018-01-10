package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Location {
    private String description;
    private HashMap<String, Location> exits;

    Location(String description) {
        this.description = description;
        exits = new HashMap<>();
    }

    void addExit(String localizedNamePath, Location exit) {
        exits.put(localizedNamePath, exit);
    }

    String getLocationInfo() {
        StringBuilder sb = new StringBuilder(SportyZombies.getLanguageLoader().getLocalizedString("location.availableexits"));
        sb.append(" ");
        for (String exit : getExits()) {
            sb.append(SportyZombies.getLanguageLoader().getLocalizedString(exit)).append(", ");
        }
        sb.replace(sb.lastIndexOf(","), sb.length(), "");
        return getDescription() + "\n" + sb.toString();
    }

    Location getNextLocation(String exit) {
        return exits.get(exit);
    }

    HashSet<String> getLocalizedExits() {
        HashSet<String> localizedExits = new HashSet<>();
        for (String exit : getExits()) {
            localizedExits.add(SportyZombies.getLanguageLoader().getLocalizedString(exit));
        }
        return localizedExits;
    }

    private String getDescription() {
        return SportyZombies.getLanguageLoader().getLocalizedString(description);
    }

    String[] getExits() {
        return exits.keySet().toArray(new String[0]);
    }
}
