
![](sjylogo.png)
# 安卓开发类库
###### 因为公司里只有我一个android开发，所以平时时间有限，如果发现bug，请提交issue，我会第一时间修改优化。另外以后我会慢慢优化自己的类库，并运用的实际项目中。

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
      	 compile 'com.github.shajinyang:Sndroid:1.0.7'
      }

      第三步：
      在自己的application里初始化
      Sndroid.init(this);

####  考虑到实际业务需求，已将网络请求部分和工具库部分和UI组件部分独立拆分，可点击下方链接分别引用
###### [网络请求库(基于Retrofit+okhttp封装的网络请求)https://github.com/shajinyang/SjyNetHelper](https://github.com/shajinyang/SjyNetHelper)
###### [UI组件库(各种自定义view的集合)https://github.com/shajinyang/CommonCustomView](https://github.com/shajinyang/CommonCustomView)
###### [常用工具库(各种常用util)https://github.com/shajinyang/SjyndroidUtil](https://github.com/shajinyang/SjyndroidUtil)

#### 另外我还封装了一些其他的工具库日常开发也会用到，可分别引用
###### [支付类库(一行代码搞定支付宝和微信支付)https://github.com/shajinyang/SPayUtil](https://github.com/shajinyang/SPayUtil)
###### [图片选择库(单选，多选，裁剪，压缩)https://github.com/shajinyang/PhotoPicker](https://github.com/shajinyang/PhotoPicker)


### 公共常用父类
####  BaseActivity
      1,支持自义定状态栏样式，透明状态栏，无状态栏
      2,支持databinding数据绑定
##### 使用示例
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

### 缓存类
#### CacheHelper
     硬盘文件缓存

#### SharedPrefrencer
     sp轻量缓存

### 组件
#### Alerter
     弹框类，加载中显示框,多功能popwindow弹出框，可以在任意view 下面弹出自定义view
     使用示例：
     //显示popview
     Alerter.PopAlert(context
                     ,view//需要显示的自定义view
                     ,anchorView)//锚点view（从该view下方划出）
                     .show();
     //隐藏popview
     Alerter.dismiss();

     //显示加载中
     Alerter.LoadingAlert(context)
            .show();
     //关闭加载中
     Alerter.dismiss();



#### ImageLoader
     图片加载
     支持默认图片设置

#### PermissionHelper
     权限申请帮助类

#### SnackBarhelper
     自定义snackbar操作类
     可显示默认snackbar样式，以及自定义的view样式

#### Toaster
     自定义吐司操作类















