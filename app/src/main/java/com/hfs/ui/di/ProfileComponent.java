package com.hfs.ui.di;

import com.hfs.ui.fragments.ActivityHistoryFragment;

import dagger.Component;

@PerUser
@Component(dependencies = AppComponent.class)
public interface ProfileComponent {

    void inject(ActivityHistoryFragment activityHistoryFragment);

    @Component.Builder
    interface Builder{

        Builder appComponent(AppComponent appComponent);

        ProfileComponent build();

    }

}
