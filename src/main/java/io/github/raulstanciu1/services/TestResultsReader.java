package io.github.raulstanciu1.services;


import io.github.raulstanciu1.models.Test;
import io.github.raulstanciu1.models.results.TestResult;
import io.github.raulstanciu1.models.results.TestResults;
import io.github.raulstanciu1.models.results.TestResultsCase;
import io.github.raulstanciu1.models.results.TestResultsStep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TestResultsReader {
    public static List<TestResults> readTestResults() throws IOException {
        ProjectEnvironment pe = ProjectEnvironment.getInstance();
        Path resultsDir = pe.getOutputDirectory().resolve("res");
        List<TestResults> testResultsList = new ArrayList<>();

        for(int i = 0; i < pe.getTestProject().getTests().size(); i++){
            Path testDir = resultsDir.resolve(String.valueOf(i));
            if(!testDir.toFile().exists()) {
                throw new IOException("Test results were not created: Test ID " + i);
            }
            Test test = pe.getTestProject().getTests().get(i);
            TestResults testResults = new TestResults(i, new ArrayList<>());
            for(int j = 0; j < test.getTestCases().size(); j++){
                Path testCaseFile = testDir.resolve("___mct_tc_"+j+"_res.txt");
                if(!testCaseFile.toFile().exists()) {
                    throw new IOException("Test results were not created: Test ID " + i + ", Test Case ID " + j);
                }
                TestResultsCase testResultsCase = new TestResultsCase(j, new ArrayList<>());
                List<String> testCaseTextContent = Files.readAllLines(testCaseFile);
                int testStepId = -1;
                List<TestResult> testResultsStepList = null;
                for(String line : testCaseTextContent){
                    String[] words = line.split(" ");
                    if(words[0].equals("MCT_BEGIN_STEP")){
                        testStepId = Integer.parseInt(words[1]);
                        testResultsStepList = new ArrayList<>();
                    } else if(words[0].equals("MCT_END_STEP")){
                        testResultsCase.testResultsSteps().add(new TestResultsStep(testStepId, testResultsStepList));
                        testStepId = -1;
                        testResultsStepList = null;
                    } else {
                        if(testStepId == -1) {
                            throw new IOException("Invalid test results received by test process for Test ID " + i + ", Test Case ID " + j);
                        }
                        testResultsStepList.add(new TestResult(
                                words[0],
                                words[1].equals("1"),
                                words[2],
                                words[3]
                        ));
                    }
                }
                testResults.testResultsCases().add(testResultsCase);
            }
            testResultsList.add(testResults);
        }
        return testResultsList;
    }
}
