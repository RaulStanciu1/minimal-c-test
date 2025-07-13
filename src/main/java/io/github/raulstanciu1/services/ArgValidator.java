package io.github.raulstanciu1.services;

import io.github.raulstanciu1.exceptions.InvalidArgsException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ArgValidator {
    public static void validateArgs(String source, String[] cFiles) throws InvalidArgsException {
        Path sourcePath = Paths.get(source);
        if(!Files.exists(sourcePath)){
            throw new InvalidArgsException("MCT File cannot be found! Make sure you provided the correct path.");
        }
        for(String cFile : cFiles){
            Path cFilePath = Paths.get(cFile);
            if(!Files.exists(cFilePath)){
                throw new InvalidArgsException("C file: "+ cFile + " cannot be found! Make sure you provided the correct path.");
            }
        }
    }
}
