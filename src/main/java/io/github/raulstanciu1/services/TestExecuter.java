package io.github.raulstanciu1.services;


import io.github.raulstanciu1.models.ProjectEnvironment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;

public class TestExecuter {
    public static int executeTest() throws IOException, InterruptedException {
        ProjectEnvironment env = ProjectEnvironment.getInstance();
        Path outputDir = env.getOutputDirectory();
        ProcessBuilder builder = new ProcessBuilder(env.getCompiler(), "___mct___.c", "-o", "___mct___");
        builder.directory(outputDir.toFile());
        builder.redirectErrorStream(true);

        Process process = builder.start();

        System.out.println("Compiling the test process...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while((line = reader.readLine()) != null){
            System.out.println("PROCESS: "+line);
        }

        int exitCode = process.waitFor();

        if(exitCode != 0){
            return exitCode;
        }

        System.out.println("Test process compiled successfully. Running the test process...");

        builder = new ProcessBuilder(outputDir+".\\___mct___.exe");
        builder.directory(outputDir.toFile());
        builder.redirectErrorStream(true);

        process = builder.start();

        exitCode = process.waitFor();

        return exitCode;
    }
}
