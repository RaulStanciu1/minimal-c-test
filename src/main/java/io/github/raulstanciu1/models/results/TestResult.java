package io.github.raulstanciu1.models.results;

public record TestResult(String varName, boolean passed, String expectedValue, String actualValue) {
}
