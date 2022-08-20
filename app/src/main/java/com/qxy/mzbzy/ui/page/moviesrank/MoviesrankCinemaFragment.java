package com.qxy.mzbzy.ui.page.moviesrank;

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
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.mzbzy.R;
import com.qxy.mzbzy.data.api.CacheDao;
import com.qxy.mzbzy.data.bean.Cache;
import com.qxy.mzbzy.data.bean.Rank;
import com.qxy.mzbzy.data.repository.CacheRepository;
import com.qxy.mzbzy.data.repository.RankRepository;
import com.qxy.mzbzy.databinding.FragmentMoviesrankCinemaBinding;
import com.qxy.mzbzy.databinding.ItemMoviesBinding;
import com.qxy.mzbzy.ui.App;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MoviesrankCinemaFragment extends Fragment {
    private MoviesrankCinemaViewModel vm;
    private FragmentMoviesrankCinemaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        vm = App.getApp().getApplicationScopeViewModel(MoviesrankCinemaFragment.MoviesrankCinemaViewModel.class);

        // 获取本页面databinding对象
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_moviesrank_cinema, container, false);
        // 设定ViewModel监听生命周期
        binding.setLifecycleOwner(this);
        // 为对象赋值
        binding.setVm(vm);
        binding.setClick(new MoviesrankCinemaFragment.ClickProxy());
        binding.setAdapter(new MAdapter());
        // LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
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
            Toast.makeText(getContext(), "测试文本", Toast.LENGTH_SHORT).show();
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String date = sdf.format(new Date());
                vm.mText.set(date);
            }
        }
    }

    public static class MoviesrankCinemaViewModel extends ViewModel {
        //LiveData与Observable均可
        // public final MutableLiveData<String> mText;
        public final ObservableField<String> mText;

        // 数据初始化
        public MoviesrankCinemaViewModel() {
            mText = new ObservableField<>();
            mText.set("This is home fragment");
        }

    }

    public class MAdapter extends RecyclerView.Adapter<MAdapter.MyHolder> {
        private List<Rank.Data.MList> list;

        public List<Rank.Data.MList> getList() {
            return list;
        }
        // 直接使用测试数据生成列表
//        {
//            Gson gson = new Gson();
//
//            Rank rank = gson.fromJson("{\n" +
//                "  \"data\": {\n" +
//                "    \"active_time\": \"2020-03-31 12:00:00\",\n" +
//                "    \"description\": \"\",\n" +
//                "    \"error_code\": \"0\",\n" +
//                "    \"list\": [\n" +
//                "      {\n" +
//                "        \"actors\": [\n" +
//                "          \"[徐峥 袁泉 沈腾 吴云芳 陈奇 黄梅莹 欧丽娅 贾冰 郭京飞]\"\n" +
//                "        ],\n" +
//                "        \"areas\": [\n" +
//                "          \"[中国]\"\n" +
//                "        ],\n" +
//                "        \"directors\": [\n" +
//                "          \"[徐峥]\"\n" +
//                "        ],\n" +
//                "        \"discussion_hot\": \"789200\",\n" +
//                "        \"hot\": \"1.361e+06\",\n" +
//                "        \"id\": \"6399487713065566465\",\n" +
//                "        \"influence_hot\": \"789200\",\n" +
//                "        \"maoyan_id\": \"1250696\",\n" +
//                "        \"name\": \"囧妈\",\n" +
//                "        \"name_en\": \"Lost in Russia\",\n" +
//                "        \"poster\": \"https://p3-dy.bytecdn.cn/obj/compass/9ac412ae054b57f69c0967a8eb5e25c9\",\n" +
//                "        \"release_date\": \"2020-01-25\",\n" +
//                "        \"search_hot\": \"684900\",\n" +
//                "        \"tags\": [\n" +
//                "          \"[喜剧]\"\n" +
//                "        ],\n" +
//                "        \"topic_hot\": \"684900\",\n" +
//                "        \"type\": \"1\"\n" +
//                "      }\n" +
//                "    ]\n" +
//                "  },\n" +
//                "  \"extra\": {\n" +
//                "    \"description\": \"\",\n" +
//                "    \"error_code\": \"0\",\n" +
//                "    \"logid\": \"202008121419360101980821035705926A\",\n" +
//                "    \"now\": \"1597213176393\",\n" +
//                "    \"sub_description\": \"\",\n" +
//                "    \"sub_error_code\": \"0\"\n" +
//                "  }\n" +
//                "}", Rank.class);
//            list=rank.getData().getList();
//            Log.d("json解析", "实例初始值设定项: "+list);
//        }
        // 请求云端上mock接口
        {
            RankRepository repository = RankRepository.getInstance();
            repository.getRankMovieC(data->{
                List<Rank.Data.MList> list1 = data.getResult().getData().getList();
                list=list1;
                Log.d("TAG", "数据返回");
                this.notifyDataSetChanged();
            });
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
