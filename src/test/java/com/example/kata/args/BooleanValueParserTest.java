package com.example.kata.args;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class BooleanValueParserTest {
    @Test
    public void testParse() {
        assertThat(parseBooleanParser(""), is(Optional.of(Value.ofBoolean(true))));
        assertThat(parseBooleanParser("anything"), is(Optional.of(Value.ofBoolean(true))));
    }

    @Test
    public void testStreamConsumption() {
        assertThat(consumeBooleanValue("").getPosition(), is(0));
        assertThat(consumeBooleanValue("anything").getPosition(), is(0));
    }

    private CharStream consumeBooleanValue(String source) {
        CharStream input = CharStream.of(source);
        ValueParser.booleanWithDefault(false).parse(input);
        return input;
    }

    private Optional<Value> parseBooleanParser(String s) {
        return ValueParser.booleanWithDefault(false).parse(CharStream.of(s));
    }
}