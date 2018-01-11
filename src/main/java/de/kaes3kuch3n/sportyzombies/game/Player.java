package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;

import java.util.HashSet;

class Player {

    private static int MAX_CARRY_WEIGHT = 20;

    private Location currentLocation;
    private HashSet<Item> inventory;
    private int health;

    Player() {
        this.health = 100;
        this.inventory = new HashSet<>();
    }

    void setCurrentLocation(Location location) {
        this.currentLocation = location;
    }

    Location getCurrentLocation() {
        return currentLocation;
    }

    String useItem(String item) {
        return getItemFromInventory(item).use();
    }

    String useItemOnLocItem(String item, Item locItem) {
        return getItemFromInventory(item).useWith(locItem);
    }

    String useItemOnInvItem(String item, String invItem) {
        return getItemFromInventory(item).useWith(getItemFromInventory(invItem));
    }

    boolean hasItem(String name) {
        for (Item i : inventory) {
            if (SportyZombies.getLanguageLoader().getLocalizedString(i.getName()).equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    boolean pickupItem(Item item) {
        if (item.getWeight() + getCarryWeight() > MAX_CARRY_WEIGHT)
            return false;
        else {
            inventory.add(item);
            return true;
        }
    }

    private int getCarryWeight() {
        return inventory.stream().map(Item::getWeight).reduce(0, Integer::sum);
    }

    private Item getItemFromInventory(String itemName) {
        return inventory.stream()
                        .filter(item -> SportyZombies.getLanguageLoader().getLocalizedString(item.getName()).equalsIgnoreCase(itemName))
                        .reduce((item, item2) -> item)
                        .orElse(null);
    }
}
