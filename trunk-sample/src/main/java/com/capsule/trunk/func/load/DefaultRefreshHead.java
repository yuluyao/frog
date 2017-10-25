package com.capsule.trunk.func.load;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.capsule.trunk.R;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/10/25 15:34
 */
public class DefaultRefreshHead implements IRefreshHead {

  private TextView tvTip;

  @Override public View getRefreshHead(Context context) {
    View view = LayoutInflater.from(context).inflate(R.layout.layout_default_refresh_head, null);
    tvTip = view.findViewById(R.id.tvTip);
    return view;
  }
}
