package dev.p2tch.reportsplugin.common.domain.repository;

import com.j256.ormlite.support.ConnectionSource;

public interface DatabaseManager {
    void connect();
    void close();
    ConnectionSource getConnectionSource();
}
