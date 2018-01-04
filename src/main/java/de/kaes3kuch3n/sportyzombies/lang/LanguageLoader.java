package de.kaes3kuch3n.sportyzombies.lang;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LanguageLoader {

    private String langFilePath;
    private HashMap<String, String> languages;
    private Language language;

    /**
     * Creates a new LanguageLoader and loads all languages specified in the Language enum.
     * @param path The path to load the language files from.
     */
    public LanguageLoader(String path) {
        this.languages = new HashMap<>();
        this.langFilePath = path;
        this.language = Language.EN;
        loadLanguages();
    }

    /**
     * Returns the localized String from the specified path. The Path consists of the
     * JSON objects separated with a dot where the String is saved.
     * @param path The path to get the String from.
     * @return The localized String.
     */
    public String getLocalizedString(String path) {
        try {
            JSONObject json = new JSONObject(languages.get(language.toString()));
            String[] splitPath = path.split("\\.");
            for (int i = 0; i < splitPath.length - 1; i++) {
                json = json.getJSONObject(splitPath[i]);
            }
            return json.getString(splitPath[splitPath.length - 1]);
        } catch (JSONException e) {
            return "Path not found";
        }
    }

    /**
     * Gets the current language.
     * @return The current language.
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets the current language.
     * @param language The language to set.
     */
    public void setLanguage(Language language) {
        this.language = language;
    }

    /**
     * Gets the absolute path for the language files.
     * @return The absolute language file path.
     */
    public String getLangFilePath() {
        return new File(langFilePath).getAbsolutePath();
    }

    /**
     * Sets the file path for the language files and reloads the language files.
     * @param path The new file path.
     */
    public void setLangFilePath(String path) {
        this.langFilePath = path;
        loadLanguages();
    }

    /**
     * Loads the languages specified in the Language enum.
     */
    private void loadLanguages() {
        for (Language l : Language.values()) {
            try {
                loadLanguage(l.toString());
            } catch (FileNotFoundException e) {
                System.err.println("No language file found for language " + l.toString() + "!\n" +
                        "Please add a laguage file called " + l.toString() + ".json to the languages folder.");
            }
        }
    }

    /**
     * Loads a JSON language String to the HashMap.
     * @param locale The language to load.
     * @throws FileNotFoundException When the language file is missing.
     */
    private void loadLanguage(String locale) throws FileNotFoundException {
        languages.put(locale.split("\\.")[0], loadLanguageFile(locale));
    }

    /**
     * Loads the JSON language file as a String.
     * @param locale The language to load.
     * @return The language String.
     * @throws FileNotFoundException When the language file is missing.
     */
    private String loadLanguageFile(String locale) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream(langFilePath + locale + ".json"));
        StringBuilder sb = new StringBuilder("");
        while (s.hasNextLine()) {
            sb.append(s.nextLine().replaceAll("\\n", ""));
        }
        return sb.toString();
    }

}
