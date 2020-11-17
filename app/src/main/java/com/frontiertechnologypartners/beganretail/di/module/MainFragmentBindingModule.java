package com.frontiertechnologypartners.beganretail.di.module;

import com.frontiertechnologypartners.beganretail.ui.home.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

//    @ContributesAndroidInjector
//    abstract CreateVendorFragment provideNewVendorFragment();
//
//    @ContributesAndroidInjector
//    abstract VendorListFragment provideVendorListFragment();
//
//    @ContributesAndroidInjector
//    abstract NewPurchaseFragment provideNewPurchaseFragment();
//
//    @ContributesAndroidInjector
//    abstract ProductFragment provideProductFragment();
//
//    @ContributesAndroidInjector
//    abstract AvailableProductsFragment provideAvailableProductsFragment();

    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragment();
//
//    @ContributesAndroidInjector
//    abstract PurchaseListFragment providePurchaseListFragment();
//
//    @ContributesAndroidInjector
//    abstract ChangePasswordFragment provideChangePasswordFragment();
//
//    @ContributesAndroidInjector
//    abstract PurchaseReportFragment providePurchaseReportFragment();

}
