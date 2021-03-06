package com.example.taobao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.searchview_library.SearchALG;
import com.example.searchview_library.SearchView;
import com.example.taobao.R;
import com.example.taobao.adapter.MyAdapter2;
import com.example.taobao.bean.FindBean;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class FindActivity extends AppCompatActivity {

    private SearchView searchView;
    private SearchALG searchALG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find);

        searchView = (SearchView) findViewById(R.id.searchView);

        initData();

        searchView.setOnSearchListener(new MyOnSearchListener());
    }

    private List<String> changedHintDatas;

    /**
     * 设置searview的监听
     */
    class MyOnSearchListener implements SearchView.OnSearchListener {

        private RecyclerView re_find;

        /**
         * 搜索回调
         * @param searchText 进行搜索的文本
         */
        @Override
        public void onSearch(String searchText) {
            if (!TextUtils.isEmpty(searchText)) {

                re_find = (RecyclerView) findViewById(R.id.re_find);
                re_find.setLayoutManager(new LinearLayoutManager(FindActivity.this,LinearLayoutManager.VERTICAL,false));
               String url="http://169.254.110.146/mobile/index.php?act=goods&op=goods_list&page=100&%20gc_id=587";
                OkHttp3Utils.doGet(url, new GsonObjectCallback<FindBean>() {

                    private MyAdapter2 adapter;

                    @Override
                    public void onUi(FindBean findBean) {
                        final List<FindBean.DatasBean.GoodsListBean> list = findBean.getDatas().getGoods_list();
                        adapter = new MyAdapter2(list,FindActivity.this);
                        re_find.setAdapter(adapter);
                         adapter.setOnItemClickListener(new MyAdapter2.OnItemClickListener() {
                             @Override
                             public void onItemClick(int position) {

                                 Intent intent = new Intent(FindActivity.this, DataActivity.class);
                                 intent.putExtra("name",list.get(position).getGoods_name());
                                 intent.putExtra("price",list.get(position).getGoods_price());
                                 intent.putExtra("img",list.get(position).getGoods_image_url());
                                 intent.putExtra("goods_id",list.get(position).getGoods_id());
                                 startActivity(intent);
                             }
                         });

                    }

                    @Override
                    public void onFailed(Call call, IOException e) {

                    }
                });



                Toast.makeText(FindActivity.this, "完成搜索" + searchText, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(FindActivity.this, "搜索内容不能为空！", Toast.LENGTH_SHORT).show();
            }
        }

        /**
         * 刷新提示列表
         * @param changedText 改变后的文本
         */
        @Override
        public void onRefreshHintList(String changedText) {
            if (changedHintDatas == null) {
                changedHintDatas = new ArrayList<>();
            } else {
                changedHintDatas.clear();
            }
            if (TextUtils.isEmpty(changedText)) {
                return;
            }
            for (int i = 0; i < hint_datas.size(); i++) {
                String hint_data = hint_datas.get(i);
                boolean isAdd = searchALG.isAddToHintList(hint_data, changedText);
                if (isAdd) {
                    changedHintDatas.add(hint_datas.get(i));
                }
            }

            /**
             * 根据搜索框文本的变化，动态的改变提示的listView
             */
            searchView.updateHintList(changedHintDatas);

        }
    }

    //热搜数据
    private List<String> hot_datas;
    //提示列表数据
    private List<String> hint_datas;

    private void initData() {
        hot_datas = new ArrayList<>();
        hint_datas = new ArrayList<>();

        searchALG = new SearchALG(this);

        for (int i = 0; i < 10; i++) {
            hot_datas.add("大大怪 " + i);
        }

        //设置热搜数据显示的列数
        searchView.setHotNumColumns(2);
        //设置热搜数据
        searchView.setHotSearchDatas(hot_datas);

        /**
         * 设置提示数据的集合
         */
        for (int i = 0; i < 10; i++) {
            hint_datas.add("ts"+"安卓学习" + "Android Hint " + i + " 你好");
        }

        /**
         * 设置自动保存搜索记录
         */
        searchView.keepSearchHistory(true);

        //设置提示列表的最大显示列数
        searchView.setMaxHintLines(8);
        //设置保存搜索记录的个数
        searchView.setMaxHistoryRecordCount(6);

    }





}
