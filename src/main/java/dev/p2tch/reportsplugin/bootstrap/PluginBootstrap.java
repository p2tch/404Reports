package dev.p2tch.reportsplugin.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.p2tch.reportsplugin.module.PluginModule;
import dev.p2tch.reportsplugin.module.Slf4jLoggerModule;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

public final class PluginBootstrap extends JavaPlugin {
    private Injector injector;
    private Logger logger;

    @Override
    public void onLoad() {
        injector = Guice.createInjector(
                new PluginModule(this),
                new Slf4jLoggerModule(this)
        );

        logger = injector.getInstance(Logger.class);
    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }
}
