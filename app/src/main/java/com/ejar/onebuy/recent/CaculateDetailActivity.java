package com.ejar.onebuy.recent;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.recent.CaculateDetailBean;
import com.ejar.onebuy.databinding.AtyCaculateDetailBinding;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;
import wang.wongxd.wquicklib.utils.DensityUtil;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.TimeUtil;
import wang.wongxd.wquicklib.utils.itemDecoration.RecycleViewDivider;

/**
 * Created by wongxd on 2017/8/16.
 */

@Route(path = "/recent/caculateDetail")
public class CaculateDetailActivity extends BaseHeaderBindingActivity<AtyCaculateDetailBinding> {
    @Autowired
    String goodNoId;
    private RvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_caculate_detail);
        ARouter.getInstance().inject(this);
        setToolBarLeftText("返回");
        setToolbarTitle("计算详情");

        bindingView.tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = bindingView.tvClose.getText().toString();
                if (text.equals("收起")) {
                    bindingView.llList.setVisibility(View.GONE);
                    bindingView.tvClose.setText("展开");
                } else {
                    bindingView.llList.setVisibility(View.VISIBLE);
                    bindingView.tvClose.setText("收起");
                }
            }
        });


        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        bindingView.rv.addItemDecoration(new RecycleViewDivider(getApplicationContext(), LinearLayout.VERTICAL,DensityUtil.dip2px(2), Color.WHITE));
        getInfo();
    }

    private void getInfo() {
        RetrofitUtil.toBaseIntercept(RetrofitImp.getRecentApi().getCaculateDetail(App.token, goodNoId), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                addDisposable(disposable);
                Logger.e(s);
                CaculateDetailBean caculateDetailBean = new Gson().fromJson(s, CaculateDetailBean.class);
                if (caculateDetailBean.getCode() != 200) {
                    TU.cT(caculateDetailBean.getMsg());
                    return;
                }


                CaculateDetailBean.DataBean data = caculateDetailBean.getData();

                bindingView.tvProName.setText("商品名称:" + data.getProName());
                bindingView.tvIssue.setText("当前期数:" + data.getNowNo() + "        商品所需人数:" + data.getNeedPerson());
                bindingView.tvNumA.setText(Html.fromHtml("= <font color=\"#e66d40\">" + data.getGoodsNo().getNumberA() + "</font>"));

                bindingView.tvNumB.setText(Html.fromHtml("= <font color=\"#e66d40\">" + data.getGoodsNo().getNumberB() + "</font>"));

                bindingView.tvResultNum.setText(Html.fromHtml("幸运号码: <font color=\"#e66d40\">" + data.getGoodsNo().getWinningNumber() + "</font>"));
                adapter.setNewData(data.getUserInfo());
            }

            @Override
            public void onError(Throwable e) {

            }
        }, CaculateDetailActivity.this));
    }

    public static SpannableString getSpanString(Context ctx, String time) {
        Drawable image = ctx.getResources().getDrawable(R.drawable.arrow_red);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        image.setBounds(20, 0, DensityUtil.dip2px(15), DensityUtil.dip2px(15));
        // Replace blank spaces with image icon
        SpannableString sb = new SpannableString(time + "   ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, time.length(), time.length() + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    private static class RvAdapter extends BaseQuickAdapter<CaculateDetailBean.DataBean.UserInfoBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_recent_caculate);
        }

        @Override
        protected void convert(BaseViewHolder helper, CaculateDetailBean.DataBean.UserInfoBean item) {

            String html = Html.toHtml(getSpanString(App.getInstance(), TimeUtil.times(item.getPaytime() + "")))
                    + " <font color=\"#e66d40\">" + item.getWinningNumber() + "</font>";
            Spanned time = Html.fromHtml(html, new Html.ImageGetter() {
                @Override
                public Drawable getDrawable(String source) {
//                    InputStream is = null;
                    try {
//                        is = (InputStream) new URL(source).getContent();
//                        Drawable d = Drawable.createFromStream(is, "src");
//                        d.setBounds(0, 0, d.getIntrinsicWidth(),
//                                d.getIntrinsicHeight());
//                        is.close();
//                        return d;


                        Drawable image = App.getInstance().getResources().getDrawable(R.drawable.arrow_red);
                        image.setBounds(20, 0, DensityUtil.dip2px(20), DensityUtil.dip2px(15));
                        return image;
                    } catch (Exception e) {
                        return null;
                    }
                }
            }, null);
            helper.setText(R.id.tv_time, time)
                    .setText(R.id.tv_name, item.getUser().getNiceName());
        }


    }
}
