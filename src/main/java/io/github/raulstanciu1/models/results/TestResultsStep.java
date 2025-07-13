package io.github.raulstanciu1.models.results;

import java.util.List;

public record TestResultsStep(int stepId, List<TestResult> testResults){
}
