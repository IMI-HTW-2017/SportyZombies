package de.kaes3kuch3n.sportyzombies.game;

class Exit {

    private String name;
    private Location leadsTo;

    Exit(String name, Location leadsTo) {
        this.name = name;
        this.leadsTo = leadsTo;
    }

    public String getName() {
        return name;
    }

    public Location getLeadsTo() {
        return leadsTo;
    }
}
