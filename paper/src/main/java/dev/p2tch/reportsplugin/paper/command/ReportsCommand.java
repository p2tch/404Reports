package dev.p2tch.reportsplugin.paper.command;

import dev.p2tch.reportsplugin.blossom.BuildInfo;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@Command(name = "reports")
public class ReportsCommand {
    final List<Component> ROOT_MESSAGES = List.of(
            MiniMessage.miniMessage().deserialize("<gray>You are using <red>404Reports v" + BuildInfo.VERSION),
            MiniMessage.miniMessage().deserialize("<gray>/reports help")
    );

    @Execute()
    void executeRootCommand(final @Context @NotNull Player player) {
        for (final Component line : ROOT_MESSAGES) {
            player.sendMessage(line);
        }
    }
}
