package dev.p2tch.reportsplugin.common.infrastructure.repository;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import dev.p2tch.reportsplugin.common.domain.model.User;
import dev.p2tch.reportsplugin.common.domain.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CachedUserRepositoryImpl implements UserRepository {
    private final UserRepository delegate;
    private final Cache<UUID, User> userCacheById;
    private final Cache<String, User> userCacheByName;

    public CachedUserRepositoryImpl(final @NotNull UserRepository delegate) {
        this.delegate = delegate;
        this.userCacheById = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
        this.userCacheByName = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public Optional<User> findById(final @NotNull UUID uuid) {
        final User cached = userCacheById.getIfPresent(uuid);

        if (cached != null) return Optional.of(cached);

        final Optional<User> user = delegate.findById(uuid);
        user.ifPresent(u -> {
            userCacheById.put(u.getUuid(), u);
            userCacheByName.put(u.getLastKnownName(), u);
        });

        return user;
    }

    @Override
    public Optional<User> findByNickname(final @NotNull String nickname) {
        final User cached = userCacheByName.getIfPresent(nickname);

        if (cached != null) {
            return Optional.of(cached);
        }

        final Optional<User> user = delegate.findByNickname(nickname);
        user.ifPresent(u -> {
            userCacheById.put(u.getUuid(), u);
            userCacheByName.put(u.getLastKnownName(), u);
        });

        return user;
    }

    @Override
    public void save(final @NotNull User user) {
        delegate.save(user);

        userCacheById.invalidate(user.getUuid());
        userCacheByName.invalidate(user.getLastKnownName());
    }

    @Override
    public void update(final @NotNull User user) {
        delegate.update(user);

        userCacheById.invalidate(user.getUuid());
        userCacheByName.invalidate(user.getLastKnownName());
    }

    @Override
    public void delete(final @NotNull UUID uuid) {
        delegate.delete(uuid);

        userCacheById.invalidate(uuid);
        userCacheByName.asMap().values().removeIf(user -> user.getUuid().equals(uuid));
    }
}
