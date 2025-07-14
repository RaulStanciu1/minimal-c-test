package io.github.raulstanciu1.services;

import io.github.raulstanciu1.models.ProjectEnvironment;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;

public class WorkspaceCleaner {
    public static void cleanWorkspace() throws IOException {
        ProjectEnvironment pe = ProjectEnvironment.getInstance();
        if(pe.getTempDirPath() != null && Files.exists(pe.getTempDirPath())) {
            try(var paths = Files.walk(pe.getTempDirPath())) {
                paths.sorted(Comparator.reverseOrder()) // Sort in reverse order to delete files before directories
                        .forEach(path -> {
                            try {
                                Files.delete(path);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
            }
        }
    }
}
