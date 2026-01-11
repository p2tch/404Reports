package dev.p2tch.reportsplugin.common.infrastructure.persistence;

import com.google.inject.Inject;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dev.p2tch.reportsplugin.common.domain.model.User;
import dev.p2tch.reportsplugin.common.domain.repository.DatabaseManager;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.DatabaseUrlProvider;
import dev.p2tch.reportsplugin.common.infrastructure.repository.user.OrmLiteUserRepositoryImpl;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public final class OrmLiteDatabaseManagerImpl implements DatabaseManager {
    private final OrmLiteUserRepositoryImpl repository;
    private final DatabaseUrlProvider urlProvider;
    private final Logger logger;
    private ConnectionSource connectionSource;

    @Inject
    public OrmLiteDatabaseManagerImpl(
            final @NotNull DatabaseUrlProvider urlProvider,
            final @NotNull OrmLiteUserRepositoryImpl repository,
            final @NotNull Logger logger
    ) {
        this.urlProvider = urlProvider;
        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public void connect() {
        try {
            if (connectionSource == null) {
                connectionSource = new JdbcConnectionSource(urlProvider.create());

                TableUtils.createTableIfNotExists(connectionSource, User.class);
            }
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CompletableFuture<Void> connectAndInitAsync() {
        return CompletableFuture.runAsync(() -> {
            connect();
            repository.init();
        });
    }

    @Override
    public void close() {
        try {
            if (connectionSource != null) {
                connectionSource.close();
            }
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
