package io.github.raulstanciu1.services;


import io.github.raulstanciu1.models.results.*;

import java.io.IOException;
import java.util.List;

public class TestResultsOverviewGenerator {
    public static TestResultsOverview generateTestResultsOverview(List<TestResults> testResultsList) throws IOException {
        TestResultsOverview testResultsOverview = new TestResultsOverview(testResultsList);
        //TODO: Complete the final test results list and calculate the overall pass/fail status
        return testResultsOverview;
    }
}
