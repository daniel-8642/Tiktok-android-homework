package com.qxy.mzbzy.ui;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

public class App extends Application implements ViewModelStoreOwner {
    private ViewModelProvider mApplicationProvider;
    private ViewModelStore mAppViewModelStore;

    public  <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider(this);
        }
        return mApplicationProvider.get(modelClass);
    }

    private static Application application;

    @Override
    public void onCreate() {
        super.onCreate();
        application =this;
        mAppViewModelStore=new ViewModelStore();
    }


    public static Application getInstance() {
        return application;
    }

    public static App getApp(){
        return (App)application;
    }

    @NonNull
    @Override
    public ViewModelStore getViewModelStore() {
        return mAppViewModelStore;
    }
}
