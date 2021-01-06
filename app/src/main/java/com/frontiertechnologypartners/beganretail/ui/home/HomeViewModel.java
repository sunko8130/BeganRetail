package com.frontiertechnologypartners.beganretail.ui.home;

import com.frontiertechnologypartners.beganretail.model.BalanceResponse;
import com.frontiertechnologypartners.beganretail.model.MerchantItemsResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.network.NetworkRepository;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {
    private NetworkRepository networkRepository;

    @Inject
    public HomeViewModel(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public void balance(int id) {
        disposable.add(networkRepository.balance(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> balanceResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<BalanceResponse>() {
                    @Override
                    public void onSuccess(BalanceResponse value) {
                        balanceResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        balanceResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }
}
