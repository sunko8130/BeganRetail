package com.frontiertechnologypartners.beganretail.ui.pricing;

import com.frontiertechnologypartners.beganretail.model.DeliveryOrdersResponse;
import com.frontiertechnologypartners.beganretail.model.ItemsPriceResponse;
import com.frontiertechnologypartners.beganretail.model.ItemsResponse;
import com.frontiertechnologypartners.beganretail.model.MerchantItemsResponse;
import com.frontiertechnologypartners.beganretail.model.SetSellingPriceResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.network.NetworkRepository;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PricingViewModel extends BaseViewModel {
    private NetworkRepository networkRepository;

    @Inject
    public PricingViewModel(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public void merchantItems(int id) {
        disposable.add(networkRepository.merchantItems(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> merchantItemsResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<MerchantItemsResponse>() {
                    @Override
                    public void onSuccess(MerchantItemsResponse value) {
                        merchantItemsResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        merchantItemsResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

    public void itemsPriceSearch(String itemCode,int id) {
        disposable.add(networkRepository.itemsPriceSearch(itemCode,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> itemsPriceResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<ItemsPriceResponse>() {
                    @Override
                    public void onSuccess(ItemsPriceResponse value) {
                        itemsPriceResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        itemsPriceResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

    public void setSellingPrice(int id, double price) {
        disposable.add(networkRepository.setSellingPrice(id, price)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> setSellingPriceResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<SetSellingPriceResponse>() {
                    @Override
                    public void onSuccess(SetSellingPriceResponse value) {
                        setSellingPriceResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        setSellingPriceResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }
}
