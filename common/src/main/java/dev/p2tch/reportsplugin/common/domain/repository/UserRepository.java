package dev.p2tch.reportsplugin.common.domain.repository;

import dev.p2tch.reportsplugin.common.domain.model.User;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    Optional<User> findById(final @NotNull UUID uuid);
    Optional<User> findByNickname(final @NotNull String nickname);

    void save(final @NotNull User user);
    void update(final @NotNull User user);
    void delete(final @NotNull UUID uuid);
}
