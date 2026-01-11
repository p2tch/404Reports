package dev.p2tch.reportsplugin.paper.object.text;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.jetbrains.annotations.NotNull;

public class ColoredText extends Text {
    public ColoredText(final @NotNull String text) {
        super(text);
    }

    public Component build() {
        return MiniMessage.miniMessage().deserialize(getText());
    }
}
