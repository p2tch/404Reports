package dev.p2tch.reportsplugin.paper.constant;

import dev.p2tch.reportsplugin.blossom.BuildInfo;
import dev.p2tch.reportsplugin.paper.object.MessageTemplate;
import dev.p2tch.reportsplugin.paper.object.Prefix;

public class Messages {
    public static final MessageTemplate DEFAULT_COMMAND_TEXT = new MessageTemplate(
            Prefix.ARROW.build()
            + "<gray>You are using <red>404Reports v" + BuildInfo.VERSION
            + "<newline>"
            + Prefix.ARROW.build() + "<gray>/reports help"
    );
}
