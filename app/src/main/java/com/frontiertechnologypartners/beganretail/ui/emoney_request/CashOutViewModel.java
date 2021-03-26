package com.frontiertechnologypartners.beganretail.ui.emoney_request;

import com.frontiertechnologypartners.beganretail.model.CashOutTransactionResponse;
import com.frontiertechnologypartners.beganretail.model.CashoutResponse;
import com.frontiertechnologypartners.beganretail.model.LoginResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.network.NetworkRepository;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewModel;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class CashOutViewModel extends BaseViewModel {
    private NetworkRepository networkRepository;

    @Inject
    public CashOutViewModel(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public void cashout(String userId, String amount) {
        disposable.add(networkRepository.cashOut(userId, amount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> cashoutResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<CashoutResponse>() {
                    @Override
                    public void onSuccess(CashoutResponse value) {
                        cashoutResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        cashoutResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

    public void cashoutSearch(String userId, String status, String reqStartDate, String reqEndDate,
                              String reqDeliStartDate, String reqDeliEndDate) {
        disposable.add(networkRepository.cashOutSearch(userId, status, reqStartDate, reqEndDate,
                reqDeliStartDate, reqDeliEndDate)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> cashoutTransactionResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<CashOutTransactionResponse>() {
                    @Override
                    public void onSuccess(CashOutTransactionResponse value) {
                        cashoutTransactionResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        cashoutTransactionResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

}
