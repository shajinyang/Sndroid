package com.sjy.sndroid.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.sjy.sndroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 带上拉加载提示adapter
 * Created by sjy on 2017/6/7.
 */

public abstract class CommonUpdateAdapter<T> extends Adapter<BaseViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    private int FOOTVIEW=1000;//底部布局
    private int NORMALVIEW=1001;//普通布局
    private int EMPTYVIEW=1002;//空布局
    private RecyclerView recyclerView;

    private int isAdd=0;//是否有新增数据(0:第一次进入,显示正在加载；1有新增；2：没有新增)

    private boolean isPage=true;//是否分页加载（默认为true）


    /**
     * 判断更新数据，设置数据源
     * @param mDatas 集全部数据
     *  @param newmDatas 集合更新的数据
     */
    public void setmDatas(List<T> mDatas, List<T> newmDatas) {
        if(newmDatas==null){
            newmDatas=new ArrayList<>();
        }
        this.mDatas=mDatas;
        if(mDatas.size()==newmDatas.size()){//第一次进入
            isAdd=0;
        }else if(mDatas.size()!=newmDatas.size()&&newmDatas.size()>0){
            isAdd=1;
        }else if(mDatas.size()!=newmDatas.size()&&newmDatas.size()==0) {
            isAdd=2;
        }
    }
    /**
     * 判断更新数据，设置数据源
     * @param mDatas 集全部数据
     *  @param newmDatas 集合更新的数据
     *  @param isChangeFoot 数据源是否是改变底部（false 不改变    true 改变）
     */
    public void setmDatas(List<T> mDatas, List<T> newmDatas, boolean isChangeFoot) {
        if(newmDatas==null){
            newmDatas=new ArrayList<>();
        }
        this.mDatas=mDatas;
        if(isChangeFoot==false){

        }else {
            if(mDatas.size()==newmDatas.size()){//第一次进入
                isAdd=0;
            }else if(mDatas.size()!=newmDatas.size()&&newmDatas.size()>0){
                isAdd=1;
            }else if(mDatas.size()!=newmDatas.size()&&newmDatas.size()==0) {
                isAdd=2;
            }
        }
    }


    public CommonUpdateAdapter(Context context, int layoutId, List<T> datas)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    public CommonUpdateAdapter(Context context, int layoutId, List<T> datas, RecyclerView recyclerView)
    {
        this.recyclerView=recyclerView;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    public CommonUpdateAdapter(Context context, int layoutId, List<T> datas, RecyclerView recyclerView, boolean isPage)
    {
        this.recyclerView=recyclerView;
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
        this.isPage=isPage;
    }

    public CommonUpdateAdapter(Context context, int layoutId)
    {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        //常规布局事件方法处理
        if(holder.getItemViewType()==NORMALVIEW){
            convert(holder, mDatas.get(position), position);
        }else if(holder.getItemViewType()==FOOTVIEW) {
            //刚加载数据，第一次进入判断，显示footer类型
            if(isAdd==0) {
                if(isPage==true) {//是否分页，不分页直接显示没有更多数据
                    //当滑动到footerview的时候，判断列表第一个item的可见项索引是否大于0，来判断是否可以滑动
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    if (layoutManager instanceof GridLayoutManager) {
                        //通过LayoutManager找到当前显示的第一个后的item的position,该方法必须在绑定childview之后才有效
                        int firstVisibleItemPosition = ((GridLayoutManager) layoutManager).findFirstVisibleItemPosition();
                        if (firstVisibleItemPosition > 0||firstVisibleItemPosition==-1) {
                            isAdd = 1;
                        } else {
                            isAdd = 2;
                        }
                    } else if (layoutManager instanceof LinearLayoutManager) {
                        int firstVisibleItemPosition = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                        if (firstVisibleItemPosition > 0||firstVisibleItemPosition==-1) {
                            isAdd = 1;
                        } else {
                            isAdd = 2;
                        }
                    }
                }else {
                    isAdd=2;
                }
            }
            //foot布局方法处理
            if(isAdd==1){
                holder.setText(R.id.txt,"正在加载更多数据");
                holder.setVisibility(R.id.progress, View.VISIBLE);
            }else if(isAdd==2) {
                holder.setText(R.id.txt,"没有更多数据了");
                holder.setVisibility(R.id.progress, View.GONE);
            }else if(isAdd==0){
                holder.setText(R.id.txt,"上拉加载更多");
                holder.setVisibility(R.id.progress, View.GONE);
            }
        }else if(holder.getItemViewType()==EMPTYVIEW){

        }
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType==NORMALVIEW){
                BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, mLayoutId);
                return viewHolder;
            }else if(viewType==FOOTVIEW) {
                BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, R.layout.recycleview_common_footer);
                return viewHolder;
            }else if(viewType==EMPTYVIEW){
                BaseViewHolder viewHolder = BaseViewHolder.get(mContext, parent, R.layout.recycleview_common_dataempty);
                return viewHolder;
            }
            return null;
    }

    @Override
    public int getItemViewType(int position) {
        if(mDatas.size()==0){
            return EMPTYVIEW;
        }else {
            //如果为最后一个item   则用foot布局
            if (position < mDatas.size()) {
                return NORMALVIEW;
            } else {
                return FOOTVIEW;
            }
        }

    }



    @Override
    public int getItemCount() {
        //因为增加了foot
        return mDatas.size()+1;
    }

    public abstract void convert(BaseViewHolder holder, T t,int position);




}
