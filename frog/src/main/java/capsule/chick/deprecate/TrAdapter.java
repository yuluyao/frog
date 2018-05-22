package capsule.chick.deprecate;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import capsule.chick.ChickViewHolder;

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
public abstract class TrAdapter<T, VH extends ChickViewHolder> extends RecyclerView.Adapter<VH> {

  public interface OnLoadMoreListener {
    void onLoadMore();
  }

  protected Context        mContext;
  protected RecyclerView   mRecyclerView;
  protected LayoutInflater mLayoutInflater;

  protected List<T> mData = new ArrayList<>();

  //view type
  public static final int VIEW_TYPE_EMPTY  = -1;
  public static final int VIEW_TYPE_HEADER = -2;
  public static final int VIEW_TYPE_FOOTER = -3;
  public static final int VIEW_TYPE_LOAD   = -4;

  private SparseIntArray typeArray;// viewType and layoutId

  /* 空 */
  private FrameLayout mEmptyLayout;
  private boolean     isDataEmpty;

  /* 头尾 */
  private LinearLayout mHeaderLayout;
  private LinearLayout mFooterLayout;

  /* load more */
  private LoadMoreView       mLoadMoreView;
  private OnLoadMoreListener onLoadMoreListener;

  /* pending */
  private static SparseArray<Object> pendingConfig = new SparseArray<>();

  public static final int PENDING_ON_LOAD_MORE_LISTENER = 1;
  public static final int PENDING_HEADER                = 2;
  public static final int PENDING_HEADER_INDEX          = 3;
  public static final int PENDING_FOOTER                = 4;
  public static final int PENDING_FOOTER_INDEX          = 5;
  public static final int PENDING_EMPTY                 = 6;

  /* ******************************* */

  @Override public void onAttachedToRecyclerView(RecyclerView recyclerView) {
    super.onAttachedToRecyclerView(recyclerView);
    mRecyclerView = recyclerView;
    mContext = mRecyclerView.getContext();
    mLayoutInflater = LayoutInflater.from(mContext);
    executePending();
  }

  private void putPending(int key, Object o) {
    pendingConfig.put(key, o);
  }

  private void executePending() {
    if (pendingConfig.get(PENDING_ON_LOAD_MORE_LISTENER) != null) {
      onLoadMoreListener = (OnLoadMoreListener) pendingConfig.get(PENDING_ON_LOAD_MORE_LISTENER);
      mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
        @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
          if (mLoadMoreView.isEnd()) {
            return;
          }

          RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
          int visibleChildCount = layoutManager.getChildCount();
          if (mData.size() > 0//有item
              && newState == RecyclerView.SCROLL_STATE_IDLE//没有在滑动
              && !mLoadMoreView.isLoading()) {//没有正在加载
            View lastVisibleView = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
            int lastVisiblePosition = recyclerView.getChildLayoutPosition(lastVisibleView);
            if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {//到了最底部

              mLoadMoreView.setStatus(LoadMoreView.LOADING);
              onLoadMoreListener.onLoadMore();
            }
          }
        }
      });
    }
    if (pendingConfig.get(PENDING_EMPTY) != null) {
      int layoutId = (int) pendingConfig.get(PENDING_EMPTY);
      if (!hasEmptyView()) {
        mEmptyLayout = new FrameLayout(mContext);
        RecyclerView.LayoutParams lp =
            new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.MATCH_PARENT);
        mEmptyLayout.setLayoutParams(lp);
      }
      mEmptyLayout.removeAllViews();
      View view = LayoutInflater.from(mContext).inflate(layoutId, mRecyclerView, false);
      mEmptyLayout.addView(view);
    }

    if (pendingConfig.get(PENDING_HEADER) != null) {
      View header = (View) pendingConfig.get(PENDING_HEADER);
      int index = (int) pendingConfig.get(PENDING_HEADER_INDEX);
      if (!hasHeader() || mHeaderLayout.getChildCount() <= index) {
        addHeaderView(header, index);
      } else {
        replaceHeaderView(header, index);
      }
    }

    if (pendingConfig.get(PENDING_FOOTER) != null) {
      View footer = (View) pendingConfig.get(PENDING_FOOTER);
      int index = (int) pendingConfig.get(PENDING_FOOTER_INDEX);
      if (!hasFooter() || mFooterLayout.getChildCount() <= index) {
        addFooterView(footer, index);
      } else {
        replaceFooterView(footer, index);
      }
    }
    pendingConfig.clear();
  }

  @Override public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
    super.onDetachedFromRecyclerView(recyclerView);
    mRecyclerView = null;
  }

  @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
    VH holder;

    switch (viewType) {
      case VIEW_TYPE_EMPTY:
        holder = buildStaticHolder(mEmptyLayout);
        break;
      case VIEW_TYPE_HEADER:
        holder = buildStaticHolder(mHeaderLayout);
        break;
      case VIEW_TYPE_FOOTER:
        holder = buildStaticHolder(mFooterLayout);
        break;
      case VIEW_TYPE_LOAD:
        //holder = buildLoadMoreHolder(parent);
        View load = mLayoutInflater.inflate(mLoadMoreView.getLayoutId(), parent, false);
        holder = buildStaticHolder(load);
        break;
      default:
        //holder = buildDataHolder(parent, viewType);
        View itemView = mLayoutInflater.inflate(typeArray.get(viewType), parent, false);
        holder = buildStaticHolder(itemView);
        break;
    }
    return holder;
  }

  //protected VH buildDataHolder(ViewGroup parent, int viewType) {
  //  return buildStaticHolder(mLayoutInflater.inflate(typeArray.get(viewType), parent, false));
  //}

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

  //private VH buildLoadMoreHolder(ViewGroup parent) {
  //  View view = mLayoutInflater.inflate(mLoadMoreView.getLayoutId(), parent, false);
  //  //View view = getItemView(mLoadMoreView.getLayoutId(), parent);
  //  VH holder = buildStaticHolder(view);
  //  holder.itemView.setOnClickListener(new View.OnClickListener() {
  //    @Override public void onClick(View v) {
  //      //if (mLoadMoreView.getLoadMoreStatus() == LoadMoreView.FAILED) {
  //      //  notifyLoadMoreToLoading();
  //      //}
  //      //if (mEnableLoadMoreEndClick
  //      //    && mLoadMoreView.getLoadMoreStatus() == LoadMoreView.END) {
  //      //  notifyLoadMoreToLoading();
  //      //}
  //    }
  //  });
  //  return holder;
  //}

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
    int type = holder.getItemViewType();
    switch (type) {
      case VIEW_TYPE_EMPTY:
        break;
      case VIEW_TYPE_HEADER:
        break;
      case VIEW_TYPE_FOOTER:
        break;
      case VIEW_TYPE_LOAD:
        mLoadMoreView.convert(holder);
        break;

      default:
        convert(holder, getData(position));
        break;
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

  protected abstract void convert(VH holder, T item);

  //public boolean isBottom() {
  //  LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
  //  //屏幕中最后一个可见子项的position
  //  int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
  //  //当前屏幕所看到的子项个数
  //  int visibleItemCount = layoutManager.getChildCount();
  //  //当前RecyclerView的所有子项个数
  //  int totalItemCount = layoutManager.getItemCount();
  //  //RecyclerView的滑动状态
  //  int state = recyclerView.getScrollState();
  //  if (visibleItemCount > 0
  //      && lastVisibleItemPosition == totalItemCount - 1
  //      && state == RecyclerView.SCROLL_STATE_IDLE) {
  //    return true;
  //  } else {
  //    return false;
  //  }
  //}

  /* ************************* adapter ************************* */
  @Override public int getItemCount() {
    int count = mData.size();
    if (hasEmptyView() && isDataEmpty) {
      return 1;
    }
    if (hasHeader()) {
      count++;
    }
    if (hasFooter()) {
      count++;
    }
    if (hasLoadMoreView()) {
      count++;
    }

    return count;
  }

  @Override public int getItemViewType(int position) {
    int emptyCount = hasEmptyView() && isDataEmpty ? 1 : 0;
    if (position < emptyCount) {
      return VIEW_TYPE_EMPTY;
    }

    position = hasEmptyView() ? position - emptyCount : position;
    int headerCount = hasHeader() ? 1 : 0;
    if (position < headerCount) {
      return VIEW_TYPE_HEADER;
    }

    position = hasHeader() ? position - headerCount : position;
    if (position < mData.size()) {
      return getDataItemViewType(position); //VIEW_TYPE_DATA
    }

    position = hasFooter() ? position - mData.size() : position;
    int footerCount = hasFooter() ? 1 : 0;
    if (position < footerCount) {
      return VIEW_TYPE_FOOTER;
    } else {
      return VIEW_TYPE_LOAD;
    }
  }

  protected int getDataItemViewType(int position) {
    return super.getItemViewType(position);
  }

  /* **************************** data **************************** */
  @Nullable public T getData(int position) {
    if (hasHeader()) {
      position--;
    }

    if (position == -1) {
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
    if (hasHeader()) {
      position++;
    }
    return position;
  }

  public void setData(List<T> list) {
    if (list == null) {
      list = new ArrayList<>();
    }
    mData.clear();
    mData.addAll(list);
    if (mData.size() == 0) {
      isDataEmpty = true;
      mRecyclerView.setEnabled(false);
    } else {
      isDataEmpty = false;
    }
  }

  public void addData(List<T> list) {
    if (list == null) {
      list = new ArrayList<>();
    }
    mData.addAll(list);
    //if (mData.size() == 0) {
    //  isDataEmpty = true;
    //} else {
    //  isDataEmpty = false;
    //}
  }


  /* ********************* refresh ********************* */

  private boolean hasEmptyView() {
    return mEmptyLayout != null;
  }

  public void setEmptyView(int layoutId) {

    if (mRecyclerView == null) {
      putPending(PENDING_EMPTY, layoutId);
      return;
    }
    if (!hasEmptyView()) {
      mEmptyLayout = new FrameLayout(mContext);
      RecyclerView.LayoutParams lp =
          new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
              RecyclerView.LayoutParams.MATCH_PARENT);
      mEmptyLayout.setLayoutParams(lp);
    }
    mEmptyLayout.removeAllViews();
    View view = LayoutInflater.from(mContext).inflate(layoutId, mRecyclerView, false);
    mEmptyLayout.addView(view);
  }

  public void notifyRefreshCompleted(List<T> data) {
    setData(data);
    notifyDataSetChanged();
  }




  /* ************************** loadmore ************************** */

  public void setLoadMoreView(LoadMoreView view) {
    mLoadMoreView = view;
  }

  public boolean hasLoadMoreView() {
    return mLoadMoreView != null;
  }

  public void setOnLoadMoreListener(OnLoadMoreListener listener) {
    if (mRecyclerView == null) {
      putPending(PENDING_ON_LOAD_MORE_LISTENER, listener);
      return;
    }
    onLoadMoreListener = listener;
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        if (mLoadMoreView.isEnd()) {
          return;
        }

        RecyclerView.LayoutManager layoutManager = mRecyclerView.getLayoutManager();
        int visibleChildCount = layoutManager.getChildCount();
        if (mData.size() > 0//有item
            && newState == RecyclerView.SCROLL_STATE_IDLE//没有在滑动
            && !mLoadMoreView.isLoading()) {//没有正在加载
          View lastVisibleView = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
          int lastVisiblePosition = recyclerView.getChildLayoutPosition(lastVisibleView);
          if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {//到了最底部

            mLoadMoreView.setStatus(LoadMoreView.LOADING);
            onLoadMoreListener.onLoadMore();
          }
        }
      }
    });
  }

  public void notifyLoadMoreCompleted(List<T> data) {
    if (null == data) {
      mLoadMoreView.setStatus(LoadMoreView.FAILED);
      return;
    }
    if (data.size() == 0) {
      mLoadMoreView.setStatus(LoadMoreView.END);
      return;
    }
    mLoadMoreView.setStatus(LoadMoreView.IDLE);
    int insertPosition = getLastDataPosition() + 1;
    addData(data);
    notifyItemInserted(insertPosition);
  }




  /* *********************** header *********************** */

  public boolean hasHeader() {
    return mHeaderLayout != null;
  }

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
    if (mRecyclerView == null) {
      putPending(PENDING_HEADER, header);
      putPending(PENDING_HEADER_INDEX, index);
      return;
    }

    if (!hasHeader() || mHeaderLayout.getChildCount() <= index) {
      addHeaderView(header, index);
    } else {
      replaceHeaderView(header, index);
    }
  }

  private void addHeaderView(View header, int index) {
    //如果没有 header layout ，先创建
    if (!hasHeader()) {
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

  public boolean hasFooter() {
    return mFooterLayout != null;
  }

  public void setFooter(View footer) {
    setFooter(footer, 0);
  }

  /**
   * add a footer,if a view exists at the index,replace it
   *
   * @param footer footer
   * @param index index
   */
  public void setFooter(View footer, int index) {
    if (mRecyclerView == null) {
      putPending(PENDING_FOOTER, footer);
      putPending(PENDING_FOOTER_INDEX, index);
      return;
    }

    if (!hasFooter() || mFooterLayout.getChildCount() <= index) {
      addFooterView(footer, index);
    } else {
      replaceFooterView(footer, index);
    }
  }

  private void addFooterView(View footer, int index) {
    //如果没有 footer layout ，先创建
    if (!hasFooter()) {
      initFooterLayout();
    }
    //添加  footer
    mFooterLayout.addView(footer, index);
    int position = mData.size();
    if (hasHeader()) {
      position++;
    }
    notifyItemInserted(position);
  }

  private void replaceFooterView(View footer, int index) {
    mFooterLayout.removeViewAt(index);
    mFooterLayout.addView(footer, index);
    int position = mData.size();
    if (hasHeader()) {
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
