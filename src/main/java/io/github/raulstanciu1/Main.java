package io.github.raulstanciu1;

import io.github.raulstanciu1.exceptions.*;
import io.github.raulstanciu1.models.ProjectEnvironment;
import io.github.raulstanciu1.models.results.TestResults;
import io.github.raulstanciu1.models.results.TestResultsOverview;
import io.github.raulstanciu1.services.*;
import io.github.raulstanciu1.services.parser.ParserService;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Options options = new Options();

        Option sourceOption = Option.builder("s")
                .hasArg()
                .argName("source")
                .desc("The path to the MCT source file")
                .required()
                .build();

        Option cFilesOption = Option.builder("c")
                .hasArgs()
                .argName("test")
                .desc("The C source files to test")
                .required()
                .build();

        options.addOption(sourceOption);
        options.addOption(cFilesOption);

        try {
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            String sourceFile = cmd.getOptionValue("s");
            String[] cFiles = cmd.getOptionValues("c");

            ArgValidator.validateArgs(sourceFile, cFiles);

            ProjectEnvironment projectEnvironment = ProjectEnvironment.getInstance();
            projectEnvironment.setMctFilePath(PathProvider.getMctFilePath(sourceFile));
            projectEnvironment.setCFilePaths(PathProvider.getCFilePaths(cFiles));
            projectEnvironment.setCompiler(CompilerLocator.getAvailableCCompiler());

            WorkspaceInitializer.initializeWorkspace();

            ParserService.parseMctFile();
            TestGenerator.generateTestFiles();

            System.out.println("Test files generated successfully. Starting the test process...");
            int exitCode = TestExecuter.executeTest();
            if(exitCode != 0){
                throw new ProcessErrorException("Test Process return an error code: "+exitCode);
            }

            List<TestResults> testResultsList = TestResultsReader.readTestResults();

            if(testResultsList.isEmpty()){
                throw new InvalidEnvironmentException("No test results were generated. Please check your MCT file and C files.");
            }

            TestResultsOverview testResultsOverview = TestResultsOverviewGenerator.generateTestResultsOverview(testResultsList);

            ReportGenerator.generateReportPdf(testResultsOverview);

            WorkspaceCleaner.cleanWorkspace();

        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java MCT -s <MCT file> -c <C files...>", options);
        } catch (InvalidArgsException e) {
            System.out.println("Invalid Arguments provided: "+e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("java MCT -s <MCT file> -c <C files...>", options);
        } catch(EnvironmentSetupException e){
            System.err.println("Encountered an error while setting up the environment: "+e.getMessage());
        } catch(CompilerMissingException e){
            System.err.println("Encountered an error while checking available C compiler: "+e.getMessage());
        } catch(InvalidEnvironmentException e){
            System.err.println("Encountered an error while creating test files: "+e.getMessage());
        } catch(IOException e){
            System.err.println("Encountered an error while performing an IO operation: "+e.getMessage());
        } catch(InterruptedException e){
            System.err.println("Encountered an error while running a process: "+e.getMessage());
        } catch(ProcessErrorException e){
            System.err.println("Process encountered a problem: "+e.getMessage());
        }
    }
}