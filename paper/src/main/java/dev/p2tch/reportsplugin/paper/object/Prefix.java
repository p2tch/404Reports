package dev.p2tch.reportsplugin.paper.object;

import org.jetbrains.annotations.NotNull;

public enum Prefix {
    ARROW("»", "<dark_gray>");

    private final String symbol;
    private final String color;

    Prefix(final @NotNull String symbol, final @NotNull String color) {
        this.symbol = symbol;
        this.color = color;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getColor() {
        return color;
    }

    public String build() {
        return build(true);
    }

    public String build(final boolean space) {
        if (space) return color + symbol + " ";
        return color + symbol;
    }
}
