package dev.p2tch.reportsplugin.common.infrastructure.configuration;

import dev.p2tch.reportsplugin.common.domain.model.DatabaseType;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.Comment;

public class DatabaseConfiguration extends OkaeriConfig {
    @Comment("mysql | postgresql | h2 | sqlite")
    private DatabaseType type = DatabaseType.SQLITE;
    private String host = "localhost";
    private Integer port = 3306;
    private String database = "database";
    private String username = "root";
    private String password = "password";

    public DatabaseType getType() {
        return type;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getDatabase() {
        return database;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
