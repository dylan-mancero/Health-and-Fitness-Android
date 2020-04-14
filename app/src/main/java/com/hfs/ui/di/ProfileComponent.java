package com.hfs.ui.di;

import com.hfs.ui.fragments.ActivityHistoryFragment;
import com.hfs.ui.fragments.AddFoodItemFragment;
import com.hfs.ui.fragments.FoodHistoryFragment;

import dagger.Component;

@PerUser
@Component(dependencies = AppComponent.class)
public interface ProfileComponent {

    void inject(AddFoodItemFragment addFoodItemFragment);

    void inject(ActivityHistoryFragment activityHistoryFragment);

    void inject(FoodHistoryFragment foodHistoryFrag);

    @Component.Builder
    interface Builder{

        Builder appComponent(AppComponent appComponent);

        ProfileComponent build();

    }

}
