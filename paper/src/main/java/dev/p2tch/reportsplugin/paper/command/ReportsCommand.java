package dev.p2tch.reportsplugin.paper.command;

import com.google.inject.Inject;
import dev.p2tch.reportsplugin.paper.constant.Messages;
import dev.p2tch.reportsplugin.paper.service.NoticeService;
import dev.rollczi.litecommands.annotations.command.Command;
import dev.rollczi.litecommands.annotations.context.Context;
import dev.rollczi.litecommands.annotations.execute.Execute;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command(name = "reports")
public class ReportsCommand {
    private final NoticeService noticeService;

    @Inject
    public ReportsCommand(final @NotNull NoticeService noticeService) {
        this.noticeService = noticeService;
    }

    @Execute
    void execute(final @NotNull @Context Player player) {
        noticeService.builder()
                .message(Messages.DEFAULT_COMMAND_TEXT)
                .send(player);
    }
}
