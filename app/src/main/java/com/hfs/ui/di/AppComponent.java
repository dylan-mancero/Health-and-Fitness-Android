package com.hfs.ui.di;

import com.hfs.lib.StandardProfile;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ProfileModule.class)
public interface AppComponent {

    StandardProfile standardProfile();

    @Component.Builder
    interface Builder{
        Builder profileModule(ProfileModule profileModule);

        AppComponent build();
    }

}
