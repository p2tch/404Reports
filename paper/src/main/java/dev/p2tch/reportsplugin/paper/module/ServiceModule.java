package dev.p2tch.reportsplugin.paper.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import dev.p2tch.reportsplugin.paper.service.NoticeService;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(NoticeService.class).in(Singleton.class);
    }
}
