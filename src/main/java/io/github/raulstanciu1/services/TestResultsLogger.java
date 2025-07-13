package io.github.raulstanciu1.services;


import io.github.raulstanciu1.models.Test;
import io.github.raulstanciu1.models.TestCase;
import io.github.raulstanciu1.models.results.TestResult;
import io.github.raulstanciu1.models.results.TestResults;
import io.github.raulstanciu1.models.results.TestResultsCase;
import io.github.raulstanciu1.models.results.TestResultsStep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TestResultsLogger {
    public static void logTestResults(List<TestResults> testResultsList) throws IOException {
        List<String> logContents = new ArrayList<>();
        List<Test> testList = ProjectEnvironment.getInstance().getTestProject().getTests();
        int totalTestCases = 0;
        int totalTestSteps = 0;
        int casesPassed = 0;
        int casesFailed = 0;
        int stepsPassed = 0;
        int stepsFailed = 0;
        logContents.add("Test Results Details:");
        for(TestResults testResults: testResultsList){
            Test currentTest = testList.get(testResults.testId());
            logContents.add("Test ID: " + testResults.testId());
            logContents.add("Test title: "+currentTest.getTitle());
            for(TestResultsCase testResultsCase: testResults.testResultsCases()){
                boolean casePassed = true;
                totalTestCases++;
                TestCase currentTestCase = currentTest.getTestCases().get(testResultsCase.testCaseId());
                logContents.add("  Test Case ID: " + testResultsCase.testCaseId());
                logContents.add("  Test Case title: " + currentTestCase.getTitle());
                logContents.add("  Test Case description: " + currentTestCase.getDescription());
                for(TestResultsStep testResultsStep : testResultsCase.testResultsSteps()){
                    boolean stepPassed = true;
                    totalTestSteps++;
                    logContents.add("    Test Step #" + testResultsStep.stepId()+":");
                    for(TestResult testResult : testResultsStep.testResults()){
                        if(testResult.passed()){
                            logContents.add("      " + testResult.varName() + ": OK Expected: "+ testResult.expectedValue() + " Actual: " + testResult.expectedValue());
                        } else {
                            casePassed = false;
                            stepPassed = false;
                            logContents.add("      " + testResult.varName() + ": NOK Expected: "+ testResult.expectedValue() + " Actual: " + testResult.actualValue());
                        }
                    }
                    if(stepPassed) {
                        stepsPassed++;
                        logContents.add("    Test Step #" + testResultsStep.stepId() + ": PASSED");
                    } else {
                        stepsFailed++;
                        logContents.add("    Test Step #" + testResultsStep.stepId() + ": FAILED");
                    }
                }
                if(casePassed) {
                    casesPassed++;
                } else {
                    casesFailed++;
                }
            }
        }
        logContents.add("Test results summary:");
        logContents.add("Tests executed: " + testResultsList.size());
        logContents.add("Test cases executed: " + totalTestCases);
        logContents.add("Test steps executed: " + totalTestSteps);
        logContents.add("Test cases passed: " + casesPassed);
        logContents.add("Test cases failed: " + casesFailed);
        logContents.add("Test steps passed: " + stepsPassed);
        logContents.add("Test steps failed: " + stepsFailed);

        Path logFilePath = ProjectEnvironment.getInstance().getCurrentWorkingDir().resolve("mct_test_results_summary.log");
        Files.writeString(logFilePath,
                String.join("\n", logContents),
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);
        for(String line : logContents) {
            System.out.println(line);
        }
        System.out.println("Test results summary written to: " + logFilePath.toAbsolutePath());
    }
}
