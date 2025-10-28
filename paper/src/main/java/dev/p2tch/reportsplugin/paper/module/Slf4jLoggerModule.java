package dev.p2tch.reportsplugin.paper.module;

import com.google.inject.AbstractModule;
import dev.p2tch.reportsplugin.paper.bootstrap.PluginBootstrap;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Slf4jLoggerModule extends AbstractModule {
    private final PluginBootstrap pluginBootstrap;

    public Slf4jLoggerModule(final @NotNull PluginBootstrap pluginBootstrap) {
        this.pluginBootstrap = pluginBootstrap;
    }

    @Override
    protected void configure() {
        bind(Logger.class).toInstance(LoggerFactory.getLogger(pluginBootstrap.getName()));
    }
}
