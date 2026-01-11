package dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.impl;

import dev.p2tch.reportsplugin.common.configuration.GeneralConfiguration;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.DatabaseUrlProvider;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class DatabaseUrlProviderFactory {
    public static DatabaseUrlProvider create(
            final @NotNull GeneralConfiguration configuration,
            final @NotNull File dataFolder
    ) {
        final var db = configuration.getDatabaseConfiguration();

        return switch (db.getType()) {
            case SQLITE -> new FileDatabaseUrlProvider(
                    "jdbc:sqlite:",
                    new File(dataFolder, db.getDatabase())
            );
            case H2 -> new FileDatabaseUrlProvider(
                    "jdbc:h2:file:",
                    new File(dataFolder, db.getDatabase())
            );
            case MYSQL -> new ServerDatabaseUrlProvider(
                    "jdbc:mysql",
                    db.getHost(),
                    db.getPort(),
                    db.getDatabase(),
                    db.getUsername(),
                    db.getPassword()
            );
            case POSTGRESQL -> new ServerDatabaseUrlProvider(
                    "jdbc:postgresql",
                    db.getHost(),
                    db.getPort(),
                    db.getDatabase(),
                    db.getUsername(),
                    db.getPassword()
            );
        };
    }
}
