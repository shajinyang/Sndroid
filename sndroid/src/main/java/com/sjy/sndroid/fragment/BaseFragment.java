package com.sjy.sndroid.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 基础fragment带懒加载
 * Created by sjy on 2017/5/17.
 */

public abstract class BaseFragment<T extends ViewDataBinding> extends Fragment {
    public T binding;
    public Context mContext;
    public Boolean isFirstLoad=true;//是否是第一次加载
    public Boolean isVisible=false;//是否可见
    public Boolean isPrepare=false;//是否全部准备完毕

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,bindLayout(),container,false);
        mContext=getActivity();
        isPrepare=true;
        loadData();
        init();
        return binding.getRoot();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser==true){
            isVisible=true;
            loadData();
        }
    }

    private void loadData(){
        if(isFirstLoad==true&&isVisible==true&&isPrepare==true){
            initData();
            isFirstLoad=false;
        }
    }


    public abstract int bindLayout();
    public abstract void init();
    public abstract void initData();

}
