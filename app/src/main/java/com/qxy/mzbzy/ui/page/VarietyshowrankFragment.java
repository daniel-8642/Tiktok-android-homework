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
import com.qxy.mzbzy.data.response.DataResult;
import com.qxy.mzbzy.databinding.FragmentVarietyshowrankBinding;
import com.qxy.mzbzy.databinding.ItemMoviesBinding;
import com.qxy.mzbzy.ui.App;

import java.util.List;

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
        public void test() {
            //Toast.makeText(getContext(),"Dash测试文本",Toast.LENGTH_SHORT).show();

            // 获取仓库对象
            RankRepository repository = RankRepository.getInstance();
            //发起网络请求，设置回调函数callback
            // 网络请求成功，获取到test对象，调用callback（vm.setTestdata(test)）处理
            // vm.setTestdata(test) 将拆解test，将返回值放置到页面
            repository.getTestData(data -> vm.getStringFromRank(data));

//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                String date = sdf.format(new Date());
//                vm.mText.setValue(date);
//            }
        }
    }
    public static class VarietyshowrankViewModel extends ViewModel {

        //LiveData与Observable均可，LiveData在重新监听时回
        public final MutableLiveData<String> mText ;
        // public final ObservableField<String> mText;

        {
            mText = new MutableLiveData<>();
            mText.setValue("This is dashboard fragment");
        }
        // 数据初始化
//        public VarietyshowrankViewModel() {
//            mText = new MutableLiveData<>();
//            mText.setValue("This is dashboard fragment");
//        }
        public void getStringFromRank(DataResult<Rank> rank){

            Rank.Data data = rank.getResult().getData();
            // Log.d("test", "getStringFromRank:"+data.toString());
            mText.postValue(data.getList().get(0).getName());
        }
    }

    public class MAdapter extends RecyclerView.Adapter<VarietyshowrankFragment.MAdapter.MyHolder> {
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
            repository.getTestData(data->{
                List<Rank.Data.MList> list1 = data.getResult().getData().getList();
                list=list1;
                Log.d("TAG", "数据返回");
                this.notifyDataSetChanged();
            });
        }
        @NonNull
        @Override
        public VarietyshowrankFragment.MAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ItemMoviesBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movies, parent, false);
            return new MyHolder(binding);
        }

        @Override
        public void onBindViewHolder(@NonNull VarietyshowrankFragment.MAdapter.MyHolder holder, int position) {
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