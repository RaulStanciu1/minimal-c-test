package io.github.raulstanciu1.models;

import java.util.ArrayList;
import java.util.List;

public class TestCase {
    private String title;
    private String description;
    private int steps;
    private String preTestCode;
    private String testCode;
    private String postTestCode;
    private final List<TestVariable> testVariables;
    private final List<TestString> testStrings;
    private final List<String> environmentsUsed;
    public TestCase(){
        this.title = "";
        this.description = "";
        this.steps = 0;
        this.preTestCode = "";
        this.testCode = "";
        this.postTestCode = "";
        this.testVariables = new ArrayList<>();
        this.testStrings = new ArrayList<>();
        this.environmentsUsed = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSteps() {
        return steps;
    }

    public void setSteps(int steps) {
        this.steps = steps;
    }

    public String getPreTestCode() {
        return preTestCode;
    }

    public void setPreTestCode(String preTestCode) {
        this.preTestCode = preTestCode;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getPostTestCode() {
        return postTestCode;
    }

    public void setPostTestCode(String postTestCode) {
        this.postTestCode = postTestCode;
    }

    public List<TestVariable> getTestVariables() {
        return testVariables;
    }

    public List<TestString> getTestStrings() {
        return testStrings;
    }

    public List<String> getEnvironmentsUsed(){
        return environmentsUsed;
    }
}
