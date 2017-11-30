
![](sjylogo.png)
# 安卓开发类库（该框架还在升级完善中）
####  考虑到实际业务需求，已将网络请求部分和工具库部分独立拆分，可点击下方链接分别引用
### [网络请求库](https://github.com/shajinyang/SjyNetHelper)
[https://github.com/shajinyang/SjyNetHelper](https://github.com/shajinyang/SjyNetHelper)
### [常用工具库](https://github.com/shajinyang/SjyndroidUtil)
[https://github.com/shajinyang/SjyndroidUtil](https://github.com/shajinyang/SjyndroidUtil)



### 如何使用

#### Android Studio
    第一步：
      在项目的gradle里配置
      allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
      }

      第二步：
      在module的gradle里配置
      dependencies {
         ...
      	 compile 'com.github.shajinyang:SjyndriodLibrarys:1.2.3'
      }

      第三步：
      在自己的application里初始化
      Sjutil.init(this);



### 公共常用父类
####  BaseActivity
      1,支持自义定状态栏样式，透明状态栏，无状态栏
      2,支持databinding数据绑定
      3,支持本地图片选择，拍照
##### 使用示例
      照片、拍照选择：
      choosePicWithCompress(new IOnChoosePicListener() {
                          @Override
                          public void onSuccess(final TResult result) {
                             //todo 自行处理
                          }
                          @Override
                          public void onFailure(TResult result, String msg) {
                             //todo 自行处理
                          }

                          @Override
                          public void onCancel() {
                              //todo 自行处理
                          }
                      },true);

        状态栏设置
        isHideStateBar=false;//是否隐藏状态栏，默认不隐藏
        isTransStateBar=false;//是否透明状态栏，默认否，配合fitsystemwindow使用（可改变某一个activity的状态栏颜色）

        数据绑定用法同databinding

#### BaseFragment
      支持懒加载
      支持databinding数据绑定
      使用方法同BaseActivity
      initData()  为懒加载方法
      init() 初始化加载

#### CommonAdapter、CommonUpdateAdapter、MultiCommonAdapter、MultiCommonUpdateAdapter、ViewPagerCommonAdapter
      常用recycleview适配器以及viewerpager适配器

##### 使用示例（以CommonUpdateAdapter为例）
      CommonUpdateAdapter支持常规的recycleview绑定
      并且适配器会根据传入的数据源控制空数据或者foot的显示
        private ArrayList<MyWalletRes.ListBean> list=new ArrayList<>();
        private CommonUpdateAdapter<MyWalletRes.ListBean> adapter;

        ...
         if(adapter==null)
          {
            adapter= new CommonUpdateAdapter<MyWalletRes.ListBean>(mContext,R.layout.recycleview_item,list,binding.recyclerView) {
                @Override
                public void convert(BaseViewHolder holder, MyWalletRes.ListBean bean, int position) {
                    holder.setText(R.id.total_money,"bean.getTotal_fee())
                            .setText(R.id.tax,bean.getRevenue())
                            .setText(R.id.fact_money,bean.getPracticalprice())
                            .setTextTimeHm(R.id.time,bean.getAdd_time());
                }
            };
            binding.recyclerView.setAdapter(adapter);
        }else {
            adapter.setmDatas(list,httpResult.data.getList());
            adapter.notifyDataSetChanged();
        }

### 常用帮助类
#### Intenter
     意图跳转帮助类
#### Sper
     SharedPerfrenced 帮助类
#### Toaster
     吐司帮助类
#### UIer
     UI帮助类
     显示加载中弹框

### 常用自定义View
#### CodeView
    倒计时View,基于TextView
    可设置显示文字，倒计时时间长度
    使用示例：
    xml:
    <com.zx.xsk.views.counttimeview.CodeView
                android:id="@+id/code_view"
                android:layout_width="90dp"
                android:layout_height="wrap_content"
                android:padding="8dp"
                android:gravity="center"
                android:background="@drawable/shape_bg_accent_round"
                android:foreground="?android:attr/selectableItemBackground"
                android:textColor="@color/whiteff"
                android:layout_marginRight="16dp"
                />


    java:
    binding.codeView.setOnTimeListener(new OnTimeListener() {
                @Override
                public void startTime() {

                }

                @Override
                public void endTime() {

                }

                @Override
                public boolean onClickTime() {
                    if(TextViewUtil.isEmpty(binding.tel)){
                        Toaster.showToast("手机号未填写");
                        return false;
                    }else if(!TextViewUtil.isMobileNO(binding.tel.getText().toString().trim())){
                        Toaster.showToast("手机号格式不正确");
                        return false;
                    }else {
                        getTelCode();
                    }
                    return true;
                }
            });

#### AnimatedImageSpan
    支持gif的imagespan
#### MyImageSpan
    居中的imagespan
#### SLoadingView
    加载状态view（网络错误，空数据，加载中，无网络）
#### LoadingRelativeLayout
    加载状态父布局（网络错误，空数据，加载中，无网络）
    使用示例：
    xml:
     <com.zx.xsk.views.LoadingRelativeLayout
            android:id="@+id/loading"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
        ...

     </com.zx.xsk.views.LoadingRelativeLayout>

      java:
      loadingRelativeLayout.showNoNet();//显示网络未连接
      loadingRelativeLayout.showLoading();//显示加载中
      loadingRelativeLayout.showEmpty();//显示空数据
      loadingRelativeLayout.showError();//显示网络错误
      loadingRelativeLayout.clearAll();//关闭状态视图
      //状态点击回调
      loadingRelativeLayout.setOnStateClick(new OnStateClickListener() {
        @Override
        public void onClickError() {
            //todo
        }

        @Override
        public void onClickNoNet() {
            //todo
        }
      });

#### MultiKeyBoardView
     复合键盘布局，基于viewpager,主要是优化了系统键盘和view的显示逻辑
     使用示例：
     xml:
     <com.zx.xsk.views.mutikeyboardview.MultiKeyBoardView
                 android:id="@+id/mul"
                 android:layout_width="match_parent"
                 android:layout_height="wrap_content"
                 android:orientation="vertical"
                 />

      java:
      binding.mulitikeyboardview
             .initKeyBoardView(this,titles,fragmentArrayList,binding.richEditor)
             .setTabColor(0xff333333)
             .setTabTxtColor(0xff999999,0xffffffff);
      具体参数含义看源码注释

#### MyNestedScorllView
     带底部滑动监听的nestscorllview
#### MyRecycleview
     带底部滑动监听的recycleview
#### NestedRecycleView
     嵌套nestedscorllview的recycleview,解决滑动冲突
     配合NestGridLayoutManager、NestLinearLayoutManager使用

#### SToolBarView
     toolbar封装
#### BubbleImageView
     仿微信聊天气泡Imageview
#### CircleImageView
     圆形图片，支持边框设置
#### CircleRectangleView
     圆角图片（可设置圆形图片）支持边框设置
#### MyNumberPicker
     设置picker边框和字体
#### NoScrollViewPager
     无滚动viewerpager
#### SquareLinearLayout
     正方形布局

#### DragFloatImageView
    可拖拽带吸附效果的imageview



### 组件
#### ImageLoader
     基于glide的图片加载组件

#### PermissionRequester
     android6.0/7.0以上动态权限申请















