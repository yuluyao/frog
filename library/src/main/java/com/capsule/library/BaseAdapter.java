package com.capsule.library;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 14:50
 */
public abstract class BaseAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<H> {

  //view type
  public static final int VIEW_TYPE_DEFAULT = 0;
  public static final int VIEW_TYPE_HEADER  = 1;
  public static final int VIEW_TYPE_FOOTER  = 2;
  public static final int VIEW_TYPE_LOAD    = 3;
  public static final int VIEW_TYPE_EMPTY   = 4;

  protected Context        mContext;
  protected RecyclerView   mRecyclerView;
  protected LayoutInflater mLayoutInflater;

  protected List<T> mData = new ArrayList<>();
  protected int     mLayoutResId;

  /* 空 */
  private FrameLayout mEmptyLayout;
  private boolean mIsUseEmpty = true;
  private boolean mHeadAndEmptyEnable;
  private boolean mFootAndEmptyEnable;

  /* 头尾 */
  private LinearLayout mHeaderLayout;
  private LinearLayout mFooterLayout;

  /* ******************************* */
  public BaseAdapter(List<T> data, int mLayoutResId) {
    mData.clear();
    mData.addAll(data);
    this.mLayoutResId = mLayoutResId;
  }

  public BaseAdapter(List<T> data) {
    mData.clear();
    mData.addAll(data);
  }

  public BaseAdapter(int mLayoutResId) {
    this.mLayoutResId = mLayoutResId;
  }

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    mRecyclerView = recyclerView;
    mContext = mRecyclerView.getContext();
    mLayoutInflater = LayoutInflater.from(mContext);
  }

  @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    super.onDetachedFromRecyclerView(recyclerView);
    mRecyclerView = null;
  }

  @Override public H onCreateViewHolder(ViewGroup parent, int viewType) {
    H baseViewHolder = null;

    switch (viewType) {
      case VIEW_TYPE_DEFAULT:
        baseViewHolder = onCreateDefViewHolder(parent, viewType);
        break;
      case VIEW_TYPE_HEADER:
        baseViewHolder = createBaseViewHolder(mHeaderLayout);
        break;
      case VIEW_TYPE_FOOTER:
        baseViewHolder = createBaseViewHolder(mFooterLayout);
        break;
      case VIEW_TYPE_LOAD:
        //baseViewHolder = getLoadingView(parent);
        break;
      case VIEW_TYPE_EMPTY:
        baseViewHolder = createBaseViewHolder(mEmptyLayout);
        break;
      default:
        baseViewHolder = onCreateDefViewHolder(parent, viewType);
        //bindViewClickListener(baseViewHolder);
    }
    //baseViewHolder.setAdapter(this);
    return baseViewHolder;
  }

  protected H onCreateDefViewHolder(ViewGroup parent, int viewType) {
    //int layoutId = mLayoutResId;
    //if (mMultiTypeDelegate != null) {
    //  layoutId = mMultiTypeDelegate.getLayoutId(viewType);
    //}
    return createBaseViewHolder(mLayoutInflater.inflate(mLayoutResId, parent, false));
  }

  @SuppressWarnings("unchecked") protected H createBaseViewHolder(View view) {
    Class temp = getClass();
    Class z = null;
    while (z == null && null != temp) {
      z = getInstancedGenericKClass(temp);
      temp = temp.getSuperclass();
    }
    H h;
    // 泛型擦除会导致z为null
    if (z == null) {
      h = (H) new BaseViewHolder(view);
    } else {
      h = createGenericKInstance(z, view);
    }
    return h != null ? h : (H) new BaseViewHolder(view);
  }

  private Class getInstancedGenericKClass(Class z) {
    Type type = z.getGenericSuperclass();
    if (type instanceof ParameterizedType) {
      Type[] types = ((ParameterizedType) type).getActualTypeArguments();
      for (Type temp : types) {
        if (temp instanceof Class) {
          Class tempClass = (Class) temp;
          if (BaseViewHolder.class.isAssignableFrom(tempClass)) {
            return tempClass;
          }
        }
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked") private H createGenericKInstance(Class z, View view) {
    try {
      Constructor constructor;
      // inner and unstatic class
      if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
        constructor = z.getDeclaredConstructor(getClass(), View.class);
        constructor.setAccessible(true);
        return (H) constructor.newInstance(this, view);
      } else {
        constructor = z.getDeclaredConstructor(View.class);
        constructor.setAccessible(true);
        return (H) constructor.newInstance(view);
      }
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override public void onBindViewHolder(H holder, int position) {

    int viewType = holder.getItemViewType();
    int offset = 0;
    if (mHeaderLayout != null) {
      offset++;
    }
    switch (viewType) {
      case VIEW_TYPE_DEFAULT:
        convert(holder, getItem(position - offset));
        break;
      case VIEW_TYPE_HEADER:
        break;
      case VIEW_TYPE_FOOTER:
        break;
      case VIEW_TYPE_LOAD:
        break;
      case VIEW_TYPE_EMPTY:
        break;

      default:
        convert(holder, getItem(position - offset));
        break;
    }
  }

  protected abstract void convert(H holder, T item);

  @Override public int getItemCount() {
    int count = mData.size();
    if (mHeaderLayout != null) {
      count++;
    }

    return count;
  }

  @Nullable public T getItem(int position) {
    if (position < mData.size()) {
      return mData.get(position);
    } else {
      return null;
    }
  }



  /* *********************** header *********************** */

  public void setHeader(View header) {
    setHeader(header, 0);
  }

  public void setHeader(@LayoutRes int header) {
    setHeader(header, 0);
  }

  public void setHeader(@LayoutRes int header, int index) {
    setHeader(LayoutInflater.from(mRecyclerView.getContext()).inflate(header, null), index);
  }

  /**
   * add a header,if a view exists at the index,replace it
   *
   * @param header header
   * @param index index
   */
  public void setHeader(View header, int index) {
    if (mHeaderLayout == null || mHeaderLayout.getChildCount() <= index) {
      addHeaderView(header, index);
    } else {
      replaceHeaderView(header, index);
    }
  }

  private void addHeaderView(View header, int index) {
    //如果没有 header layout ，先创建
    if (mHeaderLayout == null) {
      initHeaderLayout();
    }
    //添加 header
    mHeaderLayout.addView(header, index);
    notifyItemInserted(0);
  }

  private void replaceHeaderView(View header, int index) {
    mHeaderLayout.removeViewAt(index);
    mHeaderLayout.addView(header, index);
    notifyItemChanged(0);
  }

  private void initHeaderLayout() {
    mHeaderLayout = new LinearLayout(mContext);

    RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
    int orientation;
    if (layoutManager instanceof LinearLayoutManager) {
      orientation = ((LinearLayoutManager) layoutManager).getOrientation();
      if (orientation == 0) {
        mHeaderLayout.setOrientation(LinearLayout.HORIZONTAL);
        mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
      } else {
        mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
        mHeaderLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
      }
    } else {
      throw new RuntimeException(
          "get orientation on a LayoutManager which is not instant of LinearLayoutManager!");
    }
  }


  /* ********************** footer ********************** */

  /**
   * add a footer,if a view exists at the index,replace it
   *
   * @param footer footer
   * @param index index
   */
  public void setFooter(View footer, int index) {
    if (mFooterLayout == null || mFooterLayout.getChildCount() <= index) {
      addFooterView(footer, index);
    } else {
      replaceFooterView(footer, index);
    }
  }

  private void addFooterView(View footer, int index) {
    //如果没有 footer layout ，先创建
    if (mFooterLayout == null) {
      initFooterLayout();
    }
    //添加  footer
    mFooterLayout.addView(footer, index);
    int position = mData.size();
    if (mHeaderLayout != null) {
      position++;
    }
    notifyItemInserted(position);
  }

  private void replaceFooterView(View footer, int index) {
    mFooterLayout.removeViewAt(index);
    mFooterLayout.addView(footer, index);
    int position = mData.size();
    if (mHeaderLayout != null) {
      position++;
    }
    notifyItemChanged(position);
  }

  private void initFooterLayout() {
    mFooterLayout = new LinearLayout(mContext);

    RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
    int orientation;
    if (layoutManager instanceof LinearLayoutManager) {
      orientation = ((LinearLayoutManager) layoutManager).getOrientation();
      if (orientation == 0) {
        mFooterLayout.setOrientation(LinearLayout.HORIZONTAL);
        mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
      } else {
        mFooterLayout.setOrientation(LinearLayout.VERTICAL);
        mFooterLayout.setLayoutParams(new RecyclerView.LayoutParams(MATCH_PARENT, WRAP_CONTENT));
      }
    } else {
      throw new RuntimeException(
          "get orientation on a LayoutManager which is not instant of LinearLayoutManager!");
    }
  }
}
