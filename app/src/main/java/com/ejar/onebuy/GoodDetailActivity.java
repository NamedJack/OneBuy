package com.ejar.onebuy;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.bean.GoodDetailBean;
import com.ejar.onebuy.bean.home.JoinNowBean;
import com.ejar.onebuy.databinding.AtyGoodDetailBinding;
import com.ejar.onebuy.home.HomeApi;
import com.ejar.onebuy.home.fgt.JoinNowFragment;
import com.ejar.onebuy.util.DialogUtil;
import com.ejar.onebuy.util.ImageLoader;
import com.ejar.onebuy.wxapi.WXEntryActivity;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tmall.ultraviewpager.UltraViewPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.SystemUtils;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.TimeUtil;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * 商品详情
 */
@Route(path = "/base/gooddetail")
public class GoodDetailActivity extends BaseBindingActivity<AtyGoodDetailBinding> {
    @Autowired
    String id; //商品id
    //header
    private UltraViewPager banner;
    private TextView tvProductName;
    private TextView tvIssue;
    private ProgressBar pb;
    private TextView tvNeedPeople;
    private TextView tvLeft;
    private TextView tvLuckyNum;
    private LinearLayout llDetail;
    private LinearLayout llLuckyRecord;
    private LinearLayout llShare;
    private LinearLayout llRules;
    private ImageView ivFavorite;
    private TextView tvNowTime;
    //endHeader


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_good_detail);
        ARouter.getInstance().inject(this);
        if (App.token == null) {
            TU.cT("登录失效 请重新登录");
            SystemUtils.cleanTask2Activity(this, LoginActivity.class);
        }
        initRecycleView();
        bindingView.ivReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getInfo(false);
            }
        });
        bindingView.srl.setEnableLoadmore(false);

        bindingView.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                joinNow();
            }
        });

        bindingView.ivShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtil.showDialog(GoodDetailActivity.this, "分享到微信", "", "确定", "取消", new DialogUtil.DialogListener() {
                    @Override
                    public void left(Dialog dialog) {

                        WXTextObject textObj = new WXTextObject();
                        textObj.text = "我是测试";


                        WXMediaMessage msg = new WXMediaMessage();
                        msg.mediaObject = textObj;
                        msg.description = "一直夺宝 商品分享";


                        SendMessageToWX.Req req = new SendMessageToWX.Req();
                        req.transaction = buildTransaction("text"); // transaction 用于唯一标识
                        req.message = msg;
                        req.scene = SendMessageToWX.Req.WXSceneSession;



                        App.wxApi.sendReq(req);
                        WXEntryActivity.isShare = true;
                    }

                    @Override
                    public void right(Dialog dialog) {

                    }
                });
            }
        });

        bindingView.srl.autoRefresh();
    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    /**
     * 立即参与
     */
    private void joinNow() {
        //立即参与
        RetrofitUtil.getStringInstance().create(HomeApi.class).joinNow(App.token, id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        Logger.e(s);
                        addDisposable(disposable);
                        JoinNowBean joinNowBean = new Gson().fromJson(s, JoinNowBean.class);
                        if (joinNowBean.getCode() == 200) {
                            JoinNowFragment.showDialog(GoodDetailActivity.this, joinNowBean.getData().getProduct(), new JoinNowFragment.JoinCallback() {
                                @Override
                                public void doRefresh() {
                                    bindingView.srl.autoRefresh();
                                }
                            });
                        } else
                            TU.cT(joinNowBean.getMsg());
                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("参与失败！ " + e.getMessage());
                    }
                }, GoodDetailActivity.this));

    }

    private View initHeader() {
        View headerView = View.inflate(this, R.layout.good_detail_header, null);

        ivFavorite = (ImageView) headerView.findViewById(R.id.iv_favorite);
        banner = (UltraViewPager) headerView.findViewById(R.id.ultra_viewpager);

        tvNowTime = (TextView) headerView.findViewById(R.id.tv_now_time);
        tvProductName = (TextView) headerView.findViewById(R.id.tv_product_name);

        tvIssue = (TextView) headerView.findViewById(R.id.tv_issue);

        pb = (ProgressBar) headerView.findViewById(R.id.pb);

        tvNeedPeople = (TextView) headerView.findViewById(R.id.tv_need_people);

        tvLeft = (TextView) headerView.findViewById(R.id.tv_left);

        llRules = (LinearLayout) headerView.findViewById(R.id.ll_rules);
        llRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoodDetailActivity.this, RulesActivity.class));
            }
        });

        tvLuckyNum = (TextView) headerView.findViewById(R.id.tv_lucky_num);

        llDetail = (LinearLayout) headerView.findViewById(R.id.ll_detail);

        llLuckyRecord = (LinearLayout) headerView.findViewById(R.id.ll_lucky_record);

        llShare = (LinearLayout) headerView.findViewById(R.id.ll_share);

        return headerView;
    }


    private RvAdapter adapter;

    private void initRecycleView() {
        adapter = new RvAdapter();

        adapter.addHeaderView(initHeader());
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RVItemDecoration(LinearLayoutManager.VERTICAL, DensityUtil.dip2px(5)));
        adapter.setHeaderView(initHeader());

        TextView tvEmpty = new TextView(this);
        tvEmpty.setText("暂无参与者信息");
        tvEmpty.setGravity(Gravity.CENTER);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tvEmpty.setLayoutParams(lp);
        tvEmpty.setPadding(0, 10, 0, 10);
        adapter.setEmptyView(tvEmpty);
        adapter.setHeaderAndEmpty(true);

        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getInfo(true);
            }
        }, bindingView.rv);
        setBannerView(banner, new ArrayList<String>());
    }

    private class RvAdapter extends BaseQuickAdapter<GoodDetailBean.DataBean.RecodeBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_good_detail);
        }


        @Override
        protected void convert(BaseViewHolder helper, GoodDetailBean.DataBean.RecodeBean item) {
            ImageView iv = helper.getView(R.id.iv_header);
            ImageLoader.cicleImg(item.getUser().getHeadImg(),iv);

            helper.setText(R.id.tv_nick_name, item.getUser().getNiceName() + "")
//                    .setText(R.id.tv_ip, item.getIpAddr() + "(" + item.getIp() + ")")
                    .setText(R.id.tv_people_num, item.getNum() + "")
                    .setText(R.id.tv_time, TimeUtil.times(item.getCreatTime() + ""));

            if (item.getUser().getZhuiQi() != 0) {
                helper.setVisible(R.id.tv_auto_join, true);
            } else {
                helper.setVisible(R.id.tv_auto_join, false);
            }
        }
    }

    private int pageNo = 1, totalPage = 1;

    public void getInfo(final boolean isLoadMore) {
        if (!isLoadMore) {
            pageNo = 1;
            totalPage = 1;
        } else {
            if (pageNo > totalPage) {
                adapter.loadMoreEnd();
                return;
            }
        }
        RetrofitUtil.toBaseIntercept(RetrofitUtil.getStringInstance().create(HomeApi.class).getGoodDetail(App.token, id, pageNo + "", "10"),
                new ProgressObserver<>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        Logger.e(s);
                        GoodDetailBean dataBeanBaseResponse = new Gson().fromJson(s, GoodDetailBean.class);
                        addDisposable(disposable);
                        bindingView.srl.finishRefresh();
                        try {
                            if (dataBeanBaseResponse.getCode() != 200) {
                                if (isLoadMore) {
                                    adapter.loadMoreFail();
                                } else {
                                    bindingView.srl.finishRefresh();
                                }
                                TU.cT(dataBeanBaseResponse.getMsg() + "");
                                return;
                            }


                            List<GoodDetailBean.DataBean.RecodeBean> records = dataBeanBaseResponse.getData().getRecode();

                            totalPage = dataBeanBaseResponse.getData().getTotalPage();
                            if (isLoadMore) {

                                if (records == null) {
                                    adapter.loadMoreFail();
                                } else if (records.size() == 0 || pageNo > totalPage) {
                                    adapter.loadMoreEnd();
                                } else {
                                    adapter.addData(records);
                                    adapter.loadMoreComplete();
                                }
                            } else {
                                updateHeader(dataBeanBaseResponse.getData());
                                adapter.setNewData(records);
                            }

                            if (records != null && records.size() != 0) ++pageNo;

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isLoadMore) {
                            adapter.loadMoreFail();
                        } else {
                            bindingView.srl.finishRefresh();
                        }
                    }

                }, GoodDetailActivity.this,false));

    }


    private boolean isFavoriteRunning = false;

    private boolean isFavorite = false;

    /**
     * 添加收藏
     */
    public void addFavorite() {

        RetrofitImp.getFavoriteApi().addCollection(App.token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                        isFavoriteRunning = true;
                    }

                    @Override
                    public void onNext(String s) {

                        Logger.e(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optInt("code") == 200) {
                                TU.cT("添加收藏");
                                isFavorite = true;
                                ivFavorite.setImageResource(R.drawable.icon_favorite);
                            } else TU.cT("收藏失败");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            isFavoriteRunning = false;
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("收藏失败");
                        isFavoriteRunning = false;
                    }

                    @Override
                    public void onComplete() {
                        Logger.e("添加收藏  onComplete");
                        isFavoriteRunning = false;
                    }
                });
    }

    /**
     * 取消收藏
     */
    public void deleteFavorite() {

        RetrofitImp.getFavoriteApi().cancelCollection(App.token, id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                        isFavoriteRunning = true;
                    }

                    @Override
                    public void onNext(String s) {
                        Logger.e(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            if (jsonObject.optInt("code") == 200) {
                                TU.cT("取消收藏");
                                isFavorite = false;
                                ivFavorite.setImageResource(R.drawable.icon_un_favorite);
                            } else TU.cT("取消收藏失败");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } finally {
                            isFavoriteRunning = false;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("取消收藏失败");
                        isFavoriteRunning = false;
                    }

                    @Override
                    public void onComplete() {
                        Logger.e("取消收藏  onComplete");
                        isFavoriteRunning = false;
                    }
                });
    }

    private void updateHeader(GoodDetailBean.DataBean data) {
        final GoodDetailBean.DataBean.ProductBean productBean = data.getProduct();

        List<String> list = new ArrayList<String>();
        String[] imgs = productBean.getPicture().split(",");
        for (String img : imgs) {
            list.add(ApiServer.IMG_HOST + img);
        }
        setBannerView(banner, list);

        double progress = ((double) (productBean.getAlreadyNum()) / (double) (productBean.getExpectNum())) * 100.00;


        tvProductName.setText(productBean.getName());
        tvIssue.setText("期刊:" + productBean.getNowNo());
        tvNeedPeople.setText("总需" + productBean.getExpectNum() + "人次");
        tvLeft.setText(productBean.getExpectNum() - productBean.getAlreadyNum() + "");
        pb.setProgress((int) progress);

        if (data.getWiningNumbers() != null) {
            String luckyNum = "";
            if (data.getWiningNumbers().size() != 0) {
                for (GoodDetailBean.DataBean.WiningNumbersBean number : data.getWiningNumbers()) {
                    luckyNum += "  " + number.getWinningNumber();
                }
            } else luckyNum = " 暂未参加";
            tvLuckyNum.setText("往期幸运号码" + luckyNum);
        } else tvLuckyNum.setText("暂未参加");

        tvNowTime.setText(data.getNowTime());

        isFavorite = productBean.getIsCollection() == 1;

        if (isFavorite) {
            ivFavorite.setImageResource(R.drawable.icon_favorite);
        } else {
            ivFavorite.setImageResource(R.drawable.icon_un_favorite);
        }

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isFavoriteRunning) return;

                if (isFavorite)
                    deleteFavorite();
                else
                    addFavorite();
            }
        });

        llDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/base/imgAndTxtDetail").withString("json", productBean.getProductHTML()).navigation();
            }
        });

        llLuckyRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/base/previousLucky")
                        .withString("id", productBean.getId() + "")
                        .navigation();
            }
        });

        llShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/base/shareRecord")
                        .withString("id", productBean.getId() + "")
                        .navigation();
            }
        });
    }

    /**
     * 设置轮播图
     *
     * @param imgUrl
     */
    private void setBannerView(UltraViewPager ultraViewpager, List<String> imgUrl) {

        ultraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //initialize UltraPagerAdapter，and add child view to UltraViewPager
        PagerAdapter bannerAdapter = new UltraPagerAdapter(imgUrl);
        ultraViewpager.setAdapter(bannerAdapter);

        //initialize built-in indicator
        ultraViewpager.initIndicator();
        //set style of indicators
        ultraViewpager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.RED)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        //set the alignment
        ultraViewpager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM)
                .setMargin(DensityUtil.dip2px(5), 0, 0, DensityUtil.dip2px(5));
        //construct built-in indicator, and add it to  UltraViewPager
        ultraViewpager.getIndicator().build();

        //set an infinite loop
        ultraViewpager.setInfiniteLoop(true);
        //enable auto-scroll mode
        if(imgUrl.size() > 1){
            ultraViewpager.setAutoScroll(2000);
        }


    }

    /**
     * 轮播图适配器
     */
    private static class UltraPagerAdapter extends PagerAdapter {
        private List<String> data;

        public UltraPagerAdapter(List<String> imgList) {
            data = imgList;
        }

        @Override
        public int getCount() {
            return data == null ? 0 : data.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView iv = new ImageView(container.getContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);

            Glide.with(container.getContext()).load(data.get(position))
                    .placeholder(R.drawable.placeholder).crossFade(500)
                    .into(iv);

            container.addView(iv);
            return iv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ImageView iv = (ImageView) object;
            container.removeView(iv);
        }
    }
}
