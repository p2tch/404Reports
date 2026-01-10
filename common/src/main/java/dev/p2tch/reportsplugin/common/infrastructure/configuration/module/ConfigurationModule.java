package dev.p2tch.reportsplugin.common.infrastructure.configuration.module;

import com.google.inject.AbstractModule;
import dev.p2tch.reportsplugin.common.configuration.GeneralConfiguration;
import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.snakeyaml.YamlSnakeYamlConfigurer;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class ConfigurationModule extends AbstractModule {
    private final File dataFolder;

    public ConfigurationModule(final @NotNull File dataFolder) {
        this.dataFolder = dataFolder;
    }

    @Override
    protected void configure() {
        bind(GeneralConfiguration.class).toInstance(
                ConfigManager.create(GeneralConfiguration.class, it -> {
                    it.configure(opt -> {
                        opt.configurer(new YamlSnakeYamlConfigurer());
                        opt.bindFile(new File(dataFolder, "config.yml"));
                    });
                })
        );
    }
}
