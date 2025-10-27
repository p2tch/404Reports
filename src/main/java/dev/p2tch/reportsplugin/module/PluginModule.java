package dev.p2tch.reportsplugin.module;

import com.google.inject.AbstractModule;
import dev.p2tch.reportsplugin.bootstrap.PluginBootstrap;
import org.jetbrains.annotations.NotNull;

public final class PluginModule extends AbstractModule {
    private final PluginBootstrap pluginBootstrap;

    public PluginModule(final @NotNull PluginBootstrap pluginBootstrap) {
        this.pluginBootstrap = pluginBootstrap;
    }

    @Override
    protected void configure() {
        bind(PluginBootstrap.class).toInstance(pluginBootstrap);
    }
}
