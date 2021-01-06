package com.frontiertechnologypartners.beganretail.di.module;

import com.frontiertechnologypartners.beganretail.ui.category.CategorySearchActivity;
import com.frontiertechnologypartners.beganretail.ui.home.MainActivity;
import com.frontiertechnologypartners.beganretail.ui.items.ItemsActivity;
import com.frontiertechnologypartners.beganretail.ui.login.LoginActivity;
import com.frontiertechnologypartners.beganretail.ui.sales.SaleItemUpdateFragment;
import com.frontiertechnologypartners.beganretail.ui.search_categories.MainCategoriesActivity;
import com.frontiertechnologypartners.beganretail.ui.payment.AddNewPaymentActivity;
import com.frontiertechnologypartners.beganretail.ui.payment.PaymentActivity;
import com.frontiertechnologypartners.beganretail.ui.pricing.PricingActivity;
import com.frontiertechnologypartners.beganretail.ui.receive_items.AddNewReceiveItemActivity;
import com.frontiertechnologypartners.beganretail.ui.receive_items.AddReceiveOrderItemActivity;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemDetailFragment;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemViewActivity;
import com.frontiertechnologypartners.beganretail.ui.receive_items.ReceiveItemsActivity;
import com.frontiertechnologypartners.beganretail.ui.register.RegisterActivity;
import com.frontiertechnologypartners.beganretail.ui.sales.AddNewSaleItemFragment;
import com.frontiertechnologypartners.beganretail.ui.sales.AddSalesActivity;
import com.frontiertechnologypartners.beganretail.ui.sales.SaleItemDetailFragment;
import com.frontiertechnologypartners.beganretail.ui.sales.SalesActivity;
import com.frontiertechnologypartners.beganretail.ui.sales.SalesOrdersActivity;
import com.frontiertechnologypartners.beganretail.ui.stock_balance.StockBalanceActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector()
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector()
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector()
    abstract SalesOrdersActivity bindSalesOrdersActivity();

    @ContributesAndroidInjector()
    abstract SalesActivity bindSalesActivity();

    @ContributesAndroidInjector()
    abstract ItemsActivity bindItemsActivity();

    @ContributesAndroidInjector()
    abstract CategorySearchActivity bindSubCategoryActivity();

    @ContributesAndroidInjector()
    abstract MainCategoriesActivity bindMainCategoriesActivity();

    @ContributesAndroidInjector()
    abstract ReceiveItemViewActivity bindReceiveItemViewActivity();

    @ContributesAndroidInjector()
    abstract AddNewPaymentActivity bindAddNewPaymentActivity();

    @ContributesAndroidInjector()
    abstract PaymentActivity bindPaymentActivity();

    @ContributesAndroidInjector()
    abstract AddReceiveOrderItemActivity bindAddReceiveOrderItemActivity();

    @ContributesAndroidInjector()
    abstract StockBalanceActivity bindStockBalanceActivity();

    @ContributesAndroidInjector()
    abstract AddSalesActivity bindAddSalesActivity();

    @ContributesAndroidInjector()
    abstract AddNewReceiveItemActivity bindAddNewReceiveItemActivity();

    @ContributesAndroidInjector()
    abstract ReceiveItemsActivity bindReceiveItemsActivity();

    @ContributesAndroidInjector()
    abstract PricingActivity bindPricingActivity();

    @ContributesAndroidInjector
    abstract AddNewSaleItemFragment bindAddNewSaleItemFragment();

    @ContributesAndroidInjector
    abstract ReceiveItemDetailFragment bindReceiveItemDetailFragment();

    @ContributesAndroidInjector
    abstract SaleItemDetailFragment bindSaleItemDetailFragment();

    @ContributesAndroidInjector
    abstract SaleItemUpdateFragment bindSaleItemUpdateFragment();

}
