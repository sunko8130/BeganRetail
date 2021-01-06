package com.frontiertechnologypartners.beganretail.di.module;

import com.frontiertechnologypartners.beganretail.common.ViewModelFactory;
import com.frontiertechnologypartners.beganretail.di.keys.ViewModelKey;
import com.frontiertechnologypartners.beganretail.model.BalanceResponse;
import com.frontiertechnologypartners.beganretail.ui.category.CategoryViewModel;
import com.frontiertechnologypartners.beganretail.ui.home.HomeViewModel;
import com.frontiertechnologypartners.beganretail.ui.items.SearchItemViewModel;
import com.frontiertechnologypartners.beganretail.ui.login.LoginViewModel;
import com.frontiertechnologypartners.beganretail.ui.payment.PaymentViewModel;
import com.frontiertechnologypartners.beganretail.ui.pricing.PricingViewModel;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemsViewModel;
import com.frontiertechnologypartners.beganretail.ui.register.RegisterViewModel;
import com.frontiertechnologypartners.beganretail.ui.sales.SalesViewModel;
import com.frontiertechnologypartners.beganretail.ui.stock_balance.StockBalanceViewModel;

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

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel.class)
    abstract ViewModel bindLoginViewModel(LoginViewModel loginViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ReceiveItemsViewModel.class)
    abstract ViewModel bindReceiveItemsViewModel(ReceiveItemsViewModel receiveItemsViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel.class)
    abstract ViewModel bindCategoryViewModel(CategoryViewModel categoryViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(StockBalanceViewModel.class)
    abstract ViewModel bindStockBalanceViewModel(StockBalanceViewModel stockBalanceViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PricingViewModel.class)
    abstract ViewModel bindPricingViewModel(PricingViewModel pricingViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SalesViewModel.class)
    abstract ViewModel bindSalesViewModel(SalesViewModel salesViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel homeViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(SearchItemViewModel.class)
    abstract ViewModel bindSearchItemViewModel(SearchItemViewModel searchItemViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PaymentViewModel.class)
    abstract ViewModel bindPaymentViewModel(PaymentViewModel paymentViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
