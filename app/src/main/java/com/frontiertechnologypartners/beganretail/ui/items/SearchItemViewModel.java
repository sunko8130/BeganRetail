package com.frontiertechnologypartners.beganretail.ui.items;

import com.frontiertechnologypartners.beganretail.model.CategoryResponse;
import com.frontiertechnologypartners.beganretail.model.ItemSearchResponse;
import com.frontiertechnologypartners.beganretail.model.MerchantItemsResponse;
import com.frontiertechnologypartners.beganretail.model.StockBalanceResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.network.NetworkRepository;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SearchItemViewModel extends BaseViewModel {
    private NetworkRepository networkRepository;

    @Inject
    public SearchItemViewModel(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public void allCategory() {
        disposable.add(networkRepository.allCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> categoryResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<CategoryResponse>() {
                    @Override
                    public void onSuccess(CategoryResponse value) {
                        categoryResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        categoryResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

    public void allMerchantItems(int id) {
        disposable.add(networkRepository.allMerchantItems(id)
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

    public void searchItems(int categoryId, int itemId, int merchantId) {
        disposable.add(networkRepository.searchItems(categoryId, itemId, merchantId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> searchItemResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<ItemSearchResponse>() {
                    @Override
                    public void onSuccess(ItemSearchResponse value) {
                        searchItemResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        searchItemResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }
}
