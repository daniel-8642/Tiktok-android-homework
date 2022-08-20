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
import androidx.recyclerview.widget.RecyclerView;

import com.qxy.mzbzy.R;
import com.qxy.mzbzy.data.bean.Rank;
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
            repository.getRankTV(data->{
                List<Rank.Data.MList> list1 = data.getResult().getData().getList();
                list=list1;
                Log.d("TAG", "数据返回");
                this.notifyDataSetChanged();
            });
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