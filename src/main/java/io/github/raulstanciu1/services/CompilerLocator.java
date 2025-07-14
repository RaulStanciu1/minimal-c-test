package io.github.raulstanciu1.services;

import io.github.raulstanciu1.exceptions.CompilerMissingException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CompilerLocator {
    public static String getAvailableCCompiler() throws CompilerMissingException {
        String[] compilers = {"gcc", "clang", "cc"};
        for(String compiler : compilers){
            if(compilerIsAvailable(compiler)){
                return compiler;
            }
        }
        throw new CompilerMissingException("Couldn't find any C compiler command available on this machine.");
    }

    private static boolean compilerIsAvailable(String compiler){
        try {
            Process process = new ProcessBuilder(compiler, "--version").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine() != null;
        } catch (Exception e) {
            return false;
        }
    }
}
