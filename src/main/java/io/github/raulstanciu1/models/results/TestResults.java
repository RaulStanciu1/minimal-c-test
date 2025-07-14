package io.github.raulstanciu1.models.results;

import java.util.ArrayList;
import java.util.List;

public class TestResults{
    private final String title;
    private final List<TestResultsCase> testResultsCases;
    private boolean passed;
    public TestResults(String title) {
        this.testResultsCases = new ArrayList<>();
        this.title = title;
        this.passed = false;
    }
    public List<TestResultsCase> getTestResultsCases() {
        return testResultsCases;
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

