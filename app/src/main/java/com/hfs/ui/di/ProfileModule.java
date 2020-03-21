package com.hfs.ui.di;

import com.hfs.lib.StandardProfile;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule {

    private static ProfileModule instance;

    private StandardProfile standardProfile;

    public ProfileModule(StandardProfile standardProfile) {
        this.standardProfile = standardProfile;
    }

    public static ProfileModule getInstance(StandardProfile standardProfile) {
        if(instance == null) {
            instance = new ProfileModule(standardProfile);
        }

        return instance;
    }

    @Singleton
    @Provides
    StandardProfile provideStandardProfile(){
        return this.standardProfile;
    }
}
