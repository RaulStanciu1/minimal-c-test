package io.github.raulstanciu1.models.results;

import java.util.List;

public class TestResultsOverview {
    private int totalTests;
    private int totalTestCases;
    private int totalTestSteps;
    private int totalTestsPassed;
    private int totalTestsFailed;
    private int totalTestCasesPassed;
    private int totalTestCasesFailed;
    private int totalTestStepsPassed;
    private int totalTestStepsFailed;
    private float passPercentage;
    private final List<TestResults> testResultsList;
    public TestResultsOverview(List<TestResults> testResultsList) {
        this.testResultsList = testResultsList;
        this.totalTests = 0;
        this.totalTestCases = 0;
        this.totalTestSteps = 0;
        this.totalTestsPassed = 0;
        this.totalTestsFailed = 0;
        this.totalTestCasesPassed = 0;
        this.totalTestCasesFailed = 0;
        this.totalTestStepsPassed = 0;
        this.totalTestStepsFailed = 0;
        this.passPercentage = 0.0f;
    }
    public int getTotalTests() {
        return totalTests;
    }
    public void setTotalTests(int totalTests) {
        this.totalTests = totalTests;
    }
    public int getTotalTestCases() {
        return totalTestCases;
    }
    public void setTotalTestCases(int totalTestCases) {
        this.totalTestCases = totalTestCases;
    }
    public int getTotalTestSteps() {
        return totalTestSteps;
    }
    public void setTotalTestSteps(int totalTestSteps) {
        this.totalTestSteps = totalTestSteps;
    }
    public int getTotalTestsPassed() {
        return totalTestsPassed;
    }
    public void setTotalTestsPassed(int totalTestsPassed) {
        this.totalTestsPassed = totalTestsPassed;
    }
    public int getTotalTestsFailed() {
        return totalTestsFailed;
    }
    public void setTotalTestsFailed(int totalTestsFailed) {
        this.totalTestsFailed = totalTestsFailed;
    }
    public int getTotalTestCasesPassed() {
        return totalTestCasesPassed;
    }
    public void setTotalTestCasesPassed(int totalTestCasesPassed) {
        this.totalTestCasesPassed = totalTestCasesPassed;
    }
    public int getTotalTestCasesFailed() {
        return totalTestCasesFailed;
    }
    public void setTotalTestCasesFailed(int totalTestCasesFailed) {
        this.totalTestCasesFailed = totalTestCasesFailed;
    }
    public int getTotalTestStepsPassed() {
        return totalTestStepsPassed;
    }
    public void setTotalTestStepsPassed(int totalTestStepsPassed) {
        this.totalTestStepsPassed = totalTestStepsPassed;
    }
    public int getTotalTestStepsFailed() {
        return totalTestStepsFailed;
    }
    public void setTotalTestStepsFailed(int totalTestStepsFailed) {
        this.totalTestStepsFailed = totalTestStepsFailed;
    }
    public float getPassPercentage() {
        return passPercentage;
    }
    public void setPassPercentage(float passPercentage) {
        this.passPercentage = passPercentage;
    }
    public List<TestResults> getTestResultsList() {
        return testResultsList;
    }
}
