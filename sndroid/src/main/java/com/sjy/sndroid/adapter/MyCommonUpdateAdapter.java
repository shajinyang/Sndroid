package com.sjy.sndroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sjy.sndroid.R;

import java.util.List;

/**
 * recycleview基础适配器,可手动设置加载中，空数据等
 * Created by sjy on 2017/6/7.
 */

public abstract class MyCommonUpdateAdapter<T> extends Adapter<BaseViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    private int totalCount;
    private int viewType=1000;//普通  1001,加载中；1002,没有更多数据；1003,空数据
    private String tips="";//空数据提示文字

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
        totalCount=mDatas.size();
    }

    public MyCommonUpdateAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
        totalCount=mDatas.size();
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if(holder.getItemViewType()==1000) {
            convert(holder, mDatas.get(position), position);
        }else if(holder.getItemViewType()==1001){
            holder.setText(R.id.txt,"正在加载更多数据");
            holder.setVisibility(R.id.progress, View.VISIBLE);
        }else if(holder.getItemViewType()==1002){
            holder.setText(R.id.txt,"没有更多数据了");
            holder.setVisibility(R.id.progress, View.GONE);
        }else if(holder.getItemViewType()==1003){
            if(!tips.isEmpty()) {
                holder.setText(R.id.txt, tips);
            }
        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==1000) {
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, mLayoutId);
            return viewHolder;
        }else if(viewType==1001){
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, R.layout.recycleview_common_footer);
            return viewHolder;
        }else if(viewType==1002){
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, R.layout.recycleview_common_footer);
            return viewHolder;
        }else if(viewType==1003){
            BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, R.layout.recycleview_common_dataempty);
            return viewHolder;
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return totalCount;
    }

    @Override
    public int getItemViewType(int position) {
        if(viewType==1000) {
            return 1000;
        }else{
            if(position<totalCount-1){
                return 1000;
            }else {
               return viewType;
            }
        }
    }

    public abstract void convert(BaseViewHolder holder, T t, int position);

    /**
     * 显示加载中
     */
    public void setLoading(){
        totalCount=mDatas.size()+1;
        viewType=1001;
        notifyDataSetChanged();
    }

    /**
     * 显示没有更多数据
     */
    public void setEnd(){
        totalCount=mDatas.size()+1;
        viewType=1002;
        notifyDataSetChanged();
    }

    /**
     * 显示空数据
     */
    public void setEmpty(String tips){
        totalCount=mDatas.size()+1;
        viewType=1003;
        this.tips=tips;
        notifyItemInserted(mDatas.size());
    }
    /**
     * 显示空数据
     */
    public void setEmpty(){
        totalCount=mDatas.size()+1;
        viewType=1003;
        notifyItemInserted(mDatas.size());
    }

    /**
     *设置为普通
     */
    public void setNormal(){
        viewType=1000;
    }



}
