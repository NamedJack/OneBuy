package wang.wongxd.wquicklib.custom.weChatPublishImagesDrag;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.hys.utils.AppUtils;
import com.hys.utils.ImageUtils;
import com.hys.utils.InitCacheFileUtils;
import com.hys.utils.SdcardUtils;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import wang.wongxd.wquicklib.R;
import wang.wongxd.wquicklib.base.WBaseApp;

/**
 * 图片拖拽 Activity
 * Created by kuyue on 2017/7/13 上午10:21.
 * 邮箱:595327086@qq.com
 **/
public class PostImagesActivity extends AppCompatActivity {

    public static final String FILE_DIR_NAME = "com.wongxd.wbaselib";//应用缓存地址
    public static final String FILE_IMG_NAME = "images";//放置图片缓存
    public static int IMAGE_SIZE = 9;//可添加图片最大数
    private static final int REQUEST_IMAGE = 1002;

    private ArrayList<String> originImages;//原始图片
    private ArrayList<String> dragImages;//压缩长宽后图片
    private Context mContext;
    private PostArticleImgAdapter postArticleImgAdapter;
    private ItemTouchHelper itemTouchHelper;
    private RecyclerView rcvImg;
    private TextView tv;//删除区域提示

    /**
     * @param context
     * @param images  包含img地址的list
     */
    public static void startPostActivity(Context context, ArrayList<String> images) {
        Intent intent = new Intent(context, PostImagesActivity.class);
        intent.putStringArrayListExtra("img", images);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param images         包含img地址的list
     * @param mostImageCount 可添加图片最大数 默认为9
     */
    public static void startPostActivity(Context context, ArrayList<String> images, int mostImageCount) {
        Intent intent = new Intent(context, PostImagesActivity.class);
        intent.putStringArrayListExtra("img", images);
        context.startActivity(intent);
        IMAGE_SIZE = mostImageCount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_images);
        initData();
        initView();
    }

    private void initData() {
        originImages = getIntent().getStringArrayListExtra("img");
        mContext = getApplicationContext();
        InitCacheFileUtils.initImgDir(FILE_DIR_NAME, FILE_IMG_NAME);//清除图片缓存
        //添加按钮图片资源
        String plusPath = getString(R.string.glide_plus_icon_string) + AppUtils.getPackageInfo(mContext).packageName + "/mipmap/"
                + R.mipmap.mine_btn_plus;
        dragImages = new ArrayList<>();
        originImages.add(plusPath);//添加按键，超过9张时在adapter中隐藏
        dragImages.addAll(originImages);
        new Thread(new MyRunnable(dragImages, originImages, dragImages, myHandler, false)).start();//开启线程，在新线程中去压缩图片
    }

    private void initView() {
        rcvImg = (RecyclerView) findViewById(R.id.rcv_img);
        tv = (TextView) findViewById(R.id.tv);
        initRcv();
    }

    private void initRcv() {

        postArticleImgAdapter = new PostArticleImgAdapter(mContext, dragImages);
        rcvImg.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        rcvImg.setAdapter(postArticleImgAdapter);
        MyCallBack myCallBack = new MyCallBack(postArticleImgAdapter, dragImages, originImages);
        itemTouchHelper = new ItemTouchHelper(myCallBack);
        itemTouchHelper.attachToRecyclerView(rcvImg);//绑定RecyclerView

        //事件监听
        rcvImg.addOnItemTouchListener(new OnRecyclerItemClickListener(rcvImg) {

            @Override
            public void onItemClick(RecyclerView.ViewHolder vh) {
                if (originImages.get(vh.getAdapterPosition()).contains(getString(R.string.glide_plus_icon_string))) {//打开相册
                    MultiImageSelector.create()
                            .showCamera(true)
                            .count(IMAGE_SIZE - originImages.size() + 1)
                            .multi()
                            .start(PostImagesActivity.this, REQUEST_IMAGE);
                } else {
                    ViewBigImageActivity.startActivity(PostImagesActivity.this, vh.getAdapterPosition(), originImages);
                }
            }

            @Override
            public void onItemLongClick(RecyclerView.ViewHolder vh) {
                //如果item不是最后一个，则执行拖拽
                if (vh.getLayoutPosition() != dragImages.size() - 1) {
                    itemTouchHelper.startDrag(vh);
                }
            }
        });

        myCallBack.setDragListener(new MyCallBack.DragListener() {
            @Override
            public void deleteState(boolean delete) {
                if (delete) {
                    tv.setBackgroundResource(R.color.holo_red_dark);
                    tv.setText(getResources().getString(R.string.post_delete_tv_s));
                } else {
                    tv.setText(getResources().getString(R.string.post_delete_tv_d));
                    tv.setBackgroundResource(R.color.holo_red_light);
                }
            }

            @Override
            public void dragState(boolean start) {
                if (start) {
                    tv.setVisibility(View.VISIBLE);
                } else {
                    tv.setVisibility(View.GONE);
                }
            }
        });
    }

    //------------------图片相关-----------------------------

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE && resultCode == RESULT_OK) {//从相册选择完图片
            //压缩图片
            new Thread(new MyRunnable(data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT),
                    originImages, dragImages, myHandler, true)).start();
        }
    }

    /**
     * 另起线程压缩图片
     */
    static class MyRunnable implements Runnable {

        ArrayList<String> images;
        ArrayList<String> originImages;
        ArrayList<String> dragImages;
        Handler handler;
        boolean add;//是否为添加图片

        public MyRunnable(ArrayList<String> images, ArrayList<String> originImages, ArrayList<String> dragImages, Handler handler, boolean add) {
            this.images = images;
            this.originImages = originImages;
            this.dragImages = dragImages;
            this.handler = handler;
            this.add = add;
        }

        @Override
        public void run() {
            SdcardUtils sdcardUtils = new SdcardUtils();
            String filePath;
            Bitmap newBitmap = null;
            int addIndex = originImages.size() - 1;
            for (int i = 0; i < images.size(); i++) {
                if (images.get(i).contains(WBaseApp.getInstance().getString(R.string.glide_plus_icon_string))) {//说明是添加图片按钮
                    continue;
                }

                File src = new File(images.get(i));

                Logger.e("占用存储  " + src.length());

                if (src.length() >= 1024 * 1500) { //如果大于 就压缩

                    //压缩
                    newBitmap = ImageUtils.compressScaleByWH(images.get(i), 720, 1280);

                    //文件地址
                    filePath = sdcardUtils.getSDPATH() + FILE_DIR_NAME + "/"
                            + FILE_IMG_NAME + "/" + String.format("img_%d.jpg", System.currentTimeMillis());
                    //保存图片
                    ImageUtils.save(newBitmap, filePath, Bitmap.CompressFormat.JPEG, true);

                } else {

                    filePath = images.get(i);
                }

                //设置值
                if (!add) {
                    images.set(i, filePath);
                } else {//添加图片，要更新
                    dragImages.add(addIndex, filePath);
                    originImages.add(addIndex++, filePath);
                }
            }
            if (newBitmap != null)
                newBitmap.recycle();
            Message message = new Message();
            message.what = 1;
            handler.sendMessage(message);
        }
    }

    private MyHandler myHandler = new MyHandler(this);

    private static class MyHandler extends Handler {
        private WeakReference<Activity> reference;

        public MyHandler(Activity activity) {
            reference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            PostImagesActivity activity = (PostImagesActivity) reference.get();
            if (activity != null) {
                switch (msg.what) {
                    case 1:
                        activity.postArticleImgAdapter.notifyDataSetChanged();
                        break;
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myHandler.removeCallbacksAndMessages(null);
    }

}
