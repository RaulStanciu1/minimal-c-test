package io.github.raulstanciu1.services;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestExecuter {
    public static int executeTest() throws IOException, InterruptedException {
        ProjectEnvironment env = ProjectEnvironment.getInstance();
        Path outputDir = env.getOutputDirectory();
        ProcessBuilder builder = new ProcessBuilder(env.getCompiler(), "___mct___.c", "-o", "___mct___");
        builder.directory(outputDir.toFile());
        builder.redirectErrorStream(true);

        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        List<String> outputLogList = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null){
            outputLogList.add(line);
        }

        int exitCode = process.waitFor();

        env.setOutputLog(outputLogList);
        if(exitCode != 0){
            return exitCode;
        }

        builder = new ProcessBuilder(outputDir+".\\___mct___.exe");
        builder.directory(outputDir.toFile());
        builder.redirectErrorStream(true);

        process = builder.start();

        exitCode = process.waitFor();

        return exitCode;
    }
}
