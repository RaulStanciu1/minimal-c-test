package io.github.raulstanciu1.services;

import io.github.raulstanciu1.models.ProjectEnvironment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class PathProvider {
    public static Path getMctFilePath(String source) {
        return Path.of(source);
    }
    public static List<Path> getCFilePaths(String[] source) {
        return Stream.of(source)
                .map(Path::of)
                .toList();
    }
    public static Path createAndGetTempDirectoryPath() throws IOException {
        ProjectEnvironment pe = ProjectEnvironment.getInstance();
        Path currentWorkingDir = pe.getCurrentWorkingDir();
        Path tempDirectory = Files.createTempDirectory(currentWorkingDir, "__mct");
        if (!Files.exists(tempDirectory)) {
            throw new IOException("Failed to create temporary directory for the MCT workspace.");
        }
        return tempDirectory;
    }
}
