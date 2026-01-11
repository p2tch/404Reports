package dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.impl;

import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.DatabaseUrlProvider;
import org.jetbrains.annotations.NotNull;

public final class ServerDatabaseUrlProvider implements DatabaseUrlProvider {
    private final String driver;
    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public ServerDatabaseUrlProvider(
            final @NotNull String driver,
            final @NotNull String host,
            final int port,
            final @NotNull String database,
            final @NotNull String username,
            final @NotNull String password
    ) {
        this.driver = driver;
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    @Override
    public String create() {
        return driver + "://" + host + ":" + port + "/" + database +
                "?user=" + username + "&password=" + password;
    }
}