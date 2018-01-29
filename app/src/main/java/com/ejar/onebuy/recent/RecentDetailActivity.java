package com.ejar.onebuy.recent;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.ApiServer;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.recent.RecentDetailBean;
import com.ejar.onebuy.databinding.AtyRecentDetailBinding;
import com.ejar.onebuy.util.CurrentTimeUtils;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmall.ultraviewpager.UltraViewPager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.TimeUtil;
import wang.wongxd.wquicklib.utils.itemDecoration.RVItemDecoration;

/**
 * 最新揭晓详情
 */
@Route(path = "/recent/detail")
public class RecentDetailActivity extends BaseBindingActivity<AtyRecentDetailBinding> {
    @Autowired
    String id; //id
    @Autowired
    String goodsNo;
    @Autowired
    Long kjTime;
    //header
    private UltraViewPager banner;
    private TextView tvProductName;
    private LinearLayout llDetail;
    private LinearLayout llLuckyRecord;
    private LinearLayout llShare;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_recent_detail);
        ARouter.getInstance().inject(this);
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


        bindingView.srl.autoRefresh();
    }


    private TextView tvNickName, tv_user_id, tv_issue, tv_people_num, tv_time,
            tv_see_lucky_num_detail, tv_lucky_num, tvNowTime, tvWaiting, myTimer, waiteMinute;
    private ImageView iv_header;
    private LinearLayout llShowTimeWaite;
    private LinearLayout llShowAnswer;
    private LinearLayout llSeaDetail;
//    private CountdownView myCountdownView;

    private View initHeader() {
        View headerView = View.inflate(this, R.layout.header_recent_detail, null);
        llShowTimeWaite = (LinearLayout) headerView.findViewById(R.id.show_time_waite);
        waiteMinute = (TextView) headerView.findViewById(R.id.waite_minute);
        llShowAnswer = (LinearLayout) headerView.findViewById(R.id.ll_show_answer);
        llSeaDetail = (LinearLayout) headerView.findViewById(R.id.ll_sea_detail);
        tvWaiting = (TextView) headerView.findViewById(R.id.tv_waiting);
        myTimer = (TextView) headerView.findViewById(R.id.timer);
        banner = (UltraViewPager) headerView.findViewById(R.id.ultra_viewpager);

        tvProductName = (TextView) headerView.findViewById(R.id.tv_product_name);
        tvNowTime = (TextView) headerView.findViewById(R.id.tv_now_time);
        iv_header = (ImageView) headerView.findViewById(R.id.iv_header);
        tvNickName = (TextView) headerView.findViewById(R.id.tv_nick_name);

        tv_user_id = (TextView) headerView.findViewById(R.id.tv_user_id);
        tv_issue = (TextView) headerView.findViewById(R.id.tv_issue);
        tv_people_num = (TextView) headerView.findViewById(R.id.tv_people_num);


        tv_time = (TextView) headerView.findViewById(R.id.tv_time);
        tv_lucky_num = (TextView) headerView.findViewById(R.id.tv_lucky_num);
        tv_see_lucky_num_detail = (TextView) headerView.findViewById(R.id.tv_see_lucky_num_detail);


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
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getInfo(true);
            }
        }, bindingView.rv);
        setBannerView(banner, new ArrayList<String>());
    }

    private class RvAdapter extends BaseQuickAdapter<RecentDetailBean.DataBean.RecodeBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_good_detail);
        }


        @Override
        protected void convert(BaseViewHolder helper, RecentDetailBean.DataBean.RecodeBean item) {
            ImageView iv = helper.getView(R.id.iv_header);

            ImageLoader.cicleImg(item.getUser().getHeadImg(), iv);
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
        Logger.e(" goodsNo" + goodsNo + " getProId " + id + "  ");
        RetrofitUtil.toBaseIntercept(RetrofitImp.getRecentApi().getDetail(App.token, goodsNo, id, pageNo + "", "", "", "10"),
                new ProgressObserver<>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
//                        Logger.e(" " + App.token + "  " + id + "  ");
                        Logger.e(s);
                        RecentDetailBean dataBeanBaseResponse = new Gson().fromJson(s, RecentDetailBean.class);
                        addDisposable(disposable);
                        bindingView.srl.finishRefresh();
                        try {
                            if (dataBeanBaseResponse.getCode() != 200) {
                                if (isLoadMore) {
                                    adapter.loadMoreFail();
                                } else {
                                    bindingView.srl.finishRefresh();
                                }
                                return;
                            }


                            List<RecentDetailBean.DataBean.RecodeBean> records = dataBeanBaseResponse.getData().getRecode();

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

                }, RecentDetailActivity.this, false));

    }


    private void updateHeader(final RecentDetailBean.DataBean data) {
        final RecentDetailBean.DataBean.ProductBean productBean = data.getProduct();
        final RecentDetailBean.DataBean.WinnerBean winnerBean = data.getWinner();
        List<String> list = new ArrayList<String>();
        String[] imgs = productBean.getPicture().split(",");
        for (String img : imgs) {
            list.add(ApiServer.IMG_HOST + img);
        }
        setBannerView(banner, list);


        tvProductName.setText(productBean.getName());
        //  1未揭晓 2：已揭晓 3 开奖中

        if (winnerBean.getState() == 2 && winnerBean.getIsOver() == 2) {
            waiteMinute.setVisibility(View.GONE);
            llShowTimeWaite.setVisibility(View.GONE);
            llShowAnswer.setVisibility(View.VISIBLE);
            llSeaDetail.setVisibility(View.VISIBLE);
            ImageLoader.cicleImg(winnerBean.getUser().getHeadImg(), iv_header);
            tvNickName.setText(Html.fromHtml("中奖者: <font color=\"#1779ff\">" + winnerBean.getUser().getNiceName() + "</font>"));
            tv_user_id.setText("用户ID:" + winnerBean.getWinningUserId() + " (唯一不变标识) ");
            tv_issue.setText("期号:" + winnerBean.getDatenumber());
            tv_people_num.setText(productBean.getExpectNum() + "");
            tv_time.setText("开奖时间:" + winnerBean.getLotterytime());

            tv_lucky_num.setText("幸运号码 : " + winnerBean.getWinningNumber());
        } else if (winnerBean.getState() == 1 && winnerBean.getIsOver() == 2) {//显示“开奖中...”
            waiteMinute.setVisibility(View.VISIBLE);
            llShowTimeWaite.setVisibility(View.VISIBLE);
            myTimer.setVisibility(View.GONE);
            tvWaiting.setVisibility(View.VISIBLE);
        } else if (winnerBean.getState() == 1 && winnerBean.getIsOver() == 1) {//展示倒计时
            waiteMinute.setVisibility(View.VISIBLE);
            llShowTimeWaite.setVisibility(View.VISIBLE);
            myTimer.setVisibility(View.VISIBLE);

            long myTime = 0;
            try {
                myTime = (CurrentTimeUtils.getCurrentTime(kjTime) - data.getNowTime() / 1000) * 1000;
            } catch (ParseException e) {
//                Log.e("error", e.toString());
                e.printStackTrace();
            }
            final CountDownTimer timer = new CountDownTimer(myTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int hour = 0;
                    int minute = 0;
                    int second = 0;
                    second = Integer.parseInt(String.valueOf(millisUntilFinished / 1000));
                    if (second > 60) {
                        minute = second / 60;         //取整
                        second = second % 60;         //取余
                    }

                    if (minute > 60) {
                        hour = minute / 60;
                        minute = minute % 60;
                    }
                    //统一格式
                    if (hour > 10 && minute > 10 && second > 10) {
                        myTimer.setText(hour + ":" + minute + ":" + second);
                    } else if (hour < 10 && minute < 10 && second < 10) {
                        myTimer.setText("0" + hour + ":0" + minute + ":0" + second);
                    } else if (hour > 10 && minute < 10 && second > 10) {
                        myTimer.setText(hour + ":0" + minute + ":" + second);
                    } else if (hour > 10 && minute > 10 && second < 10) {
                        myTimer.setText(hour + ":" + minute + ":0" + second);
                    } else if (hour > 10 && minute < 10 && second < 10) {
                        myTimer.setText(hour + ":0" + minute + ":0" + second);
                    } else if (hour < 10 && minute > 10 && second > 10) {
                        myTimer.setText("0" + hour + ":" + minute + ":" + second);
                    } else if (hour < 10 && minute > 10 && second < 10) {
                        myTimer.setText("0" + hour + ":" + minute + ":0" + second);
                    } else if (hour < 10 && minute < 10 && second > 10) {
                        myTimer.setText("0" + hour + ":0" + minute + ":" + second);
                    }

                }

                @Override
                public void onFinish() {
                    myTimer.setText("00:00:00");
                    bindingView.srl.autoRefresh();
                }
            };
            timer.start();

        }


        try {
            tvNowTime.setText(CurrentTimeUtils.parseTime(data.getNowTime()).split(" ")[0]);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tv_see_lucky_num_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/recent/caculateDetail").withString("goodNoId", data.getGoodsNoId() + "").navigation();
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

        bindingView.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/base/gooddetail").withString("id", productBean.getId() + "").navigation();
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
        if (imgUrl.size() > 1) {
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
