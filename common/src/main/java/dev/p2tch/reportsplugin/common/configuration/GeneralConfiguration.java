package dev.p2tch.reportsplugin.common.configuration;

import dev.p2tch.reportsplugin.common.infrastructure.configuration.DatabaseConfiguration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class GeneralConfiguration extends OkaeriConfig {
    @Comment("Database configuration")
    public static DatabaseConfiguration database = new DatabaseConfiguration();
}
