package io.github.raulstanciu1.models.results;

import java.util.ArrayList;
import java.util.List;

public class TestResultsCase {
    private final String title;
    List<TestResultsStep> testResultsSteps;
    private boolean passed;
    public TestResultsCase(String title){
        this.title = title;
        this.testResultsSteps = new ArrayList<>();
    }
    public List<TestResultsStep> getTestResultsSteps() {
        return testResultsSteps;
    }
    public String getTitle() {
        return title;
    }
    public boolean isPassed() {
        return passed;
    }
    public void setPassed(boolean passed) {
        this.passed = passed;
    }
}
