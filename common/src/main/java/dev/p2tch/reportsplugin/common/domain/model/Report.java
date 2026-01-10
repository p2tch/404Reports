package dev.p2tch.reportsplugin.common.domain.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@DatabaseTable(tableName = "404reports_reports")
public class Report {
    @DatabaseField(id = true)
    private UUID uuid;

    @DatabaseField(canBeNull = false)
    private User submitter;

    @DatabaseField(canBeNull = false)
    private User target;

    @DatabaseField(canBeNull = false)
    private String reason;

    public Report() {

    }

    public Report(
            final @NotNull UUID uuid,
            final @NotNull User submitter,
            final @NotNull User target,
            final @NotNull String reason
    ) {
        this.uuid = uuid;
        this.submitter = submitter;
        this.target = target;
        this.reason = reason;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getSubmitter() {
        return submitter;
    }

    public User getTarget() {
        return target;
    }

    public String getReason() {
        return reason;
    }
}
