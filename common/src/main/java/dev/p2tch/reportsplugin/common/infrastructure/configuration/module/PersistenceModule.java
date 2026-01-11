package dev.p2tch.reportsplugin.common.infrastructure.configuration.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import dev.p2tch.reportsplugin.common.configuration.GeneralConfiguration;
import dev.p2tch.reportsplugin.common.domain.repository.DatabaseManager;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.OrmLiteDatabaseManagerImpl;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.DatabaseUrlProvider;
import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.impl.DatabaseUrlProviderFactory;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class PersistenceModule extends AbstractModule {

    private final File dataFolder;

    public PersistenceModule(final @NotNull File dataFolder) {
        this.dataFolder = dataFolder;
    }

    @Override
    protected void configure() {
        bind(DatabaseManager.class)
                .to(OrmLiteDatabaseManagerImpl.class)
                .asEagerSingleton();
    }

    @Provides
    @Singleton
    public GeneralConfiguration provideConfiguration() {
        return ConfigManager.create(GeneralConfiguration.class, it -> {
            it.configure(opt -> {
                opt.configurer(new YamlSnakeYamlConfigurer());
                opt.bindFile(new File(dataFolder, "config.yml"));
            });
        });
    }

    @Provides
    @Singleton
    public DatabaseUrlProvider provideDatabaseUrlProvider(
            final @NotNull GeneralConfiguration configuration
    ) {
        return DatabaseUrlProviderFactory.create(configuration, dataFolder);
    }
}
