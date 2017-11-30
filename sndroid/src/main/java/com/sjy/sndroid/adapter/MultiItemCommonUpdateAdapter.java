package com.sjy.sndroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sjy.sndroid.R;

import java.util.List;

/**
 * 多布局适配器带下拉加载更多
 * @param <T>
 */
public abstract class MultiItemCommonUpdateAdapter<T> extends RecyclerView.Adapter<BaseViewHolder>
{
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    private int FOOTVIEW=1000;//底部布局
    private int NORMALVIEW=1001;//普通布局

    private boolean isAdd=true;//是否有新增数据
    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonUpdateAdapter(Context context, List<T> datas,
                                        MultiItemTypeSupport<T> multiItemTypeSupport)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    /**
     * 判断更新数据，设置数据源
     * @param mDatas 集全部数据
     *  @param newmDatas 集合更新的数据
     */
    public void setmDatas(List<T> mDatas, List<T> newmDatas) {
        this.mDatas=mDatas;
        if(newmDatas.size()>0){
            isAdd=true;
        }else {
            isAdd=false;
        }
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        //常规布局事件方法处理
        if(holder.getItemViewType()==FOOTVIEW){
            //foot布局方法处理
            if(isAdd==true){
                holder.setText(R.id.txt,"正在加载更多数据");
                holder.setVisibility(R.id.progress, View.VISIBLE);
            }else {
                holder.setText(R.id.txt,"没有更多数据了");
                holder.setVisibility(R.id.progress, View.GONE);
            }

        }else {
            convert(holder, mDatas.get(position), position);
        }

    }

    @Override
    public int getItemCount() {
        //因为增加了foot
        return mDatas.size()+1;
    }

    @Override
    public int getItemViewType(int position)
    {
        //如果为最后一个item   则用foot布局
        if(position<mDatas.size()){
            return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
        }else {
            return FOOTVIEW;
        }

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        BaseViewHolder holder = BaseViewHolder.get(mContext, parent, layoutId);
        return holder;
    }

    public abstract void convert(BaseViewHolder holder, T t,int position);
}