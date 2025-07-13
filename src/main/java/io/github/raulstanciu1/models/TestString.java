package io.github.raulstanciu1.models;

import java.util.ArrayList;
import java.util.List;

public class TestString {
    private String name;
    private final List<String> inputs;
    private final List<String> outputs;
    public TestString(){
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.name = "";
    }

    public List<String> getInputs() {
        return inputs;
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
