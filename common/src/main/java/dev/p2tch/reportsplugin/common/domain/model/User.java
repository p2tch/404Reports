package dev.p2tch.reportsplugin.common.domain.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@DatabaseTable(tableName = "404reports_users")
public class User {
    @DatabaseField(id = true)
    private UUID uuid;

    @DatabaseField
    private int submittedReports;

    @DatabaseField
    private int receivedReports;

    public User() {

    }

    public User(final @NotNull UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuid() {
        return uuid;
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
