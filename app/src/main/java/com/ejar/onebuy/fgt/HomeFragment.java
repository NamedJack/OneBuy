package com.ejar.onebuy.fgt;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.ApiServer;
import com.ejar.onebuy.App;
import com.ejar.onebuy.LoginActivity;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.home.HomeBannerBean;
import com.ejar.onebuy.bean.home.HomeInfoBean;
import com.ejar.onebuy.bean.home.JoinNowBean;
import com.ejar.onebuy.databinding.FgtHomeBinding;
import com.ejar.onebuy.home.HomeApi;
import com.ejar.onebuy.home.fgt.JoinNowFragment;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraScaleTransformer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.rx.RxBus;
import wang.wongxd.wquicklib.base.rx.RxEventCodeType;
import wang.wongxd.wquicklib.base.ui.BaseBindingFragment;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.SystemUtils;
import wang.wongxd.wquicklib.utils.TU;

/**
 * Created by wongxd on 2017/8/4.
 */

public class HomeFragment extends BaseBindingFragment<FgtHomeBinding> {

    //header 的东西
    private UltraViewPager ultraViewpager;
    private LinearLayout llType;
    private LinearLayout llBetter;
    private LinearLayout llFast;
    private LinearLayout llRecord;
    private ViewFlipper vfNotice;
    private LinearLayout llSift;
    private LinearLayout llGift;
    private ImageView ivJin, ivGift;
    private TextView tvJin, tvGift;
    private ImageView ivPopOne;
    private ImageView ivPopTwo;
    private ImageView ivNewOne;
    private ImageView ivNewTwo;


    private List<HomeBannerBean.DataBean.BannerBean> bannerList = new ArrayList<>();

    //end header
    @Override
    public int setContent() {
        return R.layout.fgt_home;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getInfo(false);
                getBanner();
            }
        });
        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.autoRefresh();
    }


    @Override
    public void onStart() {
        super.onStart();
        joinNow();
    }



    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (!bindingView.srl.isRefreshing()) bindingView.srl.autoRefresh();
        }
        super.onHiddenChanged(hidden);
    }

    /**
     * 立即参与
     */
    private void joinNow() {
        //立即参与
        RxBus.getDefault().toObservable(RxEventCodeType.HOME_JOIN_NOW, String.class).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                Log.e("token", App.token);
                if (App.token == null) {
                    TU.cT("登录失效 请重新登录");
                    ARouter.getInstance().build("/base/login").withFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK)
                            .navigation();
                    return;
                }
                RetrofitUtil.getStringInstance().create(HomeApi.class).joinNow(App.token, s).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                            @Override
                            public void onNext(String s, Disposable disposable) {
                                addDisposable(disposable);
                                JoinNowBean joinNowBean = new Gson().fromJson(s, JoinNowBean.class);
                                if (joinNowBean.getCode() == 200) {
                                    JoinNowFragment.showDialog((AppCompatActivity) getActivity(), joinNowBean.getData().getProduct(), new JoinNowFragment.JoinCallback() {
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
                        }, getActivity()));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }


    /**
     * 获取轮播图 和 中奖信息
     */
    private void getBanner() {
        RetrofitUtil.getStringInstance().create(HomeApi.class).getHomeBanner(App.token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressObserver<>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        Logger.e(s);
                        try {
                            HomeBannerBean item = new Gson().fromJson(s, HomeBannerBean.class);
                            setNoticeView(vfNotice, item.data);
                            List<HomeBannerBean.DataBean.BannerBean> imgs = item.data.getList();
                            if (imgs == null || imgs.size() == 0) return;

                            for (HomeBannerBean.DataBean.BannerBean img : imgs) {
                                img.setImg(ApiServer.IMG_HOST + img.getImg());
                            }
                            setBannerView(ultraViewpager, imgs);

                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        bindingView.srl.autoRefresh();
                    }
                }, getActivity(), false));

    }


    private int totalPage = 1;
    private int fastId = 1;
    private int isFast = 0;

    private void getInfo(final boolean isLoadMore) {
        if (!isLoadMore) {
            pageNo = 1;
            totalPage = 1;
        } else {
            if (pageNo > totalPage) {
                adapter.loadMoreEnd();
                return;
            }
        }
        Logger.e("isNew  " + isNew + "  isHot  " + isHot + "  priceAsc " + priceAsc + "  isFast  " + isFast + " token " + App.token
                + "  pageNo " + pageNo + "  fastId " + fastId);

        Map<String, String> params = new HashMap<>();
        if (isFast != 1) {
            params.put("isNew", isNew + "");
            params.put("isHot", isHot + "");
            params.put("priceAsc", priceAsc + "");
            params.put("pageNo", pageNo + "");
            params.put("pageSize", "10");
        } else {
            Logger.e("极速专区   pageNo  " + pageNo);
            params.put("catalogID", "1");
            params.put("pageNo", pageNo + "");
            params.put("pageSize", "10");

        }


        RetrofitUtil.toBaseIntercept(RetrofitImp.getHomeApi().HomeInfo(params, App.token),

                new ProgressObserver<>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {

                        Logger.e(s);
                        addDisposable(disposable);
                        HomeInfoBean homeInfoBean = new Gson().fromJson(s, HomeInfoBean.class);
                        bindingView.srl.finishRefresh();
                        if (homeInfoBean.getCode() != 200) {
                            TU.cT(homeInfoBean.msg);
                            if (isLoadMore) {
                                if (homeInfoBean.msg.equals("暂无商品信息"))
                                    adapter.loadMoreEnd();
                                else
                                    adapter.loadMoreFail();
                            } else {
                                if (homeInfoBean.msg.equals("暂无商品信息"))
                                    adapter.setNewData(null);
                            }
                            return;
                        }


                        try {
//                            fastId = homeInfoBean.getData().getFastCatalogId();

                            totalPage = homeInfoBean.data.getTotalPage();
                            List<HomeInfoBean.DataBean.ProductsBean> items = homeInfoBean.data.getProducts();
                            if (isLoadMore) {
                                if (pageNo > totalPage) {
                                    adapter.loadMoreEnd();
                                } else {
                                    adapter.addData(items);
                                    adapter.loadMoreComplete();
                                    if (items.size() != 0) ++pageNo;
                                }
                            } else {
                                if (isFast != 1) {
                                    updateHeader(homeInfoBean.data);
                                }
                                adapter.setNewData(items);
                                if (items != null && items.size() != 0) ++pageNo;
                            }

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
                }, getActivity(), false));


    }

    private RvAdapter adapter;

    private void initView() {
        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        GridLayoutManager layoutM = new GridLayoutManager(getContext(), 2);
        layoutM.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0)
                    return 2;
                else if (position == adapter.getItemCount() - 1)
                    return 2;
                else return 1;
            }
        });
        bindingView.rv.setLayoutManager(layoutM);
//        bindingView.rv.addItemDecoration(new DividerGridItemDecoration(getContext()));
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getInfo(true);
                getBanner();
            }
        }, bindingView.rv);

        initHeader();
    }

    private static class RvAdapter extends BaseQuickAdapter<HomeInfoBean.DataBean.ProductsBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_home);
        }

        @Override
        protected void convert(BaseViewHolder helper, final HomeInfoBean.DataBean.ProductsBean item) {
            ImageView iv = helper.getView(R.id.iv_img);
            ImageLoader.roundImg(item.getPicture(), iv);

            double tempProgress = (double) item.getAlreadyNum() / (double) item.getExpectNum();
            double progress = tempProgress * 100;
            ProgressBar pb = helper.getView(R.id.pb);
            pb.setProgress((int) progress);

            TextView tvJoin = helper.getView(R.id.tv_join);
            tvJoin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RxBus.getDefault().post(RxEventCodeType.HOME_JOIN_NOW, item.getId() + "");
                }
            });

            helper.setText(R.id.tv_progress, (int) progress + "%")
                    .setText(R.id.tv_name, item.getName());

            helper.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", item.getId() + "").navigation();
                }
            });

        }
    }

    private int pageNo = 1;

    /**
     * 最新 0 否 1：是
     */
    private int isNew = 0;

    /**
     * 最热 0否 1：是
     */
    private int isHot = 1;

    /**
     * 按价格排序序：1升序 2降序
     */
    private int priceAsc = 1;


    /**
     * 获取第4个tab的内容，其本质是把文字与icon结合在一起
     *
     * @param title 第4个tab栏需要显示的内容
     * @return
     */
    public CharSequence getTabTitle(String title) {
        Drawable image = getResources().getDrawable(priceAsc == 1 ? R.drawable.sort_up : R.drawable.sort_down);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        image.setBounds(20, 0, DensityUtil.dip2px(15), DensityUtil.dip2px(15));
        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString(title + "   ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, title.length(), title.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }


    /**
     * 初始化头部
     */
    private void initHeader() {
        View header = View.inflate(getContext(), R.layout.home_header, null);
        ultraViewpager = (UltraViewPager) header.findViewById(R.id.ultra_viewpager);

        llType = (LinearLayout) header.findViewById(R.id.ll_type);
        llBetter = (LinearLayout) header.findViewById(R.id.ll_better);
        llFast = (LinearLayout) header.findViewById(R.id.ll_fast);
        llRecord = (LinearLayout) header.findViewById(R.id.ll_record);

        vfNotice = (ViewFlipper) header.findViewById(R.id.vf_notice);

        //精选 人气最高
        llSift = (LinearLayout) header.findViewById(R.id.ll_sift);
        ivJin = (ImageView) header.findViewById(R.id.iv_jin);
        tvJin = (TextView) header.findViewById(R.id.tv_jin);

        //奖品最大
        llGift = (LinearLayout) header.findViewById(R.id.ll_gift);
        ivGift = (ImageView) header.findViewById(R.id.iv_gift);
        tvGift = (TextView) header.findViewById(R.id.tv_gift);

        //流行 第一个
        ivPopOne = (ImageView) header.findViewById(R.id.iv_popularity_one);

        //流行 第二个
        ivPopTwo = (ImageView) header.findViewById(R.id.iv_popularity_two);

        //新品 第一个
        ivNewOne = (ImageView) header.findViewById(R.id.iv_new_one);
        //新品 第2个
        ivNewTwo = (ImageView) header.findViewById(R.id.iv_new_two);


        TabLayout tab = (TabLayout) header.findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("最热"));
        tab.addTab(tab.newTab().setText("最新"));
        tab.addTab(tab.newTab().setText("最快"));
        tab.addTab(tab.newTab().setText(getTabTitle("价值")));
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch (pos) {
                    case 0:
                        isHot = 1;
                        isNew = 0;
                        isFast = 0;
                        break;

                    case 1:
                        isHot = 0;
                        isNew = 1;
                        isFast = 0;
                        break;
                    case 2:
                        isHot = 0;
                        isNew = 0;
                        priceAsc = 1;
                        isFast = 1;
                        break;

                    case 3:
                        isHot = 0;
                        isNew = 0;
                        isFast = 0;
                        if (priceAsc == 1) priceAsc = 2;
                        else priceAsc = 1;
                        tab.setText(getTabTitle("价值"));
                        break;
                }
                getInfo(false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getText().toString().contains("价值")) {
                    isHot = 0;
                    isNew = 0;
                    if (priceAsc == 1) priceAsc = 2;
                    else priceAsc = 1;
                    tab.setText(getTabTitle("价值"));
                    getInfo(false);
                }
            }
        });


        llType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/home/typeView").navigation();
            }
        });


        llRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (App.user == null) {
                if (App.token == null || TextUtils.isEmpty(App.token)) {
                    TU.cT("登录失效 请重新登录");
                    SystemUtils.cleanTask2Activity(getActivity(), LoginActivity.class);
                } else
                    ARouter.getInstance().build("/me/joinrecord").navigation();

            }
        });

        setBannerView(ultraViewpager, bannerList);

        adapter.setHeaderView(header);
    }

    private void updateHeader(final HomeInfoBean.DataBean info) {
        //精选
        if (info.getCommendProduct() != null) {
            Glide.with(this).load(ApiServer.IMG_HOST + info.getCommendProduct().getPicture())
                    .placeholder(R.drawable.placeholder).into(ivJin);
            ivJin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", info.getCommendProduct().getId() + "")
                            .navigation();
                }
            });

            tvJin.setText(info.getCommendProduct().getName());
        }


        //人气商品
        if (info.getHotProduct() != null && info.getHotProduct().size() != 0) {
            Glide.with(this).load(ApiServer.IMG_HOST + info.getHotProduct().get(0).getPicture())
                    .placeholder(R.drawable.placeholder).into(ivGift);
            tvGift.setText(info.getHotProduct().get(0).getName() + "");
            ivGift.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", info.getHotProduct().get(0).getId() + "")
                            .navigation();
                }
            });

//            if (info.getHotProduct().size() == 2) {
//                Glide.with(this).load(ApiServer.IMG_HOST + info.getHotProduct().get(1).getPicture())
//                        .placeholder(R.drawable.placeholder).into(ivPopTwo);
//                ivPopTwo.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        ARouter.getInstance().build("/base/gooddetail").withString("id", info.getHotProduct().get(1).getId() + "")
//                                .navigation();
//                    }
//                });
//            }

        }
        //新品
        if (info.getNewProduct() != null && info.getNewProduct().size() != 0) {
            Glide.with(this).load(ApiServer.IMG_HOST + info.getNewProduct().get(0).getPicture())
                    .placeholder(R.drawable.placeholder).into(ivNewOne);
            ivNewOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", info.getNewProduct().get(0).getId() + "")
                            .navigation();
                }
            });


            if (info.getHotProduct().size() == 2) {
                Glide.with(this).load(ApiServer.IMG_HOST + info.getNewProduct().get(1).getPicture())
                        .placeholder(R.drawable.placeholder).into(ivNewTwo);
                ivNewTwo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build("/base/gooddetail").withString("id", info.getNewProduct().get(1).getId() + "")
                                .navigation();
                    }
                });
            }

        }

        llFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ARouter.getInstance().build("/home/typeItem").withString("id", info.getFastCatalogId() + "")
                        .withString("title", "人气专区").navigation();
//                ARouter.getInstance().build("/home/speedZone").withString("id",info.getFastCatalogId()+"").navigation();
            }
        });


        llBetter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/home/typeItem").withString("id", info.getHotCatalogId() + "")
                        .withString("title", "优选商品").navigation();
//                ARouter.getInstance().build("/home/betterGood").withString("id",info.getHotCatalogId()+"").navigation();
            }
        });
    }

    /**
     * 设置轮播图
     *
     * @param img
     */
    private void setBannerView(UltraViewPager ultraViewpager, List<HomeBannerBean.DataBean.BannerBean> img) {

        ultraViewpager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
        //initialize UltraPagerAdapter，and add child view to UltraViewPager
        PagerAdapter bannerAdapter = new UltraPagerAdapter(img);
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
                .setMargin(0, 0, DensityUtil.dip2px(5), DensityUtil.dip2px(5))
                .setIndicatorPadding(DensityUtil.dip2px(5));
        //construct built-in indicator, and add it to  UltraViewPager
        ultraViewpager.getIndicator().build();

//        ultraViewpager.setMultiScreen(0.8f);
//        ultraViewpager.setAutoMeasureHeight(true);

        ultraViewpager.setPageTransformer(false, new UltraScaleTransformer());

        //set an infinite loop
        ultraViewpager.setInfiniteLoop(true);
        //enable auto-scroll mode
        if (img.size() > 1) {
            ultraViewpager.setAutoScroll(2000);
        }


    }

    /**
     * 快报轮播
     *
     * @param
     */
    private void setNoticeView(ViewFlipper noticeView, HomeBannerBean.DataBean data) {
        List<String> notices = data.getAd();
        final List<String> ids = data.getStr();

        // 轮播间隔时间为3s
        noticeView.setFlipInterval(3000);
        // 内边距5dp
        noticeView.setPadding(dp2px(10f), dp2px(5f), dp2px(5f), dp2px(5f));
        // 设置enter和leave动画
        noticeView.setInAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.notice_in));
        noticeView.setOutAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.notice_out));

        for (int i = 0; i < notices.size(); i++) {
            // 根据公告内容构建一个TextView
            String notice = notices.get(i);
            final TextView textView = new TextView(getContext());
            textView.setSingleLine();
            textView.setText(Html.fromHtml(notice));
            textView.setTextSize(13f);
            textView.setEllipsize(TextUtils.TruncateAt.END);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setGravity(Gravity.CENTER_VERTICAL);
            // 将公告的位置设置为textView的tag方便点击是回调给用户
            textView.setTag(ids.get(i));
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", textView.getTag().toString()).navigation();
                }
            });
            // 添加到ViewFlipper
            noticeView.addView(textView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.MATCH_PARENT));
        }
        noticeView.startFlipping();
    }


    private int dp2px(float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue,
                getContext().getResources().getDisplayMetrics());
    }


    /**
     * 轮播图适配器
     */
    private static class UltraPagerAdapter extends PagerAdapter {
        private List<HomeBannerBean.DataBean.BannerBean> data;

        public UltraPagerAdapter(List<HomeBannerBean.DataBean.BannerBean> imgList) {
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
        public Object instantiateItem(ViewGroup container, final int position) {
            ImageView iv = new ImageView(container.getContext());
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            iv.setLayoutParams(lp);

            if (Util.isOnMainThread()) {
                Glide.with(container.getContext()).load(data.get(position).getImg())
                        .placeholder(R.drawable.placeholder).crossFade(500)
                        .centerCrop()
                        .into(iv);
            }

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ARouter.getInstance().build("/base/gooddetail").withString("id", data.get(position).getProId() + "").navigation();
                }
            });

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
