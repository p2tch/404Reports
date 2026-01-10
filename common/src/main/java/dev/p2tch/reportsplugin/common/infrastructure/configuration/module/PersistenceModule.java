package dev.p2tch.reportsplugin.common.infrastructure.configuration.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dev.p2tch.reportsplugin.common.domain.model.DatabaseType;
import dev.p2tch.reportsplugin.common.domain.repository.DatabaseManager;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.OrmLiteDatabaseManagerImpl;
import org.jetbrains.annotations.NotNull;

public class PersistenceModule extends AbstractModule {
    private final DatabaseType databaseType;
    private final String url;
    private final String user;
    private final String password;

    public PersistenceModule(
            final @NotNull DatabaseType databaseType,
            final @NotNull String url,
            final @NotNull String user,
            final @NotNull String password
    ) {
        this.databaseType = databaseType;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Provides
    @Singleton
    public DatabaseManager provideDatabaseManager() {
        return new OrmLiteDatabaseManagerImpl(url, user, password);
    }
}
