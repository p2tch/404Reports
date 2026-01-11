package dev.p2tch.reportsplugin.paper.object.text;

import org.jetbrains.annotations.NotNull;

public class Text {
    public static final String DEFAULT_TEXT = "<none>";
    private final String text;

    public Text(final @NotNull String text) {
        this.text = text;
    }

    public Text() {
        this(DEFAULT_TEXT);
    }

    public String getText() {
        return text;
    }
}
