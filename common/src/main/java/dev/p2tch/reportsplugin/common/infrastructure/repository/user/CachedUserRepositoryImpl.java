package dev.p2tch.reportsplugin.common.infrastructure.repository.user;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.inject.Inject;
import dev.p2tch.reportsplugin.common.domain.model.User;
import dev.p2tch.reportsplugin.common.domain.repository.UserRepository;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CachedUserRepositoryImpl implements UserRepository {
    private final OrmLiteUserRepositoryImpl delegate;
    private final Cache<UUID, User> cache;

    @Inject
    public CachedUserRepositoryImpl(final @NotNull OrmLiteUserRepositoryImpl delegate) {
        this.delegate = delegate;
        this.cache = Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .build();
    }

    @Override
    public User findById(final @NotNull UUID uuid) {
        final User cached = cache.getIfPresent(uuid);

        if (cached != null) return cached;

        final User user = delegate.findById(uuid);
        if (user != null) {
            cache.put(user.getUuid(), user);
        }

        return user;
    }

    @Override
    public void save(final @NotNull User user) {
        delegate.save(user);

        cache.invalidate(user.getUuid());
    }

    @Override
    public void update(final @NotNull User user) {
        delegate.update(user);

        cache.invalidate(user.getUuid());
    }

    @Override
    public void delete(final @NotNull UUID uuid) {
        delegate.delete(uuid);

        cache.invalidate(uuid);
    }
}
