package com.example.taobao.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.taobao.R;
import com.example.taobao.adapter.ExAdapter;
import com.example.taobao.api.Api;
import com.example.taobao.bean.BeanList;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by 大白 on 2017/10/10.
 */

public class Fragment04 extends Fragment implements View.OnClickListener {

    private ExpandableListView ex;
    private List<List<BeanList.DatasBean.CartListBean.GoodsBean>> lists;
    private List<BeanList.DatasBean.CartListBean> cart_list;
    private TextView shop_count;
    private CheckBox cb_all;
    private TextView shop_allmoney;
    private ExAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.item04, null);


        ex = view.findViewById(R.id.expandable);
        shop_count = (TextView) view.findViewById(R.id.shop_count);
        cb_all = (CheckBox) view.findViewById(R.id.cb_all);
        shop_allmoney = (TextView) view.findViewById(R.id.shop_allmoney);


     cb_all.setOnClickListener(this);


        String url="http://169.254.110.146/mobile/index.php?act=member_cart&op=cart_list";
        Map<String,String> m = new HashMap<String,String>();
        String key = Api.preferences.getString("key", "");
        m.put("key",key);
        OkHttp3Utils.doPost(url, m, new GsonObjectCallback<BeanList>() {



            @Override
            public void onUi(BeanList beanList) {

                lists = new ArrayList<List<BeanList.DatasBean.CartListBean.GoodsBean>>();
                cart_list = beanList.getDatas().getCart_list();
                for (int i = 0; i< cart_list.size(); i++){
                    List<BeanList.DatasBean.CartListBean.GoodsBean> goods = cart_list.get(i).getGoods();
                    lists.add(goods);

                }
				
				
                adapter = new ExAdapter(lists,cart_list,getActivity());
                ex.setAdapter(adapter);
                for(int i=0;i<ex.getCount();i++){
                    ex.expandGroup(i);
                }


            }

            @Override
            public void onFailed(Call call, IOException e) {

            }
        });




        return view;
    }


    @Override
    public void onClick(View v) {

        if (((CheckBox) v).isChecked()) {
            List<BeanList.DatasBean.CartListBean> cart_list = this.cart_list;
            for (int i = 0; i < cart_list.size(); i++) {
                BeanList.DatasBean.CartListBean cartListBean = cart_list.get(i);
                cartListBean.setAllCheck(true);
                List<BeanList.DatasBean.CartListBean.GoodsBean> goods = cart_list.get(i).getGoods();
                for (int j = 0; j < goods.size(); j++) {
                    List<BeanList.DatasBean.CartListBean.GoodsBean> beanList = cartListBean.getGoods();
                    for (BeanList.DatasBean.CartListBean.GoodsBean childData : beanList) {
                        childData.setItemCheck(true);
                    }
                }
            }
            //刷新界面
            notifyCheckAdapter();
        } else {
            List<BeanList.DatasBean.CartListBean> cart_list = this.cart_list;
            for (int i = 0; i < cart_list.size(); i++) {
                BeanList.DatasBean.CartListBean cartListBean = cart_list.get(i);
                cartListBean.setAllCheck(false);
                List<BeanList.DatasBean.CartListBean.GoodsBean> goods = cart_list.get(i).getGoods();
                for (int j = 0; j < goods.size(); j++) {
                    List<BeanList.DatasBean.CartListBean.GoodsBean> beanList = cartListBean.getGoods();
                    for (BeanList.DatasBean.CartListBean.GoodsBean childData : beanList) {
                        childData.setItemCheck(false);
                    }
                }
            }
            //刷新界面
            notifyCheckAdapter();
        }

    }




    /**
     * 设置一级监听的类
     */
    public  class onGroupClickListener implements View.OnClickListener{

        int groupPosition;
        CheckBox group_cb;

        public onGroupClickListener(int groupPosition, CheckBox group_cb) {
            this.groupPosition = groupPosition;
            this.group_cb = group_cb;
        }

        @Override
        public void onClick(View v) {
            if(((CheckBox)v).isChecked()){
                //一级全选
                setCheck(true);
            }else{
                setCheck(false);
                cb_all.setChecked(false);
            }
            notifyCheckAdapter();
        }

        //设置选中
        public void setCheck(boolean checkFlag){
            //获取集合中每一条数据
            BeanList.DatasBean.CartListBean cartListBean = cart_list.get(groupPosition);
            //一级状态
            cartListBean.setAllCheck(checkFlag);
            //全选状态判断
            int num = 0;
            for (int i = 0;i<cart_list.size();i++){
                boolean allCheck = cart_list.get(i).isAllCheck();
                if(!allCheck){
                    num ++;
                }
            }
            if(num == 0){
                cb_all.setChecked(true);
            }else{
                cb_all.setChecked(false);
            }

            //二级状态
            List<BeanList.DatasBean.CartListBean.GoodsBean> goods = cart_list.get(groupPosition).getGoods();
            for(BeanList.DatasBean.CartListBean.GoodsBean goodsbean:goods){
                goodsbean.setItemCheck(checkFlag);
            }
        }

    }

    /**
     * 二级监听
     */
    public  class onChildCheckListener implements View.OnClickListener{
        int groupPosition;
        int childPosition;
        CheckBox cb_child;

        public onChildCheckListener(int groupPosition, int childPosition, CheckBox cb_child) {
            this.cb_child = cb_child;
            this.groupPosition = groupPosition;
            this.childPosition = childPosition;
        }

        @Override
        public void onClick(View v) {
            if(((CheckBox)v).isChecked()){
                //子选中
                lists.get(groupPosition).get(childPosition).setItemCheck(true);
            }else{
                //子未选中
                lists.get(groupPosition).get(childPosition).setItemCheck(false);
            }

            //二级联动一级状态
            setParentCheckFlag();

            //检测状态  二级全联选中
            int num = 0;
            for(int i = 0;i<cart_list.size();i++){
                boolean allCheck = cart_list.get(i).isAllCheck();
                if (!allCheck) {
                    num++;
                }
            }
            if (num == 0) {
                cb_all.setChecked(true);
            } else {
                cb_all.setChecked(false);
            }
        }

        //二级联动一级状态
        private void setParentCheckFlag(){
            BeanList.DatasBean.CartListBean cartListBean = cart_list.get(groupPosition);
            List<BeanList.DatasBean.CartListBean.GoodsBean> goods = cartListBean.getGoods();
            for (int i = 0; i < goods.size(); i++) {
                if (!goods.get(i).isItemCheck()) {
                    //子未选中 父取消选中
                    cartListBean.setAllCheck(false);
                    notifyCheckAdapter();
                    return;
                }
                if (i == goods.size() - 1) {
                    //子选中 父选中
                    cartListBean.setAllCheck(true);
                    notifyCheckAdapter();
                    return;
                }
            }
            // 没出现全选或者取消全选的时候执行的
            sum();
        }

    }
    //统计数量和价格
    private void sum() {
        int num = 0;
        double price = 0;
        List<BeanList.DatasBean.CartListBean> cart_list = this.cart_list;
        for (BeanList.DatasBean.CartListBean parentData : cart_list) {
            for (BeanList.DatasBean.CartListBean.GoodsBean child : parentData.getGoods()) {
                if (child.isItemCheck()) {
                    num++;
                    double i = Double.parseDouble(child.getGoods_price());

                    price += i;
                }
            }
        }
        shop_count.setText("(" +"总数"+ num + ")");
        shop_allmoney.setText("" + price);
    }
    private void notifyCheckAdapter() {
        sum();
        ex.setAdapter(adapter);
        int count = ex.getCount();
        for (int i = 0; i < count; i++) {
            ex.expandGroup(i);
        }
    }



}
