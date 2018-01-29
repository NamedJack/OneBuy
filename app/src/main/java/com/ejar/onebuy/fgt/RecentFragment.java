package com.ejar.onebuy.fgt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.recent.RecentBean;
import com.ejar.onebuy.databinding.FgtRecentBinding;
import com.ejar.onebuy.util.CurrentTimeUtils;
import com.ejar.onebuy.util.ImageLoader;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.iwgang.countdownview.CountdownView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.base.rx.RxBus;
import wang.wongxd.wquicklib.base.rx.RxEventCodeType;
import wang.wongxd.wquicklib.base.ui.BaseBindingFragment;
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.TimeUtil;

/**
 * Created by wongxd on 2017/8/4.
 * <p>
 * 最新揭晓
 */

public class RecentFragment extends BaseBindingFragment<FgtRecentBinding> {

    private RvAdapter adapter;


    @Override
    public int setContent() {
        return R.layout.fgt_recent;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter.setEmptyView(R.layout.item_rv_empty, bindingView.rv);

        bindingView.srl.setEnableLoadmore(false);
        bindingView.srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getList(false);
            }
        });


        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                getList(true);
            }
        }, bindingView.rv);

//        adapter.setOpenJpListener(new RvAdapter.OnOpenJpListener() {
//            @Override
//            public void openId(int id) {
//                openWinner(id + "");
//            }
//        });
        adapter.setReFreshListListener(new RvAdapter.OnReLoadListListener() {
            @Override
            public void listRefesh() {
                bindingView.srl.autoRefresh();
            }
        });
        bindingView.srl.autoRefresh();

    }


    private String currentOpenWinnerId = "current";

    @Override
    public void onStart() {
        super.onStart();
    }

    private void receviceBusInfo() {
        RxBus.getDefault().toObservable(RxEventCodeType.RECENT_COUNTDOWNTIME_END, String.class).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(String s) {
                Logger.e(s);
                if (!currentOpenWinnerId.equals(s)) {
                    currentOpenWinnerId = s;
                }
                onComplete();

            }

            @Override
            public void onError(Throwable e) {


            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (!hidden) {
            if (!bindingView.srl.isRefreshing()) bindingView.srl.autoRefresh();
        }
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onResume() {
        super.onResume();
        bindingView.srl.autoRefresh();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    /**
     * 去开奖
     */
    private void openWinner(String id) {
        RetrofitUtil.toBaseIntercept(RetrofitImp.getRecentApi().openWinner(App.token, id), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                Logger.e(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    if (jsonObject.optInt("code") == 200) {
                        TU.cT("开奖成功");
                        if (!bindingView.srl.isRefreshing()) bindingView.srl.autoRefresh();
                    } else {
                        TU.cT("开奖失败");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    receviceBusInfo();
                }
            }

            @Override
            public void onError(Throwable e) {
                TU.cT("开奖失败");
                receviceBusInfo();
            }
        }, getActivity()));
    }

    private int pageNo = 1;
    private int totalPage = 1;


    private void getList(final boolean isLoadMore) {
        if (!isLoadMore) {
            pageNo = 1;
            totalPage = 1;
        } else {
            if (pageNo > totalPage) {
                adapter.loadMoreEnd();
                return;
            }
        }
        RetrofitUtil.toBaseIntercept(RetrofitImp.getRecentApi().getList(App.token, pageNo + "", "5"),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {
                        addDisposable(disposable);
                        bindingView.srl.finishRefresh();
                        Logger.e(s);
                        Gson gson = new Gson();
                        RecentBean recentBean = gson.fromJson(s, RecentBean.class);
                        if (recentBean.getCode().equals("200")) {
                            totalPage = recentBean.getData().getTotalPage();
                            List<RecentBean.DataBean.AnnouncesBean> list = recentBean.getData().getAnnounces();
                            adapter.setTime(recentBean.getData().getMmpTime());
                            if (isLoadMore) {
                                adapter.addData(list);
                                adapter.loadMoreComplete();
                            } else {
                                adapter.setNewData(list);
                            }

                            if (recentBean.getData().getAnnounces() != null && recentBean.getData().getAnnounces().size() != 0)
                                ++pageNo;


                        } else {
                            TU.cT(recentBean.getMsg());
                            if (isLoadMore) {
                                adapter.loadMoreFail();
                            }

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isLoadMore) adapter.loadMoreFail();
                        else
                            bindingView.srl.finishRefresh();
                    }
                }, getActivity(), false));
    }


    private static class RvAdapter extends BaseQuickAdapter<RecentBean.DataBean.AnnouncesBean, BaseViewHolder> {

        private final SparseArray<BaseViewHolder> mCountdownVHList;
        private boolean isCancel = true;
        private long time;

        public void setTime(long time) {
            this.time = time;
        }

        public RvAdapter() {
            super(R.layout.item_rv_recent);
            mCountdownVHList = new SparseArray<>();
        }

        @Override
        public void onViewRecycled(BaseViewHolder holder) {
            super.onViewRecycled(holder);
            int pos = holder.getLayoutPosition();
            RecentBean.DataBean.AnnouncesBean curAnnounceGoodsInfo = getItem(pos);
            if (null != curAnnounceGoodsInfo) {
                mCountdownVHList.remove(curAnnounceGoodsInfo.getId());
            }
        }

        @Override
        protected void convert(BaseViewHolder helper, final RecentBean.DataBean.AnnouncesBean item) {
            try {
                ImageView ivUser = helper.getView(R.id.iv_people);
                ivUser.setVisibility(item.getState() == 1 ? View.GONE : View.VISIBLE);
                if (item.getUser() != null) {
                    ImageLoader.cicleImg(item.getUser().getHeadImg(), ivUser);
                    helper.setText(R.id.tv_people_name, item.getUser().getNiceName());
                }


                ImageView iv = helper.getView(R.id.iv_img);
                ImageLoader.roundImg(item.getImg(), iv);

                helper.setText(R.id.tv_product_name, item.getProName())
                        .setText(R.id.tv_num, item.getJoinNum() + "");
                helper.setText(R.id.show_wait, "A值：" + item.getDateNO() + "  B值：等待最新数据中");


                final CountdownView countdownView = helper.getView(R.id.cv_countdownView);
                LinearLayout llNot = helper.getView(R.id.ll_not);
                LinearLayout llYet = helper.getView(R.id.ll_yet);
                TextView waiting = helper.getView(R.id.waiting);

                //状态 1未揭晓 2：已揭晓 3:开奖中...
                if (item.getState() == 2) {
                    llYet.setVisibility(View.VISIBLE);
                    llNot.setVisibility(View.GONE);
                    helper.setText(R.id.tv_time, TimeUtil.times(item.getAnnounceTime() + ""));
                    countdownView.stop();
                } else {
                    ivUser.setVisibility(View.GONE);
                    llYet.setVisibility(View.GONE);
                    llNot.setVisibility(View.VISIBLE);

                    // 处理倒计时
                    // 倒计时结束的时候 刷新列表

                    long time1 = CurrentTimeUtils.getCurrentTime(item.getKjTime()) - CurrentTimeUtils.getCurrentTime(time);
                    if (new Long(time1) <= new Long(0l)) {
                        countdownView.setVisibility(View.GONE);
                        waiting.setVisibility(View.VISIBLE);
                    } else {
                        countdownView.setVisibility(View.VISIBLE);
                        waiting.setVisibility(View.GONE);

                        countdownView.start(time1 * 1000);
                        countdownView.setOnCountdownEndListener(new CountdownView.OnCountdownEndListener() {
                            @Override
                            public void onEnd(CountdownView cv) {
                                countdownView.stop();
                                onReLoadListListener.listRefesh();
                            }
                        });
                    }
                }
                //状态 1未揭晓 2：已揭晓
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //状态 1未揭晓 2：已揭晓 3 开奖中
                        ARouter.getInstance().build("/recent/detail")
                                .withString("goodsNo", item.getDateNO() + "")
                                .withLong("kjTime", item.getKjTime())
                                .withString("id", item.getProId() + "").navigation();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public interface OnOpenJpListener {
            void openId(int id);
        }

        private OnOpenJpListener openJpListener;

        public void setOpenJpListener(OnOpenJpListener openJpListener) {
            this.openJpListener = openJpListener;
        }

        private OnReLoadListListener onReLoadListListener;

        public interface OnReLoadListListener {
            void listRefesh();
        }

        public void setReFreshListListener(OnReLoadListListener onReLoadListListener) {
            this.onReLoadListListener = onReLoadListListener;
        }

    }

    @Override
    public void onStop() {
        super.onStop();
    }



}