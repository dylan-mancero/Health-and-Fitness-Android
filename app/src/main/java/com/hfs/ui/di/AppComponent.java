package com.hfs.ui.di;

import com.hfs.lib.StandardProfile;
import com.hfs.lib.repo.Activities;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ProfileModule.class, RepoModule.class})
public interface AppComponent {

    StandardProfile standardProfile();

    Activities activities();

    @Component.Builder
    interface Builder{
        Builder profileModule(ProfileModule profileModule);

        Builder repoModule(RepoModule repoModule);

        AppComponent build();
    }

}
