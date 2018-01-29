package com.ejar.onebuy.home.fgt;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ejar.onebuy.App;
import com.ejar.onebuy.R;
import com.ejar.onebuy.RetrofitImp;
import com.ejar.onebuy.bean.home.JoinNowBean;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.disposables.Disposable;
import wang.wongxd.wquicklib.base.retrofit.ObserverOnNextListener;
import wang.wongxd.wquicklib.base.retrofit.ProgressObserver;
import wang.wongxd.wquicklib.base.retrofit.RetrofitUtil;
import wang.wongxd.wquicklib.utils.TU;


/**
 * wongxd 参考 http://blog.csdn.net/wbwjx/article/details/50507344
 */
public class JoinNowFragment extends android.support.v4.app.DialogFragment {
    public static final String TAG = "JoinNowFragment";


    private ImageView ivClose;
    private TextView tv5, tv20, tv50, tvLeftAll;
    private ImageView ivReduce;
    private ImageView ivAdd;
    private TextView tvNum;
    private TextView tv_issue_5, tv_issue_10, tv_issue_15, tv_issue_20;
    private TextView tvMoney;
    private Button btn;

    /**
     * 自动追期
     */
    private int autoIssue = 0;

    /**
     * 最大购买人次
     */
    private int maxNum;

    /**
     * 单人次价格
     */
    private double oncePrice;

    /**
     * 总价
     */
    private double totalMoney;

    //type ==3

    private TextView tv_close, tv_total_money, tv_need_pay;

    public static JoinNowFragment newInstance() {
        Bundle args = new Bundle();
        JoinNowFragment fragment = new JoinNowFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        必须在onCreate 中设置Style ,而在OnCreateView 中设置无效,因为此时对话框已经init了
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.BottomDialog);
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = initView();


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.BottomDialog);

        builder.setView(view);

        AlertDialog dialog = builder.create();

        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        //设置没有效果
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        return dialog;
    }

    @NonNull
    private View initView() {
        initData();
        View view = null;
//        if (productBean.getType() == 3) {
//            view = View.inflate(getActivity(), R.layout.fgt_join_now_type_3, null);
//            btn = (Button) view.findViewById(R.id.btn);
//            tv_close = (TextView) view.findViewById(R.id.tv_close);
//            tv_total_money = (TextView) view.findViewById(R.id.tv_total_money);
//            tv_need_pay = (TextView) view.findViewById(R.id.tv_need_pay);
//
//
//            tv_total_money.setText(productBean.getOneceAmount()+"");
//            tv_need_pay.setText(productBean.getOneceAmount()+"");
//
//            tv_close.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    getDialog().dismiss();
//                }
//            });
//
//            autoIssue = 0;
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    doJoin(1);
//                }
//            });
//
//        } else {
            view = View.inflate(getActivity(), R.layout.fgt_join_now, null);

            ivClose = (ImageView) view.findViewById(R.id.iv_close);


            ivReduce = (ImageView) view.findViewById(R.id.iv_reduce);
            ivAdd = (ImageView) view.findViewById(R.id.iv_add);
            tvNum = (TextView) view.findViewById(R.id.tv_num);


            tv5 = (TextView) view.findViewById(R.id.tv_5);
            tv20 = (TextView) view.findViewById(R.id.tv_20);
            tv50 = (TextView) view.findViewById(R.id.tv_50);
            tvLeftAll = (TextView) view.findViewById(R.id.tv_left_all);

            tv_issue_5 = (TextView) view.findViewById(R.id.tv_issue_5);
            tv_issue_10 = (TextView) view.findViewById(R.id.tv_issue_10);
            tv_issue_15 = (TextView) view.findViewById(R.id.tv_issue_15);
            tv_issue_20 = (TextView) view.findViewById(R.id.tv_issue_20);


            tvMoney = (TextView) view.findViewById(R.id.tv_money);
            btn = (Button) view.findViewById(R.id.btn);
            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getDialog().dismiss();
                }
            });


            tv5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (maxNum < 5) tvNum.setText(maxNum + "");
                    else tvNum.setText(5 + "");
                    int num = Integer.valueOf(tvNum.getText().toString().trim());

                    totalMoney = oncePrice * num;
                    tvMoney.setText(Html.fromHtml("共 <font color=\"#e66d40\">" + totalMoney + "</font>"));
                }
            });


            tv50.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (maxNum < 50) tvNum.setText(maxNum + "");
                    else tvNum.setText(50 + "");
                    int num = Integer.valueOf(tvNum.getText().toString().trim());

                    totalMoney = oncePrice * num;
                    tvMoney.setText(Html.fromHtml("共 <font color=\"#e66d40\">" + totalMoney + "</font>"));
                }
            });


            tv20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (maxNum < 20) tvNum.setText(maxNum + "");
                    else tvNum.setText(20 + "");
                    int num = Integer.valueOf(tvNum.getText().toString().trim());

                    totalMoney = oncePrice * num;
                    tvMoney.setText(Html.fromHtml("共 <font color=\"#e66d40\">" + totalMoney + "</font>"));
                }
            });


            tvLeftAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    tvNum.setText(maxNum + "");
                    int num = Integer.valueOf(tvNum.getText().toString().trim());

                    totalMoney = oncePrice * num;
                    tvMoney.setText(Html.fromHtml("共 <font color=\"#e66d40\">" + totalMoney + "</font>"));
                }
            });


            ivReduce.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.valueOf(tvNum.getText().toString().trim());
                    if (num > 1) --num;

                    tvNum.setText(num + "");
                    totalMoney = oncePrice * num;
                    tvMoney.setText(Html.fromHtml("共 <font color=\"#e66d40\">" + totalMoney + "</font>"));
                }
            });


            ivAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.valueOf(tvNum.getText().toString().trim());
                    if (num < maxNum) ++num;
                    tvNum.setText(num + "");
                    totalMoney = oncePrice * num;
                    tvMoney.setText(Html.fromHtml("共 <font color=\"#e66d40\">" + totalMoney + "</font>"));
                }
            });


            tv_issue_5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    autoIssue = 5;
                    doAutoIssueLogic();
                }
            });

            tv_issue_10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    autoIssue = 10;
                    doAutoIssueLogic();
                }
            });

            tv_issue_15.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    autoIssue = 15;
                    doAutoIssueLogic();
                }
            });
            tv_issue_20.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    autoIssue = 20;
                    doAutoIssueLogic();
                }
            });


            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int num = Integer.valueOf(tvNum.getText().toString().trim());
                    doJoin(num);
                }
            });
//        }
        return view;
    }


    private void doJoin(int num) {

        Logger.e("  " + productBean.getId() + "  " + num + "  " + autoIssue);
        RetrofitUtil.toBaseIntercept(RetrofitImp.getHomeApi().confirmJoinNow(App.token,
                productBean.getId() + "",
                num + "",
                autoIssue + ""),
                new ProgressObserver<String>(new ObserverOnNextListener<String>() {
                    @Override
                    public void onNext(String s, Disposable disposable) {

                        Logger.e(s);
                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            TU.cT(jsonObject.optString("msg"));
                            if (jsonObject.optInt("code") == 200) {
                                if (joinCallback!=null){
                                    joinCallback.doRefresh();
                                }
                                getDialog().dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        TU.cT("参与失败！ " + e.getMessage());
                        Logger.e(e.getMessage());
                    }
                }, getActivity()));


    }

    private void doAutoIssueLogic() {
        switch (autoIssue) {
            case 5:
                tv_issue_5.setTextColor(getResources().getColor(R.color.selected));
                tv_issue_10.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_15.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_20.setTextColor(getResources().getColor(R.color.gray));
                break;

            case 10:

                tv_issue_5.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_10.setTextColor(getResources().getColor(R.color.selected));
                tv_issue_15.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_20.setTextColor(getResources().getColor(R.color.gray));
                break;

            case 15:

                tv_issue_5.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_10.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_15.setTextColor(getResources().getColor(R.color.selected));
                tv_issue_20.setTextColor(getResources().getColor(R.color.gray));
                break;

            case 20:
                tv_issue_5.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_10.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_15.setTextColor(getResources().getColor(R.color.gray));
                tv_issue_20.setTextColor(getResources().getColor(R.color.selected));
                break;
        }

    }


    static JoinNowBean.DataBean.ProductBean productBean;

    private void initData() {
        maxNum = productBean.getExpectNum() - productBean.getAlreadyNum();
        oncePrice = productBean.getOneceAmount();

//           .setTitle(Html.fromHtml("<font color='#ff0000'><big>比价有误</big></font>"))


    }


    @Override
    public void onStart() {
        super.onStart();
        //全屏
        Dialog dialog = getDialog();
        if (null != dialog) {
            dialog.getWindow().setLayout(-1, -2);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private static JoinCallback joinCallback ;
    /**
     * 可以避免重复
     *
     * @param appCompatActivity
     * @return
     */
    public static JoinNowFragment showDialog(AppCompatActivity appCompatActivity, JoinNowBean.DataBean.ProductBean p,JoinCallback callback) {
        productBean = p;
        joinCallback = callback;
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        JoinNowFragment bottomDialogFragment =
                (JoinNowFragment) fragmentManager.findFragmentByTag(TAG);
        if (null == bottomDialogFragment) {
            bottomDialogFragment = newInstance();
        }

        if (!appCompatActivity.isFinishing()
                && null != bottomDialogFragment
                && !bottomDialogFragment.isAdded()) {
            fragmentManager.beginTransaction()
                    .add(bottomDialogFragment, TAG)
                    .commitAllowingStateLoss();
        }

        return bottomDialogFragment;
    }


    public interface JoinCallback{
        void doRefresh();
    }
}
