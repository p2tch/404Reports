package dev.p2tch.reportsplugin.common.infrastructure.repository.report;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.p2tch.reportsplugin.common.domain.model.Report;
import dev.p2tch.reportsplugin.common.domain.repository.ReportRepository;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CachedReportRepositoryImpl implements ReportRepository {
    private final ReportRepository delegate;
    private final Cache<UUID, Report> cache;

    public CachedReportRepositoryImpl(final @NotNull ReportRepository delegate) {
        this.delegate = delegate;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public Report findByID(final @NotNull UUID uuid) {
        final Report cached = cache.getIfPresent(uuid);

        if (cached != null) return cached;

        final Report report = delegate.findByID(uuid);
        if (report != null) {
            cache.put(report.getUuid(), report);
        }

        return report;
    }

    @Override
    public void create(final @NotNull Report report) {
        delegate.create(report);

        cache.invalidate(report.getUuid());
    }

    @Override
    public void delete(final @NotNull UUID uuid) {
        delegate.delete(uuid);

        cache.invalidate(uuid);
    }
}
