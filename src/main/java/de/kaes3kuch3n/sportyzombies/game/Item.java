package de.kaes3kuch3n.sportyzombies.game;

import de.kaes3kuch3n.sportyzombies.SportyZombies;

class Item {

    private String name;
    private String description;
    private String useMessage;
    private Item useWith;
    private int weight;
    private boolean takeable;

    Item(String name, String description, String useMessage, boolean takeable, int weight) {
        this.name = name;
        this.description = description;
        this.useMessage = useMessage;
        this.weight = weight;
        this.takeable = takeable;
    }

    Item(String name, String description, String useMessage, boolean takeable) {
        this.name = name;
        this.description = description;
        this.useMessage = useMessage;
        this.weight = 1;
        this.takeable = takeable;
    }

    Item(String name, String description, String useMessage) {
        this.name = name;
        this.description = description;
        this.useMessage = useMessage;
        this.weight = 1;
        this.takeable = true;
    }

    void setUseWith(Item item) {
        this.useWith = item;
    }

    String getName() {
        return name;
    }

    String use() {
        return useMessage;
    }

    String useWith(Item item) {
        if (item.equals(useWith)) {
            return useMessage;
        }
        return "game.use.invaliditem";
    }

    int getWeight() {
        return weight;
    }

    boolean isTakeable() {
        return takeable;
    }

    public String getDescription() {
        return description;
    }
}
