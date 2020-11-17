package com.frontiertechnologypartners.beganretail.ui.base;


import com.frontiertechnologypartners.beganretail.network.ApiResponse;

import java.io.IOException;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;

import static com.frontiertechnologypartners.beganretail.util.Constant.NO_INTERNET_CONNECTION;
import static com.frontiertechnologypartners.beganretail.util.Constant.UNKNOWN_ERROR;


public class BaseViewModel extends ViewModel {
    protected final CompositeDisposable disposable = new CompositeDisposable();
    public final MutableLiveData<ApiResponse> statesTownshipsResponse = new MutableLiveData<>();
    public final MutableLiveData<ApiResponse> nrcFormatResponse = new MutableLiveData<>();
    public final MutableLiveData<ApiResponse> registerResponse = new MutableLiveData<>();

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }

    protected String getErrorMessage(Throwable errorMessage) {
        if (errorMessage instanceof HttpException) {
            return errorMessage.getMessage();
        } else if (errorMessage instanceof IOException) {
            return NO_INTERNET_CONNECTION;
        } else {
            return UNKNOWN_ERROR;
        }
    }
}
