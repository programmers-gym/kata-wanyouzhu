package com.example.kata.args;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
class OptionParser {
    private final FlagParser flagParser;
    private final ValueParser valueParser;

    static OptionParser create(FlagParser flagParser, ValueParser valueParser) {
        return new OptionParser(flagParser, valueParser);
    }

    Optional<Option> parse(CharStream input) {
        Optional<Flag> parsedFlag = flagParser.parse(input);
        return parsedFlag.map(flag -> Option.of(flag, valueParser.parse(input).orElse(valueParser.getDefaultValue())));
    }

    Option getDefault() {
        return Option.of(Flag.of(flagParser.getFlag()), valueParser.getDefaultValue());
    }
}
