package com.example.kata.args;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Args {
    private final List<Argument> arguments;

    Args(String schema, String commandLine) {
        this.arguments = parseArguments(schema);
        resolveValues(commandLine);
    }

    private List<Argument> parseArguments(String schema) {
        return Arrays.stream(schema.split(";")).map(Argument::new).collect(Collectors.toList());
    }

    <T> T getValue(char flag) {
        return getArgument(flag).getValue();
    }

    int getNumberOfArguments() {
        return arguments.size();
    }

    private Argument getArgument(char flag) {
        return arguments.stream().filter(x -> x.getFlag() == flag).findFirst().orElseThrow(() -> new ArgsException("Flag not found: " + flag));
    }

    private void resolveValues(String commandLine) {
        arguments.forEach(argument -> argument.resolveValue(commandLine));
    }
}
