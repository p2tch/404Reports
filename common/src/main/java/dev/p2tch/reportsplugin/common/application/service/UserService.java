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

    public User getUserByID(final @NotNull UUID uuid) {
        return userRepository.findById(uuid);
    }

    public void createUser(final @NotNull UUID uuid) {
        userRepository.save(
                new User(uuid)
        );
    }

    public void updateUser(final @NotNull User user) {
        userRepository.update(user);
    }

    public void deleteUser(final @NotNull UUID uuid) {
        userRepository.delete(uuid);
    }
}
