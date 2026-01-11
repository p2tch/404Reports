package dev.p2tch.reportsplugin.common.infrastructure.persistence;

import com.google.inject.Inject;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import dev.p2tch.reportsplugin.common.domain.model.User;
import dev.p2tch.reportsplugin.common.domain.repository.DatabaseManager;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.DatabaseUrlProvider;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public final class OrmLiteDatabaseManagerImpl implements DatabaseManager {

    private final DatabaseUrlProvider urlProvider;
    private ConnectionSource connectionSource;

    @Inject
    public OrmLiteDatabaseManagerImpl(
            final @NotNull DatabaseUrlProvider urlProvider
    ) {
        this.urlProvider = urlProvider;
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
