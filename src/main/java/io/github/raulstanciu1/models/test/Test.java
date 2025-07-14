package io.github.raulstanciu1.models.test;

import java.util.ArrayList;
import java.util.List;

public class Test {
    private String title;
    private final List<TestEnvironment> testEnvironments;
    private final List<TestCase> testCases;
    public Test(){
        this.testEnvironments = new ArrayList<>();
        this.testCases = new ArrayList<>();
        this.title = "";
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public List<TestEnvironment> getEnvironments() {
        return testEnvironments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
