package io.github.raulstanciu1.services;

import io.github.raulstanciu1.exceptions.CompilerMissingException;
import io.github.raulstanciu1.exceptions.EnvironmentSetupException;
import io.github.raulstanciu1.models.SourcePaths;
import io.github.raulstanciu1.models.TestProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;
import java.util.List;

public class ProjectEnvironment {
    private static ProjectEnvironment instance;
    private final SourcePaths sourcePaths;
    private TestProject testProject;
    private String compiler;
    private Path outputDirectory;
    private final Path currentWorkingDir;
    private List<String> outputLog;
    private ProjectEnvironment(){
        this.sourcePaths = new SourcePaths();
        this.currentWorkingDir = Paths.get("").toAbsolutePath();
    }

    public static ProjectEnvironment getInstance() {
        if(instance == null){
            instance = new ProjectEnvironment();
        }
        return instance;
    }

    public void cleanup() throws IOException{
        //Recursively delete the temp directory
        if(this.sourcePaths.getTempDirPath() != null && Files.exists(this.sourcePaths.getTempDirPath())) {
            try(var paths = Files.walk(this.sourcePaths.getTempDirPath())) {
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

    public void setupEnvironment(String source, String[] cFiles) throws EnvironmentSetupException, CompilerMissingException {
        setSourcePaths(source, cFiles);
        this.compiler = getAvailableCCompiler();
        if(this.compiler == null){
            throw new CompilerMissingException("Couldn't find any C compiler command available on this machine.");
        }
    }

    private void setSourcePaths(String source, String[] cFiles){
        try {
            Path currentWorkingDir = Paths.get("").toAbsolutePath();
            Path sourcePath = Paths.get(source);
            Path tempDirectory = Files.createTempDirectory(currentWorkingDir, "__mct");
            this.sourcePaths.setTempDirPath(tempDirectory);
            Path tempSourcePath = tempDirectory.resolve("__mct__src.mct");
            Files.copy(sourcePath, tempSourcePath, StandardCopyOption.REPLACE_EXISTING);
            for(int i = 0; i < cFiles.length; i++){
                Path cFilePath = Paths.get(cFiles[i]);
                Path tempCFilePath = tempDirectory.resolve("___src_"+i+"___.c");
                Files.copy(cFilePath,tempCFilePath, StandardCopyOption.REPLACE_EXISTING);
                this.sourcePaths.getCFilePaths().add(tempCFilePath);
            }
            this.sourcePaths.setMctFilePath(tempSourcePath);
        }catch(IOException e){
            throw new EnvironmentSetupException(e.getMessage());
        }
    }

    private String getAvailableCCompiler(){
        String[] compilers = {"gcc", "clang", "cc"};
        for(String compiler : compilers){
            if(compilerIsAvailable(compiler)){
                return compiler;
            }
        }
        return null;
    }

    private boolean compilerIsAvailable(String compiler){
        try {
            Process process = new ProcessBuilder(compiler, "--version").start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return reader.readLine() != null;
        } catch (Exception e) {
            return false;
        }
    }

    public TestProject getTestProject() {
        return testProject;
    }

    public void setTestProject(TestProject testProject) {
        this.testProject = testProject;
    }

    public SourcePaths getSourcePaths() {
        return sourcePaths;
    }

    public String getCompiler() {
        return compiler;
    }

    public Path getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(Path outputDirectory){
        this.outputDirectory = outputDirectory;
    }

    public List<String> getOutputLog(){
        return this.outputLog;
    }

    public void setOutputLog(List<String> l){
        this.outputLog = l;
    }

    public Path getCurrentWorkingDir() {
        return currentWorkingDir;
    }
}
