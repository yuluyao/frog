package capsule.chick;

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
public class ViewHolder extends RecyclerView.ViewHolder {

  private List<Integer> clickableId = new ArrayList<>();

  public ViewHolder(View itemView) {
    super(itemView);
  }

  /* child click */
  public ViewHolder setClickableId(int... id) {
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

  public ViewHolder setVisibility(int id, int visibility) {
    itemView.findViewById(id).setVisibility(visibility);
    return this;
  }

  /* TextView */

  public TextView findTextViewById(int id) {
    return (TextView) itemView.findViewById(id);
  }

  public ViewHolder setText(int id, String text) {
    ((TextView) itemView.findViewById(id)).setText(text);
    return this;
  }

  public ViewHolder setTextColor(int id, int color) {
    ((TextView) itemView.findViewById(id)).setTextColor(color);
    return this;
  }

  public ViewHolder setTextSize(int id, int size) {
    ((TextView) itemView.findViewById(id)).setTextSize(size);
    return this;
  }

  /* ImageView */

  public ImageView findImageViewById(int id) {
    return (ImageView) itemView.findViewById(id);
  }

  public ViewHolder setImageResource(int id, int res) {
    ((ImageView) itemView.findViewById(id)).setImageResource(res);
    return this;
  }

  public ViewHolder setImageUrl(int id, String url) {
    ((ImageView) itemView.findViewById(id)).setImageURI(Uri.parse(url));
    return this;
  }

  public ViewHolder setScaleType(int id, ImageView.ScaleType ScaleType) {
    ((ImageView) itemView.findViewById(id)).setScaleType(ScaleType);
    return this;
  }

}
