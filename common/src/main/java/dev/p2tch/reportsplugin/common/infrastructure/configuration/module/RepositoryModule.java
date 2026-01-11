package dev.p2tch.reportsplugin.common.infrastructure.configuration.module;

import com.google.inject.AbstractModule;
import dev.p2tch.reportsplugin.common.domain.repository.UserRepository;
import dev.p2tch.reportsplugin.common.infrastructure.repository.user.CachedUserRepositoryImpl;
import dev.p2tch.reportsplugin.common.infrastructure.repository.user.OrmLiteUserRepositoryImpl;

public class RepositoryModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserRepository.class)
                .to(CachedUserRepositoryImpl.class)
                .asEagerSingleton();

        bind(OrmLiteUserRepositoryImpl.class)
                .asEagerSingleton();
    }
}
