package com.example.taobao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.taobao.R;
import com.example.taobao.bean.Bean1;
import com.example.taobao.utils.GsonObjectCallback;
import com.example.taobao.utils.OkHttp3Utils;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

/**
 * Created by 大白 on 2017/10/11.
 */

public class MyAdapter01 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> list = new ArrayList<>();
    private  Banner banner;

    private Context context;


    private GridView gv_fragment;
    private List<String> listT = new ArrayList<>();
    private List<Integer> imgT = new ArrayList<>();

    public MyAdapter01( Context context) {
        this.context = context;
    }

    private static  final  int type1 =0;
    private static  final  int type2 =1;
    private static  final  int type3 =2;
    private static  final  int type4 =3;


    @Override
    public int getItemViewType(int position) {
        if (position == 0){
            return  type1;
        }else if (position == 1){
            return  type2;
        }else if (position == 2){
            return  type3;
        }else {
            return  type4;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType==0) {
            View view = LayoutInflater.from(context).inflate(R.layout.item11, parent, false);
            MyViewHolder holder = new MyViewHolder(view);
            return holder;
        }else if(viewType==1) {
            View view2 = LayoutInflater.from(context).inflate(R.layout.item12, parent, false);
            MyViewHolder2 holder2 = new MyViewHolder2(view2);
            return holder2;
        }else if(viewType==2) {
            View view3 = LayoutInflater.from(context).inflate(R.layout.item13, parent, false);
            MyViewHolder3 holder3= new MyViewHolder3(view3);
            return holder3;
        }else {
            View view4 = LayoutInflater.from(context).inflate(R.layout.item14, parent, false);
            MyViewHolder4 holder4 = new MyViewHolder4(view4);
            return holder4;
        }

    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type){
            case type1:
                getimage();
                break;
            case  type2:

                listT.add("天猫");
                listT.add("聚划算");
                listT.add("天猫国际");
                listT.add("外卖");
                listT.add("天猫超市");
                listT.add("充值中心");
                listT.add("飞猪旅行");
                listT.add("领金币");
                listT.add("拍卖");
                listT.add("分类");
                imgT.add(R.mipmap.t1);
                imgT.add(R.mipmap.t2);
                imgT.add(R.mipmap.t3);
                imgT.add(R.mipmap.t4);
                imgT.add(R.mipmap.t5);
                imgT.add(R.mipmap.t6);
                imgT.add(R.mipmap.t7);
                imgT.add(R.mipmap.t8);
                imgT.add(R.mipmap.t9);
                imgT.add(R.mipmap.t10);
                MyGridAdapter myGridAdapter = new MyGridAdapter(listT,imgT,context);
                gv_fragment.setAdapter(myGridAdapter);

                break;
            case  type3:
                if (holder  instanceof  MyViewHolder3){
                    String url = "http://169.254.51.175/mobile/index.php?act=goods&op=goods_detail&%20goods_id=100009";
                    ((MyViewHolder3) holder).recyclerView2.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    OkHttp3Utils.doGet(url, new GsonObjectCallback<Bean1>() {
                        @Override
                        public void onUi(Bean1 bean) {
                            List<Bean1.DatasBean.GoodsCommendListBean> list1 = bean.getDatas().getGoods_commend_list();
                            MyAdapter011 adapter = new MyAdapter011(list1,context);
                            ((MyViewHolder3) holder).recyclerView2.setAdapter(adapter);
                        }

                        @Override
                        public void onFailed(Call call, IOException e) {

                        }
                    });
                }

                break;

        }

    }

    @Override
    public int getItemCount() {
        return 4;
    }


    class  MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.banner);
        }

    }

    private void getimage(){
        list.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3924299709,3284225473&fm=27&gp=0.jpg");
        list.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1941856829,3747654540&fm=27&gp=0.jpg");
        list.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=977352220,1801142914&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=739749145,442473879&fm=27&gp=0.jpg");
        list.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3123864243,3291356489&fm=27&gp=0.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1907263584,4244818327&fm=27&gp=0.jpg");
        list.add("http://img0.imgtn.bdimg.com/it/u=4023964350,2816279422&fm=200&gp=0.jpg");

        banner.setImageLoader(new MyImage());
        banner.setImages(list);
        banner.setDelayTime(3000);
        banner.start();
    }


    class  MyViewHolder2 extends RecyclerView.ViewHolder {



        public MyViewHolder2(View itemView) {
            super(itemView);
            gv_fragment = itemView.findViewById(R.id.gv_fragment);
        }
    }

    class  MyViewHolder3 extends RecyclerView.ViewHolder {


        private final RecyclerView recyclerView2;

        public MyViewHolder3(View itemView) {
            super(itemView);
            recyclerView2 = itemView.findViewById(R.id.recycler2);
        }

    }

    class  MyViewHolder4 extends RecyclerView.ViewHolder {
        public MyViewHolder4(View itemView) {
            super(itemView);
        }
    }

}
