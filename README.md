# Frog
[![version](https://jitpack.io/v/yuluyao/frog.svg)](https://jitpack.io/#yuluyao/frog)

解决使用`RecyclerView`常见的3个问题：

1. `RecyclerView`**适配器**；
2. `RecyclerView`**分割线**；
3. `RecyclerView`**点击监听**。

FrogAdapter是一个简洁的适配器，源码只有50几行代码。使用FrogAdapter，只需要**1行代码**（见下文）。

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

### 1. `RecyclerView`适配器

如果使用DataBinding：
```Kotlin
    // 1行代码adapter，不必使用继承
    val adapter = FrogAdapter<FooBean>(R.layout.item_foo_list)

    // 设置adapter
    recycler_view?.adapter = adapter

    // ...

    // 更新数据
    adapter.notifyDataSetChanged()
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

如果不使用DataBinding，则要在代码中处理数据绑定：
```Kotlin
    val adapter = object : FrogAdapter<FooBean>(R.layout.item_foo_list){
      override fun onBindViewHolder(holder: FrogHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        // ...
      }
    }
```

### 2. `RecyclerView`分割线

FrogDivider可以处理5种布局的分割线：

- LinearLayoutManager（vertical）
- LinearLayoutManager（horizontal）
- GridLayoutManager
- StaggeredGridLayoutManager（vertical）
- StaggeredGridLayoutManager（horizontal）

```Kotlin
    recycler_view?.addItemDecoration(FrogDivider())
    // or
    recycler_view?.addItemDecoration(FrogDivider(4f))
    // or
    recycler_view?.addItemDecoration(FrogDivider(6f, R.color.transparent))
```

### 3. `RecyclerView`点击监听

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