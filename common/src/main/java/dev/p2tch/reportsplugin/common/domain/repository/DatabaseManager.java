package dev.p2tch.reportsplugin.common.domain.repository;

import com.j256.ormlite.support.ConnectionSource;

import java.util.concurrent.CompletableFuture;

public interface DatabaseManager {
    void connect();
    CompletableFuture<Void> connectAndInitAsync();
    void close();
    ConnectionSource getConnectionSource();
}
