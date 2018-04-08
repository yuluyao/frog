# Chick
[![Release](https://jitpack.io/v/vegetayu/chick.svg)](https://jitpack.io/#vegetayu/chick)

目标：
用最佳的方式提供的RecyclerView的使用实践。

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
  compile 'com.github.vegetayu:chick:{version}'

```


## 二、适配 RecyclerView
### 1.定义 adapter
```
//继承 Adapter 类，泛型T（Data）定义数据类型，ViewHolder类可被拓展
public class MyAdapter extends Adapter<Data,ViewHolder> {
  //定义item的布局
  @Override protected void onSetItemLayout() {
      setItemLayout(R.layout.item_data_vertical);
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

3.加载失败点击重试。
```
adapter.setOnRetryListener(new Adapter.OnRetryListener() {
      @Override public void onRetry() {
        repo.load(adapter.getLastData().getId())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<List<Data>>() {
              @Override public void accept(@NonNull List<Data> datas) throws Exception {
                adapter.notifyLoadMoreCompleted(datas);//调用此方法更新视图
              }
            });
      }
    });
```

4.自定义加载提示的UI，调用`adapter.setLoadDecor()`方法。需要继承 BaseLoadDecor 或 SimpleLoadDecor 类。

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
### 1.实现 `MultiEntity` 接口

MultiEntity 接口如下：
```
public interface MultiEntity {
  int getItemType();
}
```
数据类型要实现 MultiEntity 接口：
```
public class DataBean implements MultiEntity {
...
}
```
### 2.设置多个布局

在适配器中根据不同的数据类型设置不同的布局：
```
public class MultipleAdapter extends Adapter<ArticleBean, ViewHolder> {
  ...
  @Override protected void onSetItemLayout() {
    setItemLayout(DataBean.TYPE_1, R.layout.item_1);
    setItemLayout(DataBean.TYPE_2, R.layout.item_2);
    setItemLayout(DataBean.TYPE_3, R.layout.item_3);
  }
  ...
```


## 六、添加分割线


```
  recyclerView.addItemDecoration(new Divider(1, R.color.red));//第1个参数的单位是dp
```
> 目前，横竖分割线的宽度是一样的，不可分别设置

## 七、初始 EmptyView

```
  recyclerView.addItemDecoration(new EmptyDecor(R.layout.layout_empty));
```

## 八、添加头和脚

```
  //可滑动的头部
  recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head));
  //固定头部
  recyclerView.addItemDecoration(new HeadDecor(R.layout.layout_head, true));
  //脚部，可滑动
  recyclerView.addItemDecoration(new FootDecor(R.layout.layout_foot));
```
> 注意，头部和脚部都是不可点击的

## 九、动画
继承 BaseItemAnimator 类可以实现自己的动画，也可以直接使用目前已经定义好的实现类，详见 com.capsule.recy.anim.impl 包

一行代码即可添加全套（添加、删除、移动、更新）动画：
```
  recyclerView.setItemAnimator(new SlideInLeftAnimator());
```


## 十、展开详情 Expandable
展开详情功能并没有标准的模板代码，这里提供思路：

1. data实现 Expandable 接口。
2. 在 adapter 中通过 Expandable.isExpand() 方法判断详情是否展开。
3. 在点击事件回调中，改变 data 的 expand 状态。

具体可参考 sample 中的代码。

## 十一、多级列表


## 十二、字母分组


## 十三、Drag
```
  ItemTouchHelper helper = new ItemTouchHelper(new DragCallback());
  helper.attachToRecyclerView(recyclerView);
```

## 十四、Swipe
```
  ItemTouchHelper helper = new ItemTouchHelper(new SwipeCallback());
  helper.attachToRecyclerView(recyclerView);
```

## 十五、多选



