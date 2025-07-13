package io.github.raulstanciu1.models;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class SourcePaths {
    private Path mctFilePath;
    private Path tempDirPath;
    private final List<Path> cFilePaths;
    public SourcePaths(){
        this.cFilePaths = new ArrayList<>();
    }

    public List<Path> getCFilePaths() {
        return cFilePaths;
    }

    public Path getMctFilePath() {
        return mctFilePath;
    }

    public Path getTempDirPath(){
        return tempDirPath;
    }

    public void setMctFilePath(Path mctFilePath) {
        this.mctFilePath = mctFilePath;
    }

    public void setTempDirPath(Path tempDirPath) {
        this.tempDirPath = tempDirPath;
    }
}
