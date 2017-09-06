# capsule

一个好用的 RecyclerView Adapter 库！

## 一、引用项目
在 project 的 build.gradle 中添加：
```
 allprojects {
   repositories {
     ...
     maven { url 'https://jitpack.io' }//最好放在最后一行
   }
 }
```
在 module 的 build.gradle 中添加：
```
  compile 'com.github.wushenggit:capsule:1.0.0'

```


## 二、adapter 的使用很简单
### 1.定义 adapter
```
//继承 Adapter 类，泛型T（Data）定义数据类型，ViewHolder类可被拓展
public class MyAdapter extends Adapter<Data,ViewHolder> {
  //定义item的布局
  @Override protected int onGetItemLayoutId() {
    return R.layout.layout_item;
  }
  //更新UI
  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setText(R.id.tv_title, item.getTitle());
  }
}
```

### 2.使用 adapter
```
  //初始化RecyclerView
  recyclerView = (RecyclerView) findViewById(R.id.recycler);
  recyclerView.setLayoutManager(new LinearLayoutManager(this));
  //设置适配器
  MyAdapter adapter = new MyAdapter();
  recyclerView.setAdapter(adapter);
  //更新UI
  List<T> dataList = ...;//获取数据
  adapter.notifyRefreshCompleted(dataList);
```


## 三、加载更多
1.对 adapter 添加加载监听
```
adapter.setOnLoadMoreListener(new Adapter.OnLoadMoreListener() {
      @Override public void onLoadMore() {
        repo.load(adapter.getLastData().getId())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Data>>() {
              @Override public void accept(@NonNull List<Data> datas) throws Exception {
                adapter.notifyLoadMoreCompleted(datas);//调用此方法更新视图
              }
            });
```

2.加载完成调用`adapter.notifyLoadMoreCompleted()`更新ui，在方法中已对传入参数做了处理，自动处理底部的加载视图


## 四、点击事件和长按事件
### 1.点击监听
通过 RecyclerView 的 `addOnItemTouchListener()`方法添加点击监听，这里定义了`ItemClickListener`、`ItemLongClickListener`、`EmptyClickListener`3个监听器
```
recyclerView.addOnItemTouchListener(new ItemClickListener() {
      @Override public void onItemClick(RecyclerView.ViewHolder vh, int position, View childView) {
        if (childView != null) {
          Toast.makeText(ClickActivity.this, "点击 item child", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(ClickActivity.this, "点击 item" + position, Toast.LENGTH_SHORT).show();
        }
      }
    });
```

### 2.child view 点击监听
1.想要监听 child view，首先在 adapter 里注册要点击的View的id
```
  @Override protected void convert(ViewHolder holder, Data item) {
    holder.setClickableId(R.id.icon);
  }
```
2.在点击回调里判断 childView 参数不为空，则触发 childView 的点击
```
    if (childView != null) {
      Toast.makeText(ClickActivity.this, "点击 item child", Toast.LENGTH_SHORT).show();
    } else {
      Toast.makeText(ClickActivity.this, "点击 item" + position, Toast.LENGTH_SHORT).show();
    }
```


## 五、多种 viewType
1.继承MultiAdapter类，数据类型要实现 `MultiEntity` 接口
```
public class MultipleAdapter extends MultiAdapter<ArticleBean, ViewHolder> {
  ...
```



## 六、添加分割线


```
  recyclerView.addItemDecoration(new Divider(6, 0x7f00ffff));
```


## 七、初始 EmptyView

```
  recyclerView.addItemDecoration(new EmptyDecor(R.layout.layout_empty));
```


## 八、添加头和脚
注意，头部和脚部都是不可点击的

```
  //可滑动的头部
  recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head));
  //固定头部
  recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head, true));
  //脚部，可滑动
  recyclerView.addItemDecoration(new FootDecor(R.layout.layout_foot));
```


## 九、动画


## 十、展开 Expandable


## 十一、分组 Section


## 十二、Swipe


## 十三、Drag



