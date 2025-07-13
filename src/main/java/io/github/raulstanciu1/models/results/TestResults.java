package io.github.raulstanciu1.models.results;

import java.util.List;

public record TestResults(int testId, List<TestResultsCase> testResultsCases) {
}
