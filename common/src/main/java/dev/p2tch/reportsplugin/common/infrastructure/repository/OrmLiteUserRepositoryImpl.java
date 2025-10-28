package dev.p2tch.reportsplugin.common.infrastructure.repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import dev.p2tch.reportsplugin.common.domain.model.User;
import dev.p2tch.reportsplugin.common.domain.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class OrmLiteUserRepositoryImpl implements UserRepository {
    private final Dao<User, UUID> dao;

    public OrmLiteUserRepositoryImpl(final @NotNull ConnectionSource connectionSource) {
        try {
            this.dao = DaoManager.createDao(connectionSource, User.class);
        } catch (final SQLException e) {
            throw new RuntimeException("Failed to initialize User DAO", e);
        }
    }

    @Override
    public Optional<User> findById(final @NotNull UUID uuid) {
        try {
            return Optional.ofNullable(dao.queryForId(uuid));
        } catch (final SQLException e) {
            throw new RuntimeException("Error while fetching user by ID", e);
        }
    }

    @Override
    public Optional<User> findByNickname(final @NotNull String nickname) {
        try {
            return Optional.ofNullable(dao.queryForFirst(dao.queryBuilder().where().eq("lastKnownName", nickname).prepare()));
        } catch (final SQLException e) {
            throw new RuntimeException("Error while fetching user by nickname", e);
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
