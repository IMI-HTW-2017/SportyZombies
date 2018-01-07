package de.kaes3kuch3n.sportyzombies.game;

enum Commands {
    QUIT("commands.quit"), HELP("commands.help"), GO("commands.go"), UNKNOWN(null);

    private final String localizedCommandNamePath;

    Commands(String localizedCommandNamePath) {
        this.localizedCommandNamePath = localizedCommandNamePath;
    }

    public String getLocalizedCommandNamePath() {
        return localizedCommandNamePath;
    }
}
