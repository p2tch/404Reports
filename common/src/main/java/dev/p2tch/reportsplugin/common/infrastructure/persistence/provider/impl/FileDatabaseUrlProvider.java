package dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.impl;

import dev.p2tch.reportsplugin.common.infrastructure.persistence.provider.DatabaseUrlProvider;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class FileDatabaseUrlProvider implements DatabaseUrlProvider {
    private final String prefix;
    private final File file;

    public FileDatabaseUrlProvider(
            final @NotNull String prefix,
            final @NotNull File file
    ) {
        this.prefix = prefix;
        this.file = file;
    }

    @Override
    public String create() {
        return prefix + file.getAbsolutePath();
    }
}