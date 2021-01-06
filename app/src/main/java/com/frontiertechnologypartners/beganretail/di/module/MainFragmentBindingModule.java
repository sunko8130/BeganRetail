package com.frontiertechnologypartners.beganretail.di.module;

import com.frontiertechnologypartners.beganretail.ui.home.HomeFragment;
import com.frontiertechnologypartners.beganretail.ui.sales.AddNewSaleItemFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragment();

}
