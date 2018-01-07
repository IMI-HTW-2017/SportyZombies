package de.kaes3kuch3n.sportyzombies.game;

import java.util.HashSet;

class Player {

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
}
