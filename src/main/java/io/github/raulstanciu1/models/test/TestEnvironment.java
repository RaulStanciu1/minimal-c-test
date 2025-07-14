package io.github.raulstanciu1.models.test;

import java.util.ArrayList;
import java.util.List;

public class TestEnvironment {
    private String name;
    private final List<TestVariable> testVariables;
    private final List<TestString> testStrings;
    public TestEnvironment(){
        this.name = "";
        this.testVariables = new ArrayList<>();
        this.testStrings = new ArrayList<>();
    }

    public List<TestString> getTestStrings() {
        return testStrings;
    }

    public List<TestVariable> getTestVariables() {
        return testVariables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
