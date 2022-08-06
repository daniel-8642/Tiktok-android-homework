package com.qxy.mzbzy.ui.state;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {
    //LiveData与Observable均可，LiveData在重新监听时回
    // public final MutableLiveData<String> mText;
    public final ObservableField<String> mText;

    // 数据初始化
    public HomeViewModel() {
        mText = new ObservableField<>();
        mText.set("This is home fragment");
    }

}