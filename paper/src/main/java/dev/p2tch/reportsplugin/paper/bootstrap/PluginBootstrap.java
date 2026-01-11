package dev.p2tch.reportsplugin.paper.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dev.p2tch.reportsplugin.common.domain.repository.DatabaseManager;
import dev.p2tch.reportsplugin.common.infrastructure.configuration.module.PersistenceModule;
import dev.p2tch.reportsplugin.common.infrastructure.configuration.module.RepositoryModule;
import dev.p2tch.reportsplugin.paper.command.ReportsCommand;
import dev.p2tch.reportsplugin.paper.module.PluginModule;
import dev.p2tch.reportsplugin.paper.module.ServiceModule;
import dev.p2tch.reportsplugin.paper.module.Slf4jLoggerModule;
import dev.rollczi.litecommands.LiteCommands;
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.slf4j.Logger;

import java.io.File;

public final class PluginBootstrap extends JavaPlugin {
    private LiteCommands<CommandSender> liteCommands;
    private Injector injector;
    private Logger logger;

    @Override
    public void onLoad() {
        injector = Guice.createInjector(
                new PluginModule(this),
                new Slf4jLoggerModule(this),
                new PersistenceModule(getDataFolder()),
                new ServiceModule(),
                new RepositoryModule()
        );

        logger = injector.getInstance(Logger.class);

        ensureDataFolder();

        final DatabaseManager databaseManager =
                injector.getInstance(DatabaseManager.class);

        databaseManager.connectAndInitAsync()
                .whenComplete((res, ex) -> {
                    if (ex != null) {
                        logger.error("An unexpected error while connecting to the database {}", ex.getMessage());
                        getServer().getPluginManager().disablePlugin(this);
                    } else {
                        logger.info("Successfully connected to the database");
                    }
                });
    }

    @Override
    public void onEnable() {
        this.liteCommands = LiteBukkitFactory.builder("404reports", this)
                .commands(
                        injector.getInstance(ReportsCommand.class)
                )
                .build();
    }

    @Override
    public void onDisable() {

    }

    private void ensureDataFolder() {
        final File dataFolder = getDataFolder();

        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            logger.error("Failed to create data folder: {}", dataFolder.getAbsolutePath());
            throw new IllegalStateException("Cannot create plugin data folder");
        }
    }
}
