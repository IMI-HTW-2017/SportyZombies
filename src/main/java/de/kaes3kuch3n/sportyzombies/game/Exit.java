package de.kaes3kuch3n.sportyzombies.game;

public class Exit {

    private String name;
    private Room leadsTo;

    Exit(String name, Room leadsTo) {
        this.name = name;
        this.leadsTo = leadsTo;
    }

    public String getName() {
        return name;
    }

    public Room getLeadsTo() {
        return leadsTo;
    }
}
