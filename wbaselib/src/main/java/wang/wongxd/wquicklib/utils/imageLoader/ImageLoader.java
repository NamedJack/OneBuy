package wang.wongxd.wquicklib.utils.imageLoader;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ImageLoader {


    public void display(Context ctx, String uri, ImageView imageView) {
        Glide.with(ctx)
                .load(uri)
                .into(imageView);
    }


}