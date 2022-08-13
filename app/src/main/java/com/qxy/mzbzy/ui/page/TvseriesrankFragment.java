package com.qxy.mzbzy.ui.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.mzbzy.R;
import com.qxy.mzbzy.data.bean.Test2;
import com.qxy.mzbzy.data.repository.Test2Repository;
import com.qxy.mzbzy.databinding.FragmentTvseriesrankBinding;
import com.qxy.mzbzy.ui.App;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TvseriesrankFragment extends Fragment {
    private TvseriesrankViewModel vm;
    private FragmentTvseriesrankBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = App.getApp().getApplicationScopeViewModel(TvseriesrankViewModel.class);

        // 获取本页面databinding对象
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tvseriesrank, container, false);
        // 设定ViewModel监听生命周期
        binding.setLifecycleOwner(this);
        // 为对象赋值
        binding.setVm(vm);
        binding.setClick(new TvseriesrankFragment.ClickProxy());
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
        public void testA() {
            Test2Repository repository = Test2Repository.getInstance(getContext());
            SimpleDateFormat myFormat=new SimpleDateFormat("HH:mm:ss");
            new Thread(()->{
                repository.Test2Dao().insert(new Test2(
                        myFormat.format(new Date(System.currentTimeMillis())),"A"));
                this.reload();
            }).start();
//            Toast.makeText(getContext(), "Noti测试文本", Toast.LENGTH_SHORT).show();
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String date = sdf.format(new Date());
//                vm.mText.setValue(date);
//            }
        }
        public void testB() {
            Test2Repository repository = Test2Repository.getInstance(getContext());
            SimpleDateFormat myFormat=new SimpleDateFormat("HH:mm:ss");
            new Thread(()->{
                repository.Test2Dao().insert(new Test2(
                        myFormat.format(new Date(System.currentTimeMillis())),"B"));
                this.reload();
            }).start();
        }
        public void clear() {
            Test2Repository repository = Test2Repository.getInstance(getContext());
            new Thread(()->{
                repository.Test2Dao().clear();
                this.reload();
            }).start();
        }

        public void reloadTest(){
            new Thread(this::reload).start();
        }

        public void reload(){
            Test2Repository repository = Test2Repository.getInstance(getContext());
            List<Test2> list = repository.Test2Dao().getList();
            StringBuilder str = new StringBuilder();
            for (Test2 i:list) {
                str.append(i.name);
                str.append(" : ");
                str.append(i.time);
                str.append("\n");
            }
            vm.mText.postValue(str.toString());
        }
    }
    public static class TvseriesrankViewModel extends ViewModel {

        //LiveData与Observable均可，LiveData在重新监听时回
        public final MutableLiveData<String> mText;
        // public final ObservableField<String> mText;

        // 数据初始化
        public TvseriesrankViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is tvseriesrank fragment");
        }
    }

}