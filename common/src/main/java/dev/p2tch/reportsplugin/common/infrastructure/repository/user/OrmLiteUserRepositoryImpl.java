package dev.p2tch.reportsplugin.common.infrastructure.repository.user;

import com.google.inject.Inject;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.p2tch.reportsplugin.common.domain.model.User;
import dev.p2tch.reportsplugin.common.domain.repository.DatabaseManager;
import dev.p2tch.reportsplugin.common.domain.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.UUID;

public class OrmLiteUserRepositoryImpl implements UserRepository {
    private final DatabaseManager databaseManager;
    private Dao<User, UUID> dao;

    @Inject
    public OrmLiteUserRepositoryImpl(final @NotNull DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void init() {
        try {
            this.dao = DaoManager.createDao(
                    databaseManager.getConnectionSource(),
                    User.class
            );
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(final @NotNull UUID uuid) {
        try {
            return dao.queryForId(uuid);
        } catch (final SQLException e) {
            throw new RuntimeException("Error while fetching user by ID", e);
        }
    }

    @Override
    public void save(final @NotNull User user) {
        try {
            dao.createOrUpdate(user);
        } catch (final SQLException e) {
            throw new RuntimeException("Error while saving user", e);
        }
    }

    @Override
    public void update(final @NotNull User user) {
        try {
            dao.update(user);
        } catch (final SQLException e) {
            throw new RuntimeException("Error while updating user", e);
        }
    }

    @Override
    public void delete(final @NotNull UUID uuid) {
        try {
            dao.deleteById(uuid);
        } catch (final SQLException e) {
            throw new RuntimeException("Error while deleting user by ID", e);
        }
    }
}
