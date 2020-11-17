package com.frontiertechnologypartners.beganretail.di.module;

import com.frontiertechnologypartners.beganretail.network.NetworkRepository;
import com.frontiertechnologypartners.beganretail.network.RetrofitService;
import com.frontiertechnologypartners.beganretail.network.RetrofitService2;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.frontiertechnologypartners.beganretail.util.Constant.BASE_URL;
import static com.frontiertechnologypartners.beganretail.util.Constant.CONNECTION_TIMEOUT;
import static com.frontiertechnologypartners.beganretail.util.Constant.DEV_BASE_URL;

@Module(includes = {ViewModelModule.class})
public class ApplicationModule {

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS)
                .build();
    }

    @Provides
    @Singleton
    @Named("retrofit_1")
    static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    @Named("retrofit_2")
    static Retrofit provideRetrofit2(OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(DEV_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static RetrofitService provideRetrofitService(@Named("retrofit_1") Retrofit retrofit) {
        return retrofit.create(RetrofitService.class);
    }

    @Singleton
    @Provides
    static RetrofitService2 provideRetrofitService2(@Named("retrofit_2") Retrofit retrofit) {
        return retrofit.create(RetrofitService2.class);
    }


    @Singleton
    @Provides
    static NetworkRepository provideNetworkRepository(RetrofitService retrofitService, RetrofitService2 retrofitService2) {
        return new NetworkRepository(retrofitService, retrofitService2);
    }


//
//    @Singleton
//    @Provides
//    @Named("retrofit_service_2")
//    static RetrofitService provideRetrofitService2(@Named("retrofit_2") Retrofit retrofit2) {
//        return retrofit2.create(RetrofitService.class);
//    }
//
//
//    @Singleton
//    @Provides
//    static NetworkRepository provideNetworkRepository2(@Named("retrofit_service_2") RetrofitService retrofitService2) {
//        return new NetworkRepository(retrofitService2);
//    }

}
