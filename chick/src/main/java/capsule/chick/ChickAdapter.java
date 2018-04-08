package capsule.chick;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import capsule.chick.click.RetryListener;
import capsule.chick.diff.DiffCallback;
import capsule.chick.load.BaseLoadDecor;
import capsule.chick.load.DefaultLoadDecor;
import capsule.chick.multi.MultiEntity;
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
public abstract class ChickAdapter<T, VH extends ChickViewHolder> extends RecyclerView.Adapter<VH> {

  public interface OnLoadMoreListener {
    void onLoadMore();
  }

  public interface OnRetryListener {
    void onRetry();
  }

  protected Context        mContext;
  protected RecyclerView   mRecyclerView;
  protected LayoutInflater mLayoutInflater;
  private List<T> mData = new ArrayList<>();
  private SparseIntArray mTypeArray;// viewType and layoutId

  /* loadDecor more */
  private BaseLoadDecor loadDecor;

  /* pending */
  private static final int PENDING_ON_LOAD_MORE_LISTENER = 1;
  private static final int PENDING_RETRY_LOAD_LISTENER   = 2;

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
      setupLoadMore((OnLoadMoreListener) pendingConfig.get(PENDING_ON_LOAD_MORE_LISTENER));
    }
    if (pendingConfig.get(PENDING_RETRY_LOAD_LISTENER) != null) {
      setupRetry((OnRetryListener) pendingConfig.get(PENDING_RETRY_LOAD_LISTENER));
    }
    pendingConfig.clear();
  }

  @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    super.onDetachedFromRecyclerView(recyclerView);
    mRecyclerView = null;
  }

  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    VH holder;
    View itemView = mLayoutInflater.inflate(mTypeArray.get(viewType), parent, false);
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
    if (mTypeArray == null) {
      mTypeArray = new SparseIntArray();
    }
    mTypeArray.put(type, layoutId);
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
      VH = (VH) new ChickViewHolder(view);
    } else {
      VH = createGenericKInstance(z, view);
    }
    return VH != null ? VH : (VH) new ChickViewHolder(view);
  }

  private Class getInstancedGenericKClass(Class z) {
    Type type = z.getGenericSuperclass();
    if (type instanceof ParameterizedType) {
      Type[] types = ((ParameterizedType) type).getActualTypeArguments();
      for (Type temp : types) {
        if (temp instanceof Class) {
          Class tempClass = (Class) temp;
          if (ChickViewHolder.class.isAssignableFrom(tempClass)) {
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

  //@Override public void onBindViewHolder(VH holder, int position, List<Object> payloads) {
  //  if (payloads.isEmpty()) {
  //    onBindViewHolder(holder, position);
  //  } else {
  //    Log.i("vegeta", payloads.size() + "");
  //  }
  //}

  protected abstract void convert(VH holder, T item);

  /* ************************* adapter ************************* */
  @Override public int getItemCount() {
    return mData.size();
  }

  /* **************************** data **************************** */
  @Nullable public List<T> getData() {
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
    int oldSize = getItemCount();
    setData(data);
    if (oldSize == 0) {
      notifyItemRangeInserted(0, data.size());
    } else {
      notifyDataSetChanged();
    }

    //refreshWithDiffUtil(data);

    if (null != loadDecor) {
      loadDecor.setAble();//如果刷新之前有加载失败的情况，列表状态会变为不可加载，刷新以后，要使列表变为可加载状态
    }
  }

  private void refreshWithDiffUtil(List<T> data) {
    List<T> oldData = new ArrayList<>();
    List<T> newData = new ArrayList<>();
    oldData.addAll(getData());
    newData.addAll(data);
    setData(data);
    DiffUtil.calculateDiff(new DiffCallback<>(oldData, newData), true).dispatchUpdatesTo(this);
  }

  /* ************************** loadmore ************************** */
  public void setLoadDecor(BaseLoadDecor decor) {
    loadDecor = decor;
  }

  public void setOnLoadMoreListener(OnLoadMoreListener listener) {
    if (mRecyclerView == null) {
      putPending(PENDING_ON_LOAD_MORE_LISTENER, listener);
      return;
    }
    setupLoadMore(listener);
  }

  public void setOnRetryListener(OnRetryListener listener) {
    if (mRecyclerView == null) {
      putPending(PENDING_RETRY_LOAD_LISTENER, listener);
      return;
    }
    setupRetry(listener);
  }

  private void setupLoadMore(final OnLoadMoreListener listener) {
    if (loadDecor == null) {
      loadDecor = new DefaultLoadDecor(mRecyclerView);
    }
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (!isChildViewFull()) {
          loadDecor.setAble();
          return;
        }

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
            listener.onLoadMore();
            //}
          }
        }
      }
    });
  }

  private boolean isChildViewFull() {
    View lastVisibleView = mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1);
    if (lastVisibleView == null) {
      return false;
    }
    float offset = mContext.getResources().getDisplayMetrics().density * 40;
    if (lastVisibleView.getY() + lastVisibleView.getHeight() + offset
        < mRecyclerView.getY() + mRecyclerView.getHeight()) {
      return false;
    }
    return true;
  }

  private void setupRetry(final OnRetryListener listener) {
    if (loadDecor == null) {
      loadDecor = new DefaultLoadDecor(mRecyclerView);
    }
    mRecyclerView.addOnItemTouchListener(new RetryListener() {
      @Override public void onRetryLoad() {
        if (loadDecor.isFailed()) {
          loadDecor.setLoading();
          listener.onRetry();
        }
      }
    });
  }

  public void notifyLoadMoreCompleted(List<T> data) {
    if (null == data) {//加载失败
      loadDecor.setFailed();
    } else if (data.size() == 0) {//加载为空
      loadDecor.setEnd();
    } else {//正常加载
      loadDecor.setAble();
    }
    addData(data);
    notifyDataSetChanged();

    //loadmoreWithDiffUtil(data);
  }

  private void loadmoreWithDiffUtil(List<T> data) {
    List<T> oldData = new ArrayList<>();
    List<T> newData = new ArrayList<>();
    oldData.addAll(getData());
    newData.addAll(getData());
    newData.addAll(data);
    addData(data);
    DiffUtil.calculateDiff(new DiffCallback<>(oldData, newData), true).dispatchUpdatesTo(this);
  }
}
