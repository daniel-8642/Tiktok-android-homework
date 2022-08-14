package com.qxy.mzbzy.ui.page.moviesrank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.mzbzy.R;
import com.qxy.mzbzy.data.bean.Rank;
import com.qxy.mzbzy.databinding.FragmentMoviesrankInternetBinding;
import com.qxy.mzbzy.ui.App;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MoviesrankInternetFragment extends Fragment {
private MoviesrankInternetViewModel vm;
private FragmentMoviesrankInternetBinding binding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = App.getApp().getApplicationScopeViewModel(MoviesrankInternetFragment.MoviesrankInternetViewModel.class);

        // 获取本页面databinding对象
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_moviesrank_internet,container,false);
        // 设定ViewModel监听生命周期
        binding.setLifecycleOwner(this);
        // 为对象赋值
        binding.setVm(vm);
        binding.setClick(new MoviesrankInternetFragment.ClickProxy());

        binding.recycler.setAdapter(new mAdapter());
        // 创建一个线性布局管理器
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        // 默认是Vertical，可以不写
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.recycler.setLayoutManager(mLayoutManager);

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

    public static class MoviesrankInternetViewModel extends ViewModel {
        //LiveData与Observable均可
        // public final MutableLiveData<String> mText;
        public final ObservableField<String> mText;
        public final ObservableField<List<Rank.Data.MList>> list = new ObservableField<>(new ArrayList<>());
        // 数据初始化
        public MoviesrankInternetViewModel() {
            mText = new ObservableField<>();
            mText.set("This is home fragment");
        }

    }
    public class mAdapter extends RecyclerView.Adapter<MyHolder>  {

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.title.setText(""+(position+1));
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }
    public static class MyHolder extends RecyclerView.ViewHolder {
        TextView title, star,type, hot;
        ImageView imege;

        public MyHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imege = itemView.findViewById(R.id.image);
            star = itemView.findViewById(R.id.star);
            type = itemView.findViewById(R.id.type);
            hot = itemView.findViewById(R.id.hot);
        }
    }


}
