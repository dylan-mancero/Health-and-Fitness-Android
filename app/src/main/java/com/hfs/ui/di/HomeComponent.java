package com.hfs.ui.di;

import com.hfs.ui.fragments.HomeFragment;

import dagger.Component;

@PerUser
@Component(dependencies = AppComponent.class, modules = RepoModule.class)
public interface HomeComponent {

    void inject(HomeFragment homeFragment);

    @Component.Builder
    interface Builder{

        Builder appComponent(AppComponent appComponent);

        HomeComponent build();

    }

}
