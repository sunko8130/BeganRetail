package com.frontiertechnologypartners.beganretail.di.module;

import com.frontiertechnologypartners.beganretail.ui.home.HomeFragment;
import com.frontiertechnologypartners.beganretail.ui.sales.AddNewSaleItemFragment;
import com.frontiertechnologypartners.beganretail.ui.topup.PreTopupFragment;
import com.frontiertechnologypartners.beganretail.ui.topup.TopUpFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragment();

    @ContributesAndroidInjector
    abstract PreTopupFragment providePreTopupFragment();

    @ContributesAndroidInjector
    abstract TopUpFragment ProvideTopUpFragment();

}
