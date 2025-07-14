package io.github.raulstanciu1.services;

import io.github.raulstanciu1.models.ProjectEnvironment;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class WorkspaceInitializer {
    public static void initializeWorkspace() throws IOException {
        ProjectEnvironment pe = ProjectEnvironment.getInstance();
        Path tempDirectory = PathProvider.createAndGetTempDirectoryPath();
        pe.setTempDirPath(tempDirectory);
        Path sourcePath = pe.getMctFilePath();
        Path tempSourcePath = tempDirectory.resolve("__mct__src.mct");
        Files.copy(sourcePath, tempSourcePath, StandardCopyOption.REPLACE_EXISTING);
        for(int i = 0; i < pe.getCFilePaths().size(); i++){
            Path cFilePath = pe.getCFilePaths().get(i);
            Path tempCFilePath = tempDirectory.resolve("___src_"+i+"___.c");
            Files.copy(cFilePath,tempCFilePath, StandardCopyOption.REPLACE_EXISTING);
        }
        Path outputDirectory = tempDirectory.resolve("out");
        pe.setOutputDirectory(outputDirectory);
        Files.createDirectories(outputDirectory);
        Files.createDirectories(outputDirectory.resolve("res"));
        copySourceFiles(tempDirectory, outputDirectory);
    }

    private static void copySourceFiles(Path srcDir, Path targetDir) throws IOException{
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(srcDir, "*.c")) {
            for (Path file : stream) {
                if (Files.isRegularFile(file)) {
                    Path destinationFile = targetDir.resolve(file.getFileName());
                    Files.copy(file, destinationFile, StandardCopyOption.REPLACE_EXISTING);
                }
            }
        }
    }
}
