package dev.p2tch.reportsplugin.common.domain.repository;

import dev.p2tch.reportsplugin.common.domain.model.Report;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface ReportRepository {
    Report findByID(final @NotNull UUID uuid);

    void create(final @NotNull Report report);
    void delete(final @NotNull UUID uuid);
}
