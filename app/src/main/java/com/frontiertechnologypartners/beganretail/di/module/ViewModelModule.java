package com.frontiertechnologypartners.beganretail.di.module;

import com.frontiertechnologypartners.beganretail.di.keys.ViewModelKey;
import com.frontiertechnologypartners.beganretail.ui.register.RegisterViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegisterViewModel.class)
    abstract ViewModel bindRegisterViewModel(RegisterViewModel registerViewModel);

    //    @Binds
//    @IntoMap
//    @ViewModelKey(LoginViewModel.class)
//    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);
//

//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ProductsViewModel.class)
//    abstract ViewModel bindProductsViewModel(ProductsViewModel productsViewModel);
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(PurchaseViewModel.class)
//    abstract ViewModel bindPurchaseViewModel(PurchaseViewModel purchaseViewModel);
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ChangePassViewModel.class)
//    abstract ViewModel bindChangePassViewModel(ChangePassViewModel changePassViewModel);
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ForgotPassViewModel.class)
//    abstract ViewModel bindForgotPassViewModel(ForgotPassViewModel forgotPassViewModel);
//
//    @Binds
//    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
