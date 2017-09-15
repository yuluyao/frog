package com.capsule.recy;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.capsule.recy.load.BaseLoadDecor;
import com.capsule.recy.load.DefaultLoadDecor;
import com.capsule.recy.multi.MultiEntity;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 14:50
 */
public abstract class Adapter<T, VH extends ViewHolder> extends RecyclerView.Adapter<VH> {

  public interface OnLoadMoreListener {
    void onLoadMore();
  }

  protected Context        mContext;
  protected RecyclerView   mRecyclerView;
  protected LayoutInflater mLayoutInflater;

  protected List<T> mData = new ArrayList<>();

  private SparseIntArray     typeArray;// viewType and layoutId
  /* loadDecor more */
  private OnLoadMoreListener onLoadMoreListener;
  private BaseLoadDecor      loadDecor;
  /* pending */
  public static final int PENDING_ON_LOAD_MORE_LISTENER = 1;

  private static SparseArray<Object> pendingConfig = new SparseArray<>();


  /* ******************************* */

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    mRecyclerView = recyclerView;
    mContext = mRecyclerView.getContext();
    mLayoutInflater = LayoutInflater.from(mContext);
    executePending();
    onSetItemLayout();
  }

  private void putPending(int key, Object o) {
    pendingConfig.put(key, o);
  }

  private void executePending() {
    if (pendingConfig.get(PENDING_ON_LOAD_MORE_LISTENER) != null) {
      setScrollListener((OnLoadMoreListener) pendingConfig.get(PENDING_ON_LOAD_MORE_LISTENER));
    }
    pendingConfig.clear();
  }

  @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    super.onDetachedFromRecyclerView(recyclerView);
    mRecyclerView = null;
  }

  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    VH holder;
    View itemView = mLayoutInflater.inflate(typeArray.get(viewType), parent, false);
    holder = buildStaticHolder(itemView);
    return holder;
  }

  @Override public int getItemViewType(int position) {
    T item = getData(position);
    if (item != null && item instanceof MultiEntity) {
      return ((MultiEntity) item).getItemType();
    } else {
      return super.getItemViewType(position);
    }
  }

  protected void setItemLayout(int layoutId) {
    setItemLayout(0, layoutId);
  }

  protected void setItemLayout(int type, int layoutId) {
    if (typeArray == null) {
      typeArray = new SparseIntArray();
    }
    typeArray.put(type, layoutId);
  }

  /**
   * call {@link #setItemLayout(int, int)} or {@link #setItemLayout(int)}
   */
  protected abstract void onSetItemLayout();

  @SuppressWarnings("unchecked") protected VH buildStaticHolder(View view) {
    Class temp = getClass();
    Class z = null;

    while (z == null && null != temp) {
      z = getInstancedGenericKClass(temp);
      temp = temp.getSuperclass();
    }
    VH VH;
    // 泛型擦除会导致z为null
    if (z == null) {
      VH = (VH) new ViewHolder(view);
    } else {
      VH = createGenericKInstance(z, view);
    }
    return VH != null ? VH : (VH) new ViewHolder(view);
  }

  private Class getInstancedGenericKClass(Class z) {
    Type type = z.getGenericSuperclass();
    if (type instanceof ParameterizedType) {
      Type[] types = ((ParameterizedType) type).getActualTypeArguments();
      for (Type temp : types) {
        if (temp instanceof Class) {
          Class tempClass = (Class) temp;
          if (ViewHolder.class.isAssignableFrom(tempClass)) {
            return tempClass;
          }
        }
      }
    }
    return null;
  }

  @SuppressWarnings("unchecked") private VH createGenericKInstance(Class z, View view) {
    try {
      Constructor constructor;
      // inner and unstatic class
      if (z.isMemberClass() && !Modifier.isStatic(z.getModifiers())) {
        constructor = z.getDeclaredConstructor(getClass(), View.class);
        constructor.setAccessible(true);
        return (VH) constructor.newInstance(this, view);
      } else {
        constructor = z.getDeclaredConstructor(View.class);
        constructor.setAccessible(true);
        return (VH) constructor.newInstance(view);
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

  @Override public void onBindViewHolder(VH holder, int position) {
    convert(holder, getData(position));
  }

  protected abstract void convert(VH holder, T item);

  /* ************************* adapter ************************* */
  @Override public int getItemCount() {
    return mData.size();
  }

  /* **************************** data **************************** */
  @Nullable public  List<T> getData(){
    return mData;
  }


  @Nullable public T getData(int position) {
    if (position < 0) {
      return null;
    }

    if (position < mData.size()) {
      return mData.get(position);
    } else {
      return null;
    }
  }

  public T getLastData() {
    if (mData.size() == 0) {
      return null;
    }
    return mData.get(mData.size() - 1);
  }

  public int getLastDataPosition() {
    int position = mData.size() - 1;
    return position;
  }

  public void setData(List<T> list) {
    if (list == null) {
      list = new ArrayList<>();
    }
    mData.clear();
    mData.addAll(list);
  }

  public void changeData(T data, int position) {
    mData.set(position, data);
  }

  public void addData(List<T> list) {
    if (list == null) {
      list = new ArrayList<>();
    }
    mData.addAll(list);
  }

  public void notifyRefreshCompleted(List<T> data) {
    setData(data);
    notifyDataSetChanged();
    //notifyItemRangeInserted(0,data.size());
    if (null != loadDecor) {
      loadDecor.setAble();//如果刷新之前有加载失败的情况，列表状态会变为不可加载，刷新以后，要使列表变为可加载状态
    }
  }

  /* ************************** loadmore ************************** */
  public void setLoadDecor(BaseLoadDecor decor){
    loadDecor = decor;
  }

  public void setOnLoadMoreListener(OnLoadMoreListener listener) {
    if (mRecyclerView == null) {
      putPending(PENDING_ON_LOAD_MORE_LISTENER, listener);
      return;
    }
    setScrollListener(listener);
  }

  private void setScrollListener(OnLoadMoreListener listener) {
    loadDecor = new DefaultLoadDecor(mRecyclerView);
    onLoadMoreListener = listener;
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (loadDecor.isEnd()) {
          return;
        }
        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        //int visibleChildCount = layoutManager.getChildCount();
        if (mData.size() > 0//有item
            && newState == RecyclerView.SCROLL_STATE_IDLE//没有在滑动
            && !(loadDecor.isLoading())) {//没有正在加载
          View lastVisibleView = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
          int lastVisiblePosition = recyclerView.getChildLayoutPosition(lastVisibleView);
          if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {//到了最底部
            //if (loadDecor.isAble()) {
            //  loadDecor.setBegin();
            //} else {
              loadDecor.setLoading();
              onLoadMoreListener.onLoadMore();
            //}
          }
        }
      }
    });
    //loadDecor.setAble();
  }

  public void notifyLoadMoreCompleted(List<T> data) {
    if (null == data) {
      loadDecor.setFailed();
      return;
    }
    if (data.size() == 0) {
      loadDecor.setEnd();
      notifyDataSetChanged();
      return;
    }
    loadDecor.setAble();
    addData(data);
    notifyDataSetChanged();
    //notifyItemChanged(getLastDataPosition());
    //notifyItemRangeInserted(getLastDataPosition()+1,data.size());
  }
}
