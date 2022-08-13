package com.qxy.mzbzy.ui.page;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.qxy.mzbzy.R;
import com.qxy.mzbzy.databinding.FragmentMoviesrankBinding;
import com.qxy.mzbzy.ui.App;
import com.qxy.mzbzy.ui.page.moviesrank.MoviesrankCinemaFragment;
import com.qxy.mzbzy.ui.page.moviesrank.MoviesrankInternetFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MoviesrankFragment extends Fragment {
    private MoviesrankViewModel vm;
    private FragmentMoviesrankBinding binding;
    public final List<Fragment> viewPagerList = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
        viewPagerList.add(new MoviesrankCinemaFragment());
        viewPagerList.add(new MoviesrankInternetFragment());
        ViewPager2 viewPager2 = binding.moviesrankViewPager;
        TabLayout tabLayout = binding.moviesrankTabLayOut;
        viewPager2.setAdapter(new FragmentStateAdapter(this) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return viewPagerList.get(position);
            }

            @Override
            public int getItemCount() {
                return viewPagerList.size();
            }
        });

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            //todo:更换string
            switch (position) {
                case 0:
                    tab.setText("首页");
                    break;
                case 1:
                    tab.setText("时间/单位");
                    break;
                default:
                    break;
            }
        });
        tabLayoutMediator.attach();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = App.getApp().getApplicationScopeViewModel(MoviesrankViewModel.class);

        // 获取本页面databinding对象
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_moviesrank,container,false);
        binding = FragmentMoviesrankBinding.inflate(inflater,container,false);
        // 设定ViewModel监听生命周期
        binding.setLifecycleOwner(this);
        // 为对象赋值
        binding.setVm(vm);
        binding.setClick(new ClickProxy());
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
            Toast.makeText(getContext(),"测试文本",Toast.LENGTH_SHORT).show();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                vm.mText.set(date);
            }
        }
    }

    public static class MoviesrankViewModel extends ViewModel {
        //LiveData与Observable均可，LiveData在重新监听时回
        // public final MutableLiveData<String> mText;
        public final ObservableField<String> mText;

        // 数据初始化
        public MoviesrankViewModel() {
            mText = new ObservableField<>();
            mText.set("This is home fragment");
        }

    }
}