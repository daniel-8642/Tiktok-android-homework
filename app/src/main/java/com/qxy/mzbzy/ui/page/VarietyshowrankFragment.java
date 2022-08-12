package com.qxy.mzbzy.ui.page;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.qxy.mzbzy.R;
import com.qxy.mzbzy.data.bean.Test;
import com.qxy.mzbzy.data.repository.TestRepository;
import com.qxy.mzbzy.data.response.DataResult;
import com.qxy.mzbzy.databinding.FragmentVarietyshowrankBinding;
import com.qxy.mzbzy.ui.App;

public class VarietyshowrankFragment extends Fragment {
    private VarietyshowrankViewModel vm;
    private FragmentVarietyshowrankBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = App.getApp().getApplicationScopeViewModel(VarietyshowrankViewModel.class);

        // 获取本页面databinding对象
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_varietyshowrank, container, false);
        // 设定ViewModel监听生命周期
        binding.setLifecycleOwner(this);
        // 为对象赋值
        binding.setVm(vm);
        binding.setClick(new VarietyshowrankFragment.ClickProxy());
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
            //Toast.makeText(getContext(),"Dash测试文本",Toast.LENGTH_SHORT).show();

            // 获取仓库对象
            TestRepository repository = TestRepository.getInstance();
            //发起网络请求，设置回调函数callback
            // 网络请求成功，获取到test对象，调用callback（vm.setTestdata(test)）处理
            // vm.setTestdata(test) 将拆解test，将返回值放置到页面
            repository.getTestData(test -> vm.setTestdata(test));

//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String date = sdf.format(new Date());
//                vm.mText.setValue(date);
//            }
        }
    }
    public static class VarietyshowrankViewModel extends ViewModel {

        //LiveData与Observable均可，LiveData在重新监听时回
        public final MutableLiveData<String> mText;
        // public final ObservableField<String> mText;

        // 数据初始化
        public VarietyshowrankViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is dashboard fragment");
        }
        // vm.setTestdata(test) 将拆解test，将返回值放置到页面
        public void setTestdata(DataResult<Test> result){
            Test test = result.getResult();
            mText.setValue(test.test);
            Log.d("http",test.test);
        }
    }
}