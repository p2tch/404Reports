package dev.p2tch.reportsplugin.common.infrastructure.persistence;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import dev.p2tch.reportsplugin.common.domain.repository.DatabaseManager;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class OrmLiteDatabaseManagerImpl implements DatabaseManager {
    private final String url;
    private final String user;
    private final String password;
    private ConnectionSource connectionSource;

    public OrmLiteDatabaseManagerImpl(final @NotNull String url, final @NotNull String user, final @NotNull String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public void connect() {
        try {
            if (connectionSource == null) {
                if (user.isEmpty() && password.isEmpty()) connectionSource = new JdbcConnectionSource(url);
                else connectionSource = new JdbcConnectionSource(url, user, password);
            }
        } catch (final SQLException e) {
            throw new RuntimeException("Error while connecting to database", e);
        }
    }

    @Override
    public void close() {
        if (connectionSource != null) {
            try {
                connectionSource.close();
            } catch (final Exception e) {
                throw new RuntimeException("Error while closing connection", e);
            }
        }
    }

    @Override
    public ConnectionSource getConnectionSource() {
        return connectionSource;
    }
}
