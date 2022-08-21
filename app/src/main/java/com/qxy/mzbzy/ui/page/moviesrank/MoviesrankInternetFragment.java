package com.qxy.mzbzy.ui.page.moviesrank;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableField;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.mzbzy.R;
import com.qxy.mzbzy.data.api.CacheDao;
import com.qxy.mzbzy.data.bean.Cache;
import com.qxy.mzbzy.data.bean.Rank;
import com.qxy.mzbzy.data.repository.CacheRepository;
import com.qxy.mzbzy.data.repository.RankRepository;
import com.qxy.mzbzy.databinding.FragmentMoviesrankInternetBinding;
import com.qxy.mzbzy.databinding.ItemMoviesBinding;
import com.qxy.mzbzy.ui.App;

import java.text.SimpleDateFormat;
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
        binding.setAdapter(new MAdapter());
        // 创建一个线性布局管理器
        // LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        // 默认是Vertical，可以不写
        // mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        // binding.recycler.setLayoutManager(mLayoutManager);

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

        // 数据初始化
        public MoviesrankInternetViewModel() {
            mText = new ObservableField<>();
            mText.set("This is home fragment");
        }

    }

    public class MAdapter extends RecyclerView.Adapter<MAdapter.MyHolder>  {
        private List<Rank.Data.MList> list;

        public List<Rank.Data.MList> getList() {
            return list;
        }
        // 请求云端上mock接口
        {
            CacheDao cacheDao = CacheRepository.getInstance(getContext()).CacheDao();
            new Thread(() -> {
                Cache cache = cacheDao.getByName("moviesrankI");
                if (cache != null) {
                    list = cache.rank.getData().getList();
                    getActivity().runOnUiThread(()-> this.notifyDataSetChanged());
                }
                RankRepository repository = RankRepository.getInstance();
                repository.getRankMovieI(data -> {
                    Rank result = data.getResult();
                    new Thread(() -> cacheDao.insert(new Cache("moviesrankI", result))).start();
                    list = result.getData().getList();
                    getActivity().runOnUiThread(()-> this.notifyDataSetChanged());
                });
            }).start();
        }
        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemMoviesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movies, parent, false);
            //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
            // Log.d("TAG", "onCreateViewHolder: ");
            return new MyHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            if (list!=null&&list.size()>position) {
                Rank.Data.MList item = list.get(position);
                holder.itemBinding.setItem(item);
            }
        }

        @Override
        public int getItemCount() {
            return list == null ? 0 : list.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            ItemMoviesBinding itemBinding;

            public MyHolder(ItemMoviesBinding itemView) {
                super(itemView.getRoot());
                itemBinding = itemView;
            }
        }
    }
}
