package io.github.raulstanciu1.models;

import java.util.ArrayList;
import java.util.List;

public class TestVariable {
    private String name;
    private final List<String> inputs;
    private final List<String> outputs;
    private String delta;
    public TestVariable(){
        this.delta = null;
        this.name = "";
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public List<String> getOutputs() {
        return outputs;
    }

    public List<String> getInputs() {
        return inputs;
    }

    public String getDelta() {
        return delta;
    }

    public void setDelta(String delta) {
        this.delta = delta;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
