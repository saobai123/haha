package com.example.taobao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.taobao.R;
import com.example.taobao.bean.BeanList;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by 大白 on 2017/10/20.
 */

public class ExAdapter extends BaseExpandableListAdapter  {

    private List<BeanList.DatasBean.CartListBean> cart_list;
    private List<List<BeanList.DatasBean.CartListBean.GoodsBean>> lists;
    private Context context;
    private CheckBox check_zi;


    public ExAdapter(List<List<BeanList.DatasBean.CartListBean.GoodsBean>> lists, List<BeanList.DatasBean.CartListBean> cart_list, Context context) {
        this.lists = lists;
        this.cart_list = cart_list;
        this.context = context;
    }

    @Override
    public int getGroupCount() {
        return cart_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(groupPosition<cart_list.size())    {
            return  lists.get(groupPosition).size();
        }    else {
            return 0;
        }
    }

    @Override
    public Object getGroup(int groupPosition) {
        return cart_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return lists.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.group, null);
        }
            TextView fu = convertView.findViewById(R.id.tv_fu);
              fu.setText(cart_list.get(groupPosition).getStore_name());



        return convertView;



    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.child, null);
        }
        TextView zi = convertView.findViewById(R.id.tv_zi);
        zi.setText(lists.get(groupPosition).get(childPosition).getGoods_name());
        TextView zi2 = convertView.findViewById(R.id.tv_zi2);
        zi2.setText(lists.get(groupPosition).get(childPosition).getGoods_price());
        ImageView img = convertView.findViewById(R.id.img_zi);
        Picasso.with(context).load(lists.get(groupPosition).get(childPosition).getGoods_image_url()).into(img);



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }



}
