package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Location {
    private String description;
    private HashMap<String, Location> exits;
    private HashSet<Item> items;

    Location(String description) {
        this.description = description;
        exits = new HashMap<>();
        items = new HashSet<>();
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

        StringBuilder sbItems = new StringBuilder();
        for (Item i : items) {
            sbItems.append(SportyZombies.getLanguageLoader().getLocalizedString(i.getDescription())).append("\n");
        }

        return getDescription() + "\n" + sbItems.toString() + sb.toString();
    }

    Location getNextLocation(String exit) {
        return exits.get(exit);
    }

    String[] getExits() {
        return exits.keySet().toArray(new String[0]);
    }

    boolean containsItem(String item) {
        for (Item i : items) {
            if (item.equalsIgnoreCase(SportyZombies.getLanguageLoader().getLocalizedString(i.getName())))
                return true;
        }
        return false;
    }

    Item getItem(String name) {
        return items.stream()
                    .filter(item -> SportyZombies.getLanguageLoader().getLocalizedString(item.getName()).equalsIgnoreCase(name))
                    .reduce((item, item2) -> item)
                    .orElse(null);
    }

    void addItems(Item... item) {
        items.addAll(Arrays.asList(item));
    }

    private String getDescription() {
        return SportyZombies.getLanguageLoader().getLocalizedString(description);
    }
}
