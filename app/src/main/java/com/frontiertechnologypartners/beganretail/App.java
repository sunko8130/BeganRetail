package com.frontiertechnologypartners.beganretail;

import com.frontiertechnologypartners.beganretail.di.component.ApplicationComponent;
import com.frontiertechnologypartners.beganretail.di.component.DaggerApplicationComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import io.paperdb.Paper;

public class App extends DaggerApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Paper.init(this);
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        ApplicationComponent component = DaggerApplicationComponent.builder().application(this).build();
        component.inject(this);

        return component;
    }
}
