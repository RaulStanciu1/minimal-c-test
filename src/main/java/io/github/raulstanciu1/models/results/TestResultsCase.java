package io.github.raulstanciu1.models.results;

import java.util.List;

public record TestResultsCase(int testCaseId, List<TestResultsStep> testResultsSteps) {
}
