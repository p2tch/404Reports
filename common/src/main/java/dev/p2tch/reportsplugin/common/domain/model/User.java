package dev.p2tch.reportsplugin.common.domain.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@DatabaseTable(tableName = "404reports_users")
public class User {
    @DatabaseField(id = true)
    private final UUID uuid;

    @DatabaseField(canBeNull = false)
    private String lastKnownName;

    @DatabaseField
    private int submittedReports;

    @DatabaseField
    private int receivedReports;

    public User(final @NotNull UUID uuid, final @NotNull String name) {
        this.uuid = uuid;
        this.lastKnownName = name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getLastKnownName() {
        return lastKnownName;
    }

    public void setLastKnownName(final @NotNull String lastKnownName) {
        this.lastKnownName = lastKnownName;
    }

    public int getSubmittedReports() {
        return submittedReports;
    }

    public void increaseSubmittedReports() {
        submittedReports++;
    }

    public int getReceivedReports() {
        return receivedReports;
    }

    public void increaseReceivedReports() {
        receivedReports++;
    }
}
