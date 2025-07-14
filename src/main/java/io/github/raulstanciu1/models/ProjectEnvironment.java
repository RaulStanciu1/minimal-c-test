package io.github.raulstanciu1.models;

import io.github.raulstanciu1.models.test.TestProject;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ProjectEnvironment {
    private static ProjectEnvironment instance;
    private Path mctFilePath;
    private List<Path> cFilePaths;
    private Path tempDirPath;
    private Path outputDirectory;
    private final Path currentWorkingDir;
    private TestProject testProject;
    private String compiler;

    private ProjectEnvironment(){
        this.currentWorkingDir = Paths.get("").toAbsolutePath();
        this.cFilePaths = new ArrayList<>();
    }

    public static ProjectEnvironment getInstance() {
        if(instance == null){
            instance = new ProjectEnvironment();
        }
        return instance;
    }


    public TestProject getTestProject() {
        return testProject;
    }

    public void setTestProject(TestProject testProject) {
        this.testProject = testProject;
    }


    public String getCompiler() {
        return compiler;
    }
    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public Path getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(Path outputDirectory){
        this.outputDirectory = outputDirectory;
    }

    public Path getCurrentWorkingDir() {
        return currentWorkingDir;
    }

    public Path getMctFilePath() {
        return mctFilePath;
    }
    public void setMctFilePath(Path mctFilePath) {
        this.mctFilePath = mctFilePath;
    }
    public Path getTempDirPath() {
        return tempDirPath;
    }
    public void setTempDirPath(Path tempDirPath) {
        this.tempDirPath = tempDirPath;
    }
    public List<Path> getCFilePaths() {
        return cFilePaths;
    }
    public void setCFilePaths(List<Path> cFilePaths) {
        this.cFilePaths = cFilePaths;
    }
}
