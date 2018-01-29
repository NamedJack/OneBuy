package com.ejar.onebuy.me;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ejar.onebuy.App;
import com.ejar.onebuy.RetrofitImp;
import com.hys.utils.AppUtils;
import com.hys.utils.SdcardUtils;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import me.nereo.multi_image_selector.MultiImageSelector;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import wang.wongxd.wquicklib.R;
import wang.wongxd.wquicklib.base.WBaseApp;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.custom.weChatPublishImagesDrag.MyCallBack;
import wang.wongxd.wquicklib.custom.weChatPublishImagesDrag.OnRecyclerItemClickListener;
import wang.wongxd.wquicklib.custom.weChatPublishImagesDrag.PostArticleImgAdapter;
import wang.wongxd.wquicklib.custom.weChatPublishImagesDrag.PostImagesActivity;
import wang.wongxd.wquicklib.custom.weChatPublishImagesDrag.ViewBigImageActivity;
import wang.wongxd.wquicklib.databinding.ActivityPostImagesBinding;
import wang.wongxd.wquicklib.utils.TU;

import static wang.wongxd.wquicklib.custom.weChatPublishImagesDrag.PostImagesActivity.IMAGE_SIZE;

/**
 * 发布晒单
 **/
public class SendShareActivity extends BaseHeaderBindingActivity<ActivityPostImagesBinding> {
//    private String[] permissions = {Manifest.permission.};

    public static final String FILE_DIR_NAME = "com.wongxd.wbaselib";//应用缓存地址
    public static final String FILE_IMG_NAME = "images";//放置图片缓存

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
     * @param images         包含img地址的list
     * @param mostImageCount 可添加图片最大数 默认为9
     * @param gid            晒单id
     * @param pid            商品id
     */
    public static void startPostActivity(Context context, ArrayList<String> images, int mostImageCount, String gid, String pid) {
        Intent intent = new Intent(context, SendShareActivity.class);
        intent.putStringArrayListExtra("img", images);
        intent.putExtra("gid", gid);
        intent.putExtra("pid", pid);
        context.startActivity(intent);
        IMAGE_SIZE = mostImageCount;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_images);
        setToolbarTitle("发表晒单");
        setToolBarRightText("发布");
        setToolBarRightTextColor(Color.RED);
        setToolBarRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    doSend();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        initData();
        initView();
        gid = getIntent().getStringExtra("gid");
        pid = getIntent().getStringExtra("pid");
    }

    private String gid, pid;

    /**
     * 发布
     */
    private void doSend() {

        String content = bindingView.etContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            TU.cT("写点什么吧");
            return;
        }

        if (dragImages.size() == 0) {
            TU.cT("上传图片不存在");
            return;
        }
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("token", App.token)
                .addFormDataPart("gid", gid)
                .addFormDataPart("content", content);
        Logger.e(gid + "  "+App.token+"  " );

        for (int i = 0, length = dragImages.size(); i < length; i++) {
            if (i == length - 1) break;
            if (dragImages.get(i).contains(getString(R.string.glide_plus_icon_string)))
                continue;
            File file = new File(dragImages.get(i));
            Logger.e("fileName" + file.getName() + ".jpg");
            builder.addFormDataPart("imgs", file.getName() + ".jpg", RequestBody.create(MediaType.parse("image/*"), file));
        }


        RequestBody body = builder.build();
        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().unboxing(body),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        Logger.e(s);

//                        {"code":200,"msg":"OK","data":{"msg":"发布晒单信息成功","code":100}}

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optInt("code") == 200) {
                                TU.cT("发布成功");
                                finish();
                            } else TU.cT("发布失败! " + jsonObject.optString("msg"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("发布失败");
                    }
                }, SendShareActivity.this));
    }

    private void initData() {
        originImages = getIntent().getStringArrayListExtra("img");
        mContext = getApplicationContext();
//        InitCacheFileUtils.initImgDir(FILE_DIR_NAME, FILE_IMG_NAME);//清除图片缓存
        //添加按钮图片资源
        String plusPath = getString(R.string.glide_plus_icon_string) + AppUtils.getPackageInfo(mContext).packageName + "/mipmap/"
                + R.mipmap.mine_btn_plus;
        dragImages = new ArrayList<>();
        originImages.add(plusPath);//添加按键，超过最大张时在adapter中隐藏
        dragImages.addAll(originImages);
        new Thread(new MyRunnable(dragImages, originImages, dragImages, myHandler, false)).start();//开启线程，在新线程中去压缩图片
    }

    private void initView() {
        rcvImg = (RecyclerView) findViewById(R.id.rcv_img);
        tv = (TextView) findViewById(R.id.tv);
        initRcv();
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
////        if (requestCode == REQUEST_TAKE_PHOTO_PERMISSION) {
////            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
////                //申请成功，可以拍照
//////                takePhoto();
////            } else {
////                Toast.makeText(this, "CAMERA PERMISSION DENIED", Toast.LENGTH_SHORT).show();
////            }
////            return;
////        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }

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
                            .count(PostImagesActivity.IMAGE_SIZE - originImages.size() + 1)
                            .multi()
                            .start(SendShareActivity.this, REQUEST_IMAGE);
                } else {
                    ArrayList<String> img = new ArrayList<String>();
                    for (String s : dragImages) {
                        if (s.contains(getString(R.string.glide_plus_icon_string))) continue;
                        img.add(s);
                    }
                    ViewBigImageActivity.startActivity(SendShareActivity.this, vh.getAdapterPosition(), img, false);
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
            String filePath = null;
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
                    newBitmap = com.ejar.onebuy.util.ImageUtils.compressScaleByWH(images.get(i), 720, 1280);

                    //文件地址
                    filePath = sdcardUtils.getSDPATH() + FILE_DIR_NAME + "/"
                            + FILE_IMG_NAME + "/" + String.format("img_%d.jpg", System.currentTimeMillis());
                    //保存图片
                    com.ejar.onebuy.util.ImageUtils.save(newBitmap, filePath, Bitmap.CompressFormat.JPEG, true);

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
            SendShareActivity activity = (SendShareActivity) reference.get();
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
