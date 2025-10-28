package dev.p2tch.reportsplugin.common.application.service;

import dev.p2tch.reportsplugin.common.domain.model.User;
import dev.p2tch.reportsplugin.common.domain.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserRepository userRepository;

    public UserService(final @NotNull UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByID(final @NotNull UUID uuid) {
        return userRepository.findById(uuid);
    }

    public Optional<User> getUserByName(final @NotNull String username) {
        return userRepository.findByUsername(username);
    }

    public void createUser(final @NotNull UUID uuid, final @NotNull String username) {
        userRepository.save(new User(uuid, username));
    }

    public void updateUser(final @NotNull User user) {
        userRepository.update(user);
    }

    public void deleteUser(final @NotNull UUID uuid) {
        userRepository.delete(uuid);
    }
}
