package com.ejar.onebuy.me;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.me.LuckyRecordBean;
import com.ejar.onebuy.databinding.AtyLuckyRecordBinding;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * Created by wongxd on 2017/8/10.
 * 幸运记录
 */

@Route(path = "/me/luckyRecord")
public class LuckyRecordActivity extends BaseHeaderBindingActivity<AtyLuckyRecordBinding> {
    private static final int REQUEST_TAKE_PHOTO_PERMISSION = 1;//相机权限

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_lucky_record);
        setToolBarLeftText("个人中心");
        setToolbarTitle("幸运记录");


        adapter = new RvAdapter(new WeakReference<AppCompatActivity>(LuckyRecordActivity.this));
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(3)));

        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getList(true);
            }
        }, bindingView.rv);

        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getList(false);
            }
        });

        bindingView.srl.autoRefresh();

        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter1, View view, int position) {
                Logger.e(view.getId()+"" );
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    ActivityCompat.requestPermissions(LuckyRecordActivity.this,
                            new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1);
                }
//                SendShareActivity.startPostActivity(LuckyRecordActivity.this, new ArrayList<String>(), 3, currentGid + "", currentPid + "");
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_TAKE_PHOTO_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //申请成功，可以拍照
//                takePhoto();
                SendShareActivity.startPostActivity(LuckyRecordActivity.this, new ArrayList<String>(), 3, currentGid + "", currentPid + "");
            } else {
                Toast.makeText(this, "没有权限", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private RvAdapter adapter;


    private int pageNo = 1, totalPage = 1;

    private void getList(final boolean isLoadMore) {
        if (isLoadMore) {
            if (pageNo > totalPage) {
                adapter.loadMoreEnd();
                return;
            }
        } else {
            pageNo = 1;
            totalPage = 1;
        }

        RetrofitUtil.toBaseIntercept(RetrofitImp.getMeApi().luckyRecord(App.token, App.user.getId() + "", pageNo + "", "10"),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        bindingView.srl.finishRefresh();
                        Logger.e(s);
                        LuckyRecordBean bean = new Gson().fromJson(s, LuckyRecordBean.class);

                        if (bean.getCode() != 200) {
                            if (isLoadMore) adapter.loadMoreFail();
                            else bindingView.srl.finishRefresh();
                            return;
                        }

                        totalPage = bean.getData().getTotalPage();
                        if (bean.getData().getList() != null && bean.getData().getList().size() != 0) {
                            ++pageNo;
                        } else {
                            if (isLoadMore) {
                                adapter.loadMoreEnd();
                            } else {
                                bindingView.srl.finishRefresh();
                            }
                            return;
                        }

                        if (isLoadMore) {
                            adapter.addData(bean.getData().getList());
                            adapter.loadMoreComplete();
                        } else {
                            adapter.setNewData(bean.getData().getList());
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isLoadMore) adapter.loadMoreFail();
                        else bindingView.srl.finishRefresh();
                    }
                }, LuckyRecordActivity.this,false));
    }

    private int currentGid, currentPid;

    private class RvAdapter extends BaseQuickAdapter<LuckyRecordBean.DataBean.ListBean, BaseViewHolder> {

        private WeakReference<AppCompatActivity> aty;

        public RvAdapter(WeakReference<AppCompatActivity> aty) {
            super(R.layout.item_rv_lucky_record);
            this.aty = aty;
        }


        @Override
        protected void convert(BaseViewHolder helper, final LuckyRecordBean.DataBean.ListBean item) {

            currentGid = item.getId();
            currentPid = item.getProId();
            ImageView iv = helper.getView(R.id.iv_img);
            ImageLoader.roundImg(item.getProImg(),iv);

            helper.setText(R.id.tv_name, item.getShopName())
                    .setText(R.id.tv_issue, "期数: " + item.getDatenumber())
                    .setText(R.id.tv_lucky_num, "幸运号码: " + item.getWinningNumber())
                    .setText(R.id.tv_need_people, "本期参与: " + item.getNum() + " 人次")
                    .setText(R.id.tv_time, "揭晓时间: " + item.getLotterytime() + "")
                    .addOnClickListener(R.id.tv_share)
            ;

            helper.setVisible(R.id.tv_use, item.getType() == 4);

            //状态1：一般商品 2：十元专区 3：快速专区  4: 虚拟商品
            if (item.getType() == 4) {
                TextView tv = helper.getView(R.id.tv_use);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                             //// TODO: 2017/8/25  虚拟商品使用
                    }
                });
            }


            helper.getView(R.id.tv_name).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", item.getProId() + "").navigation();
                }
            });


        }
    }
}
