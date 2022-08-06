package com.qxy.mzbzy.ui.state;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NotificationsViewModel extends ViewModel {

    //LiveData与Observable均可，LiveData在重新监听时回
    public final MutableLiveData<String> mText;
    // public final ObservableField<String> mText;

    // 数据初始化
    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }
}