package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;

import java.util.HashMap;
import java.util.Map;

class Location {
    private String description;
    private HashMap<String, Exit> exits;

    Location(String description) {
        this.description = description;
        exits = new HashMap<>();
    }

    void addExit(String name, String localizedNamePath, Location exit) {
        exits.put(name, new Exit(localizedNamePath, exit));
    }

    String getLocationInfo() {
        StringBuilder sb = new StringBuilder(SportyZombies.getLanguageLoader().getLocalizedString("location.availableexits"));
        sb.append(" ");
        for (String exit : getExits()) {
            sb.append(SportyZombies.getLanguageLoader().getLocalizedString(exits.get(exit).getName())).append(", ");
        }
        sb.replace(sb.lastIndexOf(","), sb.length(), "");
        return getDescription() + "\n" + sb.toString();
    }

    Location getNextLocation(String exit) {
        return exits.get(exit).getLeadsTo();
    }

    HashMap<String, String> getLocalizedExits() {
        HashMap<String, String> localizedExits = new HashMap<>();
        for (Map.Entry exit : exits.entrySet()) {
            Exit e = (Exit)exit.getValue();
            localizedExits.put(exit.getKey().toString(), SportyZombies.getLanguageLoader().getLocalizedString(e.getName()));
        }
        return localizedExits;
    }

    private String getDescription() {
        return SportyZombies.getLanguageLoader().getLocalizedString(description);
    }

    private String[] getExits() {
        return exits.keySet().toArray(new String[0]);
    }
}
