package wang.wongxd.wquicklib.custom.weChatPublishImagesDrag;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.FutureTarget;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import wang.wongxd.wquicklib.R;
import wang.wongxd.wquicklib.base.rx.RxBus;
import wang.wongxd.wquicklib.base.rx.RxEventCodeType;
import wang.wongxd.wquicklib.custom.pinchImageview.PinchImageView;
import wang.wongxd.wquicklib.utils.TU;


/**
 * Created by wongxd
 *
 * 用于查看大图
 *
 *
 */
public class ViewBigImageActivity extends AppCompatActivity implements OnPageChangeListener {

    // 保存图片
    private TextView tv_save_big_image;
    // 接收传过来的uri地址
    List<String> imageurl;
    // 用于管理图片的滑动
    ViewPager very_image_viewpager;

    // 当前页数
    private int page;
    //显示当前图片的页数
    TextView very_image_viewpager_text;

    ViewPagerAdapter adapter;


    //是否显示 保存按钮
    private boolean isCanSave;

    public static final String URL = "imageurl";

    /**
     *
     * @param activity
     * @param position 当前 img position
     * @param urlList
     * @param transitionView
     */
    public static void startActivity(AppCompatActivity activity, int position, ArrayList<String> urlList, View transitionView) {
        Intent intent = new Intent(activity, ViewBigImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(URL, urlList);
        bundle.putInt("page", position);
        intent.putExtras(bundle);

        // 这里指定了共享的视图元素
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, transitionView,
                        activity.getResources().getString(R.string.view_big_img_transitions_name));

        ActivityCompat.startActivity(activity, intent, options.toBundle());

    }


    public static void startActivity(AppCompatActivity activity, int position, ArrayList<String> urlList) {
        Intent intent = new Intent(activity, ViewBigImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(URL, urlList);
        bundle.putInt("page", position);
        intent.putExtras(bundle);


        activity.startActivity(intent);

    }

    public static void startActivity(AppCompatActivity activity, int position, ArrayList<String> urlList,boolean isShowSaveBtn) {
        Intent intent = new Intent(activity, ViewBigImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(URL, urlList);
        bundle.putInt("page", position);
        bundle.putBoolean("isCanSave", isShowSaveBtn);
        intent.putExtras(bundle);


        activity.startActivity(intent);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_big_image);

        getView();
    }

    /**
     * 保存图片至相册
     */
    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), context.getPackageName());
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
//        try {
//            MediaStore.Images.Media.insertImage(context.getContentResolver(),
//                    file.getAbsolutePath(), fileName, null);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        // 最后通知图库更新
//        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsoluteFile())));
    }


    /**
     * Glide 获得图片缓存路径
     */
    private String getImagePath(String imgUrl) {
        String path = null;
        FutureTarget<File> future = Glide.with(ViewBigImageActivity.this)
                .load(imgUrl)
                .downloadOnly(500, 500);
        try {
            File cacheFile = future.get();
            path = cacheFile.getAbsolutePath();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return path;
    }


    /*
     * 接收控件
     */
    private void getView() {
        /************************* 接收控件 ***********************/
        very_image_viewpager_text = (TextView) findViewById(R.id.very_image_viewpager_text);
        tv_save_big_image = (TextView) findViewById(R.id.tv_save_big_image);
        very_image_viewpager = (ViewPager) findViewById(R.id.very_image_viewpager);

        tv_save_big_image.setVisibility(isCanSave?View.VISIBLE:View.GONE);

        tv_save_big_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TU.cT("开始下载图片");
                final BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // 子线程获得图片路径
                        final String imagePath = getImagePath(imageurl.get(page));
                        // 主线程更新
                        ViewBigImageActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (imagePath != null) {
                                    Bitmap bitmap = BitmapFactory.decodeFile(imagePath, bmOptions);
                                    if (bitmap != null) {
                                        saveImageToGallery(ViewBigImageActivity.this, bitmap);
                                        TU.cT("已保存至" + Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + getPackageName());
                                    }
                                }
                            }
                        });
                    }
                }).start();

            }
        });
        /************************* 接收传值 ***********************/
        Bundle bundle = getIntent().getExtras();
        page = bundle.getInt("page");
        imageurl = bundle.getStringArrayList("imageurl");
        isCanSave = bundle.getBoolean("isCanSave");
        /**
         * 给viewpager设置适配器
         */
        adapter = new ViewPagerAdapter();
        very_image_viewpager.setAdapter(adapter);
        very_image_viewpager.setCurrentItem(page);
        very_image_viewpager.addOnPageChangeListener(this);
        very_image_viewpager.setEnabled(false);
        // 设定当前的页数和总页数
        very_image_viewpager_text.setText((page + 1) + " / " + imageurl.size());

    }


    /**
     * ViewPager的适配器
     *
     * @author guolin
     */
    class ViewPagerAdapter extends PagerAdapter {

        LayoutInflater inflater;

        ViewPagerAdapter() {
            inflater = getLayoutInflater();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = inflater.inflate(R.layout.viewpager_very_image, container, false);
            final PinchImageView zoom_image_view = (PinchImageView) view.findViewById(R.id.zoom_image_view);
            final ProgressBar spinner = (ProgressBar) view.findViewById(R.id.loading);
            // 保存网络图片的路径
            String adapter_image_Entity = (String) getItem(position);
            Logger.e("图片地址--" + adapter_image_Entity);
            spinner.setVisibility(View.VISIBLE);
            spinner.setClickable(false);
            Glide.with(ViewBigImageActivity.this).load(adapter_image_Entity)
                    .crossFade(700)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            TU.cT( "资源加载异常");
                            spinner.setVisibility(View.GONE);
                            return false;
                        }

                        //这个用于监听图片是否加载完成
                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                                       boolean isFromMemoryCache, boolean isFirstResource) {
                            spinner.setVisibility(View.GONE);

                            /**这里应该是加载成功后图片的高*/
                            int height = zoom_image_view.getHeight();

                            int wHeight = getWindowManager().getDefaultDisplay().getHeight();
                            if (height > wHeight) {
                                zoom_image_view.setScaleType(ImageView.ScaleType.CENTER_CROP);
                            } else {
                                zoom_image_view.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            }
                            return false;
                        }
                    }).into(zoom_image_view);

            container.addView(view, 0);
            return view;
        }

        @Override
        public int getCount() {
            if (imageurl == null || imageurl.size() == 0) {
                return 0;
            }
            return imageurl.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

        Object getItem(int position) {
            return imageurl.get(position);
        }
    }

    /**
     * 下面是对Viewpager的监听
     */
    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }

    /**
     * 本方法主要监听viewpager滑动的时候的操作
     */
    @Override
    public void onPageSelected(int arg0) {
        // 每当页数发生改变时重新设定一遍当前的页数和总页数
        very_image_viewpager_text.setText((arg0 + 1) + " / " + imageurl.size());
        page = arg0;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.getDefault().post(RxEventCodeType.VIEW_BIG_IMG_LIST_POSTION_CHANGE, page - 1);
    }
}
