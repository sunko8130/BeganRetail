package com.frontiertechnologypartners.beganretail.ui.topup;

import com.frontiertechnologypartners.beganretail.model.PreTopUpResponse;
import com.frontiertechnologypartners.beganretail.model.ProvidersResponse;
import com.frontiertechnologypartners.beganretail.model.SVAResponse;
import com.frontiertechnologypartners.beganretail.model.TopUpResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.network.NetworkRepository;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TopupViewModel extends BaseViewModel {
    private NetworkRepository networkRepository;

    @Inject
    TopupViewModel(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    void getAvailableProviders() {
        disposable.add(networkRepository.getProviders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> providersResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<ProvidersResponse>() {
                    @Override
                    public void onSuccess(ProvidersResponse value) {
                        providersResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        providersResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

    void getPreTopUpData(String userId, String amount,String provider) {
        disposable.add(networkRepository.preTopUp(userId, amount,provider)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> preTopUpResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<PreTopUpResponse>() {
                    @Override
                    public void onSuccess(PreTopUpResponse value) {
                        preTopUpResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        preTopUpResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

    void topUp(String userId, String provider, String mobile, String amount,String discountAmount) {
        disposable.add(networkRepository.topUp(userId, provider, mobile, amount,discountAmount)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> topUpResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<TopUpResponse>() {
                    @Override
                    public void onSuccess(TopUpResponse value) {
                        topUpResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        topUpResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

    public void refreshAmount(int userId){
        disposable.add(networkRepository.refreshAmount(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> svaResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<SVAResponse>() {
                    @Override
                    public void onSuccess(SVAResponse value) {
                        svaResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        svaResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }
}
