package de.kaes3kuch3n.sportyzombies;

import de.kaes3kuch3n.sportyzombies.game.Game;
import de.kaes3kuch3n.sportyzombies.game.Language;

public class SportyZombies {

    public static Language language;

    public static void main(String[] args) {

        if (args.length == 0) {
            language = Language.EN;
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "en":
                    language = Language.EN;
                    break;
                case "de":
                    language = Language.DE;
                    break;
                default:
                    System.out.println("Language not recognized, using English.");
                    language = Language.EN;
                    break;
            }
        }

        Game game = new Game();
        game.run();
    }
}
