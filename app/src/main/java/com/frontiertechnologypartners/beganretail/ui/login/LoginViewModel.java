package com.frontiertechnologypartners.beganretail.ui.login;

import com.frontiertechnologypartners.beganretail.model.LoginResponse;
import com.frontiertechnologypartners.beganretail.model.RegisterResponse;
import com.frontiertechnologypartners.beganretail.network.ApiResponse;
import com.frontiertechnologypartners.beganretail.network.NetworkRepository;
import com.frontiertechnologypartners.beganretail.ui.base.BaseViewModel;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends BaseViewModel {
    private NetworkRepository networkRepository;

    @Inject
    public LoginViewModel(NetworkRepository networkRepository) {
        this.networkRepository = networkRepository;
    }

    public void login(Map<String, Object> fields) {
        disposable.add(networkRepository.login(fields)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> loginResponse.setValue(ApiResponse.loading()))
                .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
                    @Override
                    public void onSuccess(LoginResponse value) {
                        loginResponse.setValue(ApiResponse.success(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        loginResponse.setValue(ApiResponse.error(getErrorMessage(e)));
                    }
                }));
    }

//    public void login(String loginName, String loginPassword) {
//        disposable.add(networkRepository.login(loginName,loginPassword)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doOnSubscribe(__ -> loginResponse.setValue(ApiResponse.loading()))
//                .subscribeWith(new DisposableSingleObserver<LoginResponse>() {
//                    @Override
//                    public void onSuccess(LoginResponse value) {
//                        loginResponse.setValue(ApiResponse.success(value));
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        loginResponse.setValue(ApiResponse.error(getErrorMessage(e)));
//                    }
//                }));
//    }
}
