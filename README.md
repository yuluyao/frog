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
  implementation 'com.github.yuluyao:frog:0.2.9'

```

## 二、使用

### 1. `RecyclerView`适配器

这里是几个使用 CleanAdapter 的例子：

```Kotlin
  // 创建 adapter，需要3种数据：
  // 1，泛型参数 String；
  // 2，布局 R.layout.layout_item1；
  // 3，数据绑定 onBind。
  val adapter = adapter<String>(R.layout.layout_item1) {
    onBind { holder, position ->
    }
  }
  mRecyclerView?.adapter = adapter
```

```Kotlin
  // 显式地声明 adapter 的类型与泛型
  val adapter: CleanAdapter<String>
  adapter = adapter(R.layout.layout_item1) {
    onBind { holder, position ->
    }
  }
  mRecyclerView?.adapter = adapter
```

```Kotlin
  val adapter = adapter<String>(R.layout.layout_item1) {
    // 这里设置的布局，会覆盖 adapter() 函数中的布局参数，即，R.layout.layout_item1无效，最终布局是 R.layout.layout_item2
    // 所以，只在一处设置布局就可以了
    layouts { intArrayOf(R.layout.layout_item2) }

    onBind { holder, position ->
    }
  }
  mRecyclerView?.adapter = adapter
```

```Kotlin
  // 创建 adapter 的另一种写法：
  val adapter = adapter<String>()
  adapter.layouts { intArrayOf(R.layout.layout_item1) }
  adapter.onBind { holder, position -> }
  mRecyclerView?.adapter = adapter
```

```Kotlin
  val adapter = adapter<String>()
  // 多种类型，传入多个 layout
  adapter.layouts { intArrayOf(R.layout.layout_item1, R.layout.layout_item2) }
  // 设置类型，这里的类型数字对应了 layouts 数组的下标。即，从0开始。
  adapter.onItemType { position -> 1 }
  adapter.onBind { holder, position ->  }
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