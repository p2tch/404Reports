package dev.p2tch.reportsplugin.paper.command;

import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command(name = "reports")
public class ReportsCommand {
    @Execute()
    void executeRootCommand(final @Context @NotNull Player player) {

    }
}
