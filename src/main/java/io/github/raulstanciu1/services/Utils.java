package io.github.raulstanciu1.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Utils {
    public static String stripQuotes(String s) {
        return s.replaceAll("^\"|\"$", "");
    }

    public static void copyResourceFile(String file, Path dest) throws IOException {
        InputStream inputStream = Utils.class.getClassLoader().getResourceAsStream(file);
        if(inputStream == null){
            throw new FileNotFoundException("Resource file not found");
        }
        Path newDestPath = dest.resolve(file);
        Files.copy(inputStream, newDestPath, StandardCopyOption.REPLACE_EXISTING);
        inputStream.close();
    }

    public static String replaceWord(String input, String keyword, String replacement) {
        input = input.replaceAll("\\b" + keyword + "\\b", replacement);
        return input;
    }

    public static String insertBeforeWord(String input, String word, String wordToInsert){
        input = input.replaceAll("\\b" + word + "\\b", wordToInsert + word);
        return input;
    }
}
