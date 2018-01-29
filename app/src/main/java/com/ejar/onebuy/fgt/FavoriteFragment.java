package com.ejar.onebuy.fgt;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.ejar.onebuy.ApiServer;
import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.favorite.FavoriteBean;
import com.ejar.onebuy.databinding.FgtFavoriteBinding;
import com.ejar.onebuy.util.DialogUtil;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
import wang.wongxd.wquicklib.utils.TU;
import wang.wongxd.wquicklib.utils.glide.GlideRoundTransform;

/**
 * Created by wongxd on 2017/8/4.
 */

public class FavoriteFragment extends BaseBindingFragment<FgtFavoriteBinding> {

    private RvAdapter adapter;

    @Override
    public int setContent() {
        return R.layout.fgt_favorite;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bindingView.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(adapter.getData().size()==0) return;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String text = bindingView.tvDelete.getText().toString();
                if (text.contains("删除")) {
                    adapter.setEdit(true);
                    bindingView.tvDelete.setText("完成");
                } else {
                    adapter.setEdit(false);
                    bindingView.tvDelete.setText("删除");
                }
            }
        });

        adapter = new RvAdapter();
        bindingView.rv.setAdapter(adapter);
        bindingView.rv.setLayoutManager(new LinearLayoutManager(getContext()));
//        bindingView.rv.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL));

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


        bindingView.srl.autoRefresh();
    }

    private int pageNo = 1, totalPage = 1;

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

        RetrofitUtil.toBaseIntercept(RetrofitImp.getFavoriteApi().showCollection(App.token, pageNo + "", "10"), new ProgressObserver<String>(new ObserverOnNextListener<String>() {
            @Override
            public void onNext(String s, Disposable disposable) {
                bindingView.srl.finishRefresh();
                addDisposable(disposable);
                Logger.e(s);
                FavoriteBean favoriteBean = new Gson().fromJson(s, FavoriteBean.class);
                if (favoriteBean.getCode() != 200) {
                    TU.cT(favoriteBean.getMsg());
                    if (isLoadMore) adapter.loadMoreFail();
                    return;
                }

                FavoriteBean.DataBean data = favoriteBean.getData();
                totalPage = data.getTotalPage();

                if (isLoadMore) {
                    adapter.addData(data.getSlist());
                    adapter.loadMoreComplete();
                } else {
                    adapter.setNewData(data.getSlist());
                }

                if (data.getSlist() != null && data.getSlist().size() != 0) ++pageNo;

                bindingView.tvAll.setText("全部宝贝 ( " + adapter.getData().size() + " )");
            }

            @Override
            public void onError(Throwable e) {
                bindingView.srl.finishRefresh();
                if (isLoadMore) {
                    adapter.loadMoreFail();
                }
            }
        }, getActivity(),false));
    }


    @Override
    public void onStart() {
        super.onStart();
        RxBus.getDefault().toObservable(RxEventCodeType.FAVORITE_LIST_DELETE, FavoriteBean.DataBean.SlistBean.class).subscribe(new Observer<FavoriteBean.DataBean.SlistBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                addDisposable(d);
            }

            @Override
            public void onNext(final FavoriteBean.DataBean.SlistBean slistBean) {
                if (isFavoriteRunning) return;
                DialogUtil.showDialog((AppCompatActivity) getActivity(), "确定删除收藏吗？", "", "确定", "取消", new DialogUtil.DialogListener() {
                    @Override
                    public void left(Dialog dialog) {
                        deleteFavorite(slistBean);
                        dialog.dismiss();
                    }

                    @Override
                    public void right(Dialog dialog) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    private boolean isFavoriteRunning = false;

    /**
     * 取消收藏
     */
    public void deleteFavorite(final FavoriteBean.DataBean.SlistBean item) {

        RetrofitImp.getFavoriteApi().cancelCollection(App.token, item.getId() + "")
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
                                int index = adapter.getData().indexOf(item);
                                adapter.getData().remove(index);
                                adapter.notifyItemRemoved(index);
                                adapter.setEdit(true);
                                bindingView.tvAll.setText("全部宝贝 ( " + adapter.getData().size() + " )");
                                if (adapter.getData().size() == 0) {
                                    bindingView.tvDelete.setText("删除");
                                }
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

    private static class RvAdapter extends BaseQuickAdapter<FavoriteBean.DataBean.SlistBean, BaseViewHolder> {
        public RvAdapter() {
            super(R.layout.item_rv_favorite);
        }

        public void setEdit(boolean isEdit) {

            for (FavoriteBean.DataBean.SlistBean item : getData()) {
                item.setEdit(isEdit);
            }
            notifyItemRangeChanged(0, getItemCount(), getItemCount());
        }

        @Override
        protected void convert(final BaseViewHolder helper, final FavoriteBean.DataBean.SlistBean item) {
            try {
                ImageView iv = helper.getView(R.id.iv_img);
                String[] imgs = item.getPicture().split(",");
                Glide.with(iv.getContext()).load(ApiServer.IMG_HOST + imgs[0]).placeholder(R.drawable.placeholder)
                        .transform(new GlideRoundTransform(iv.getContext())).into(iv);

                double progress = ((double) item.getAlreadyNum()) / ((double) item.getExpectNum()) * 100.00;

                ProgressBar pb = helper.getView(R.id.pb);
                pb.setProgress((int) progress);
                helper.setText(R.id.tv_name, item.getName())
                        .setText(R.id.tv_issue, "期号: " + item.getNowNo())
                        .setText(R.id.tv_need_people, "总需 " + item.getExpectNum() + " 人次")
                        .setText(R.id.tv_left, String.valueOf(item.getExpectNum() - item.getAlreadyNum()));

                helper.getView(R.id.btn_join).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ARouter.getInstance().build("/base/gooddetail").withString("id", item.getId() + "").navigation();
                    }
                });

                helper.setVisible(R.id.tv_delete, item.isEdit());

                TextView tvDelete = helper.getView(R.id.tv_delete);

                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RxBus.getDefault().post(RxEventCodeType.FAVORITE_LIST_DELETE, item);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onBindViewHolder(BaseViewHolder holder, int position, List<Object> payloads) {
            onBindViewHolder(holder, position);
        }
    }
}
