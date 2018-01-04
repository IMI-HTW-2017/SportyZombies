package de.kaes3kuch3n.sportyzombies;

import de.kaes3kuch3n.sportyzombies.game.Game;
import de.kaes3kuch3n.sportyzombies.lang.Language;
import de.kaes3kuch3n.sportyzombies.lang.LanguageLoader;

public class SportyZombies {

    private static LanguageLoader languageLoader;

    public static void main(String[] args) {
        setupLanguageLoader(args);
        Game game = new Game();
        game.run();
    }

    private static void setupLanguageLoader(String[] args) {
        languageLoader = new LanguageLoader("src/main/resources/language/");

        if (args.length == 1) {
            Language lang = null;
            for (Language l : Language.values()) {
                if (Language.valueOf(args[0].toUpperCase()).equals(l)) {
                    lang = l;
                }
            }
            languageLoader.setLanguage((lang == null) ? Language.EN : lang);
        }
    }

    public static LanguageLoader getLanguageLoader() {
        return languageLoader;
    }
}
