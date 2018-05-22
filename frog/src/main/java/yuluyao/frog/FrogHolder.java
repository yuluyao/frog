package yuluyao.frog;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * 描 述：
 * 作 者：Vegeta Yu
 * 时 间：2017/8/9 14:49
 */
public class FrogHolder extends RecyclerView.ViewHolder {

  private List<Integer> clickableId = new ArrayList<>();

  public FrogHolder(View itemView) {
    super(itemView);
  }

  /* child click */
  public FrogHolder setClickableId(int... id) {
    for (int i : id) {
      if (!clickableId.contains(i)) {
        clickableId.add(i);
      }
    }
    return this;
  }

  public List<Integer> getClickableId() {
    return clickableId;
  }

  /* View */

  public FrogHolder setVisibility(int id, int visibility) {
    itemView.findViewById(id).setVisibility(visibility);
    return this;
  }

  /* TextView */

  public TextView findTextViewById(int id) {
    return (TextView) itemView.findViewById(id);
  }

  public FrogHolder setText(int id, String text) {
    ((TextView) itemView.findViewById(id)).setText(text);
    return this;
  }

  public FrogHolder setTextColor(int id, int color) {
    ((TextView) itemView.findViewById(id)).setTextColor(color);
    return this;
  }

  public FrogHolder setTextSize(int id, int size) {
    ((TextView) itemView.findViewById(id)).setTextSize(size);
    return this;
  }

  /* ImageView */

  public ImageView findImageViewById(int id) {
    return (ImageView) itemView.findViewById(id);
  }

  public FrogHolder setImageResource(int id, int res) {
    ((ImageView) itemView.findViewById(id)).setImageResource(res);
    return this;
  }

  public FrogHolder setImageUrl(int id, String url) {
    ((ImageView) itemView.findViewById(id)).setImageURI(Uri.parse(url));
    return this;
  }

  public FrogHolder setScaleType(int id, ImageView.ScaleType ScaleType) {
    ((ImageView) itemView.findViewById(id)).setScaleType(ScaleType);
    return this;
  }

}
