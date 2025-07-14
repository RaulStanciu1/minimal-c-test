package io.github.raulstanciu1.models.results;

import java.util.ArrayList;
import java.util.List;

public class TestResultsStep{
    private final int stepId;
    private final List<TestResult> testResults;
    private boolean passed;
    public TestResultsStep(int stepId){
        this.stepId = stepId;
        this.testResults = new ArrayList<>();
    }
    public List<TestResult> getTestResults() {
        return testResults;
    }
    public int getStepId() {
        return stepId;
    }
    public boolean isPassed() {
        return passed;
    }
    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
