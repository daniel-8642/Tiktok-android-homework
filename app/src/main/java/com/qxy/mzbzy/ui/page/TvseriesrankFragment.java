package com.qxy.mzbzy.ui.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.mzbzy.R;
import com.qxy.mzbzy.data.api.CacheDao;
import com.qxy.mzbzy.data.bean.Cache;
import com.qxy.mzbzy.data.bean.Rank;
import com.qxy.mzbzy.data.repository.CacheRepository;
import com.qxy.mzbzy.data.repository.RankRepository;
import com.qxy.mzbzy.databinding.FragmentTvseriesrankBinding;
import com.qxy.mzbzy.databinding.ItemMoviesBinding;
import com.qxy.mzbzy.ui.App;

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
        binding.setAdapter(new MAdapter());
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

    public class MAdapter extends RecyclerView.Adapter<TvseriesrankFragment.MAdapter.MyHolder> {
        private List<Rank.Data.MList> list;

        public List<Rank.Data.MList> getList() {
            return list;
        }
        // 请求云端上mock接口
        {
            CacheDao cacheDao = CacheRepository.getInstance(getContext()).CacheDao();
            new Thread(() -> {
                Cache cache = cacheDao.getByName("TV");
                if (cache != null) {
                    list = cache.rank.getData().getList();
                }
                RankRepository repository = RankRepository.getInstance();
                repository.getRankTV(data -> {
                    Rank result = data.getResult();
                    new Thread(() -> cacheDao.insert(new Cache("TV", result))).start();
                    list = result.getData().getList();
                });
            }).start();
        }
        @NonNull
        @Override
        public TvseriesrankFragment.MAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemMoviesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movies, parent, false);
            return new MyHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull TvseriesrankFragment.MAdapter.MyHolder holder, int position) {
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