package dev.p2tch.reportsplugin.common.infrastructure.repository.report;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.p2tch.reportsplugin.common.domain.model.Report;
import dev.p2tch.reportsplugin.common.domain.repository.ReportRepository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.UUID;

public class OrmLiteReportRepositoryImpl implements ReportRepository {
    private final Dao<Report, UUID> dao;

    public OrmLiteReportRepositoryImpl(final @NotNull ConnectionSource connectionSource) {
        try {
            this.dao = DaoManager.createDao(connectionSource, Report.class);
        } catch (final SQLException e) {
            throw new RuntimeException("Failed to initialize Report DAO", e);
        }
    }

    @Override
    public Report findByID(final @NotNull UUID uuid) {
        try {
            return dao.queryForId(uuid);
        } catch (final SQLException e) {
            throw new RuntimeException("Error while fetching report by ID", e);
        }
    }

    @Override
    public void create(final @NotNull Report report) {
        try {
            dao.createOrUpdate(report);
        } catch (final SQLException e) {
            throw new RuntimeException("Error while creating report", e);
        }
    }

    @Override
    public void delete(final @NotNull UUID uuid) {
        try {
            dao.deleteById(uuid);
        } catch (final SQLException e) {
            throw new RuntimeException("Error while deleting report by ID", e);
        }
    }
}
