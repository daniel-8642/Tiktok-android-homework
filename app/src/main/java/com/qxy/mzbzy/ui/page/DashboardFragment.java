package com.qxy.mzbzy.ui.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.mzbzy.R;
import com.qxy.mzbzy.databinding.FragmentDashboardBinding;
import com.qxy.mzbzy.ui.App;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardFragment extends Fragment {
    private DashboardViewModel vm;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = App.getApp().getApplicationScopeViewModel(DashboardViewModel.class);

        // 获取本页面databinding对象
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dashboard, container, false);
        // 设定ViewModel监听生命周期
        binding.setLifecycleOwner(this);
        // 为对象赋值
        binding.setVm(vm);
        binding.setClick(new DashboardFragment.ClickProxy());
        // 返回根view
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        vm = null;
    }

    public class ClickProxy {
        public void test() {
            Toast.makeText(getContext(),"Dash测试文本",Toast.LENGTH_SHORT).show();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                vm.mText.setValue(date);
            }
        }
    }
    public static class DashboardViewModel extends ViewModel {

        //LiveData与Observable均可，LiveData在重新监听时回
        public final MutableLiveData<String> mText;
        // public final ObservableField<String> mText;

        // 数据初始化
        public DashboardViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is dashboard fragment");
        }
    }
}