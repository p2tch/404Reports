package dev.p2tch.reportsplugin.common.application.service;

import dev.p2tch.reportsplugin.common.domain.model.Report;
import dev.p2tch.reportsplugin.common.domain.repository.ReportRepository;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class ReportService {
    private final ReportRepository reportRepository;

    public ReportService(final @NotNull ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public Report findByID(final @NotNull UUID uuid) {
        return reportRepository.findByID(uuid);
    }

    public void create(final @NotNull Report report) {
        reportRepository.create(report);
    }

    public void delete(final @NotNull UUID uuid) {
        reportRepository.delete(uuid);
    }
}
