//package yuluyao.frog.load;
//
//import android.support.annotation.LayoutRes;
//
//import androidx.recyclerview.widget.RecyclerView;
//import yuluyao.frog.decor.FootDecor;
//
//
///**
// * Created by wusheng on 2017/9/3.
// */
//
//public abstract class SimpleLoadDecor extends BaseLoadDecor {
//
//  public SimpleLoadDecor(RecyclerView recyclerView) {
//    super(recyclerView);
//  }
//
//  @Override public RecyclerView.ItemDecoration onGetTipDecor() {
//    return new FootDecor(onGetTipLayout());
//  }
//
//  @Override public RecyclerView.ItemDecoration onGetLoadingDecor() {
//    return new FootDecor(onGetLoadingLayout());
//  }
//
//  @Override public RecyclerView.ItemDecoration onGetFailedDecor() {
//    return new FootDecor(onGetFailedLayout());
//  }
//
//  @Override public RecyclerView.ItemDecoration onGetEndDecor() {
//    return new FootDecor(onGetEndLayout());
//  }
//
//  public abstract @LayoutRes int onGetTipLayout();
//
//  public abstract @LayoutRes int onGetLoadingLayout();
//
//  public abstract @LayoutRes int onGetFailedLayout();
//
//  public abstract @LayoutRes int onGetEndLayout();
//}
