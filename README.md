# Frog
[![version](https://jitpack.io/v/yuluyao/frog.svg)](https://jitpack.io/#yuluyao/frog)

解决使用`RecyclerView`常见的3个问题：

1. `RecyclerView`**适配器**；
2. `RecyclerView`**分割线**；
3. `RecyclerView`**点击事件**。

## 一、install
在 project 的 build.gradle 中添加：
```Groovy
 allprojects {
   repositories {
     ...
     maven { url 'https://jitpack.io' }//最好放在最后一行
   }
 }
```
在 module 的 build.gradle 中添加：
```Groovy
  implementation 'com.github.yuluyao:frog:0.2.6'

```

## 二、使用

### `RecyclerView`适配器

#### 使用DataBinding：
```Kotlin
    val adapter = FrogAdapter<FooBean>(R.layout.item_foo_list)

    //...
    recycler_view?.adapter = adapter
```

```xml
    <data>
        <!--  这里的name只能是item -->
        <variable
          name="item"
          type="com.a.b.c.FooBean"
          />
    </data>
```

#### 不使用DataBinding则要在代码中处理数据绑定：
```Kotlin
    val adapter =object : FrogAdapter<FooBean>(R.layout.item_foo_list){
      override fun onBindViewHolder(holder: FrogHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        // ...
      }
    }
```

### `RecyclerView`分割线

FrogDivider可以处理5种布局的分割线：LinearLayoutManager（vertical）、LinearLayoutManager（horizontal）、
GridLayoutManager、StaggeredGridLayoutManager（vertical）、StaggeredGridLayoutManager（horizontal）。
```Kotlin
    recycler_view?.addItemDecoration(FrogDivider(5f))
```

### `RecyclerView`点击事件

点击事件：
```Kotlin
    recycler_view?.addOnItemTouchListener(object : FrogClickListener() {
      override fun onItemClicked(position: Int) {
        // ...
      }
    })
```

另外，还有：

1. `FrogSingleClickListener`：严格单次点击监听。
2. `FrogLongClickListener`：长按监听。
3. `FrogChildClickListener`：item内部View点击监听。
4. 等等...