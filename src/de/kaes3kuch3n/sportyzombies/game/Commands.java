package de.kaes3kuch3n.sportyzombies.game;

public enum Commands {
    QUIT {
        @Override
        String getLocalizedName(Language language) {
            switch (language) {
                case EN:
                    return "quit";
                case DE:
                    return "beenden";
                default:
                    return "quit";
            }
        }
    },
    HELP {
        @Override
        String getLocalizedName(Language language) {
            switch (language) {
                case EN:
                    return "help";
                case DE:
                    return "hilfe";
                default:
                    return "help";
            }
        }
    },
    UNKNOWN {
        @Override
        String getLocalizedName(Language language) {
            return null;
        }
    };

    abstract String getLocalizedName(Language language);
}
