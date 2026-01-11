package dev.p2tch.reportsplugin.common.infrastructure.configuration.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dev.p2tch.reportsplugin.common.configuration.GeneralConfiguration;
import dev.p2tch.reportsplugin.common.domain.model.DatabaseType;
import dev.p2tch.reportsplugin.common.domain.repository.DatabaseManager;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.OrmLiteDatabaseManagerImpl;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.DatabaseUrlProvider;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.impl.DatabaseUrlProviderFactory;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class PersistenceModule extends AbstractModule {
    private final GeneralConfiguration configuration;
    private final File dataFolder;

    public PersistenceModule(
            final @NotNull GeneralConfiguration configuration,
            final @NotNull File dataFolder
    ) {
        this.configuration = configuration;
        this.dataFolder = dataFolder;
    }

    @Override
    protected void configure() {
        bind(DatabaseUrlProvider.class)
                .toInstance(DatabaseUrlProviderFactory.create(configuration, dataFolder));

        bind(DatabaseManager.class)
                .to(OrmLiteDatabaseManagerImpl.class)
                .asEagerSingleton();
    }
}
