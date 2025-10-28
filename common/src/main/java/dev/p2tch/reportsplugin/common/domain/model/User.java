package dev.p2tch.reportsplugin.common.domain.model;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class User {
    protected final UUID uuid;

    public User(final @NotNull UUID uuid) {
        this.uuid = uuid;
    }
}
