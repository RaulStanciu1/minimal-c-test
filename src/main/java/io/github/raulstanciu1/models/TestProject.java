package io.github.raulstanciu1.models;

import java.util.ArrayList;
import java.util.List;

public class TestProject {
    private String commonCode;
    private final List<Test> tests;
    public TestProject(){
        this.commonCode = "";
        this.tests = new ArrayList<>();
    }

    public List<Test> getTests() {
        return tests;
    }

    public String getCommonCode() {
        return commonCode;
    }

    public void setCommonCode(String commonCode) {
        this.commonCode = commonCode;
    }
}
