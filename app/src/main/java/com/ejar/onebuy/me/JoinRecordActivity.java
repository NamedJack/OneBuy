package com.ejar.onebuy.me;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.R;
import com.ejar.onebuy.databinding.AtyJoinRecordBinding;
import com.ejar.onebuy.me.fgt.HaveBeenFragment;
import com.ejar.onebuy.me.fgt.HaveInHandFragment;
import com.ejar.onebuy.me.fgt.JoinRecordFragment;

import java.util.ArrayList;
import java.util.List;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;


/**
 * Created by wongxd on 2017/8/9.
 * 推广记录
 */
@Route(path = "/me/joinrecord")
public class JoinRecordActivity extends BaseHeaderBindingActivity<AtyJoinRecordBinding> {

    @Autowired
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_join_record);
        ARouter.getInstance().inject(this);

        setToolBarLeftText("个人中心");
        setToolbarTitle("推广记录");

        List<TabBean> list = new ArrayList<>();

        list.add(new TabBean("全部", new  JoinRecordFragment()));
        list.add(new TabBean("进行中", new HaveInHandFragment()));
        list.add(new TabBean("已揭晓", new HaveBeenFragment()));

        bindingView.vp.setAdapter(new VpAdapter(getSupportFragmentManager(), list));
        bindingView.tab.setupWithViewPager(bindingView.vp);
        bindingView.vp.setOffscreenPageLimit(3);
        if (type != 0) {
            bindingView.vp.setCurrentItem(type);
        }

    }

    private static class VpAdapter extends FragmentStatePagerAdapter {
        private List<TabBean> mList;

        public VpAdapter(FragmentManager fm, List<TabBean> list) {
            super(fm);
            mList = list;
        }

        @Override
        public Fragment getItem(int position) {
            return mList.get(position).getFgt();
        }

        @Override
        public int getCount() {
            return mList == null ? 0 : mList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position).getTitle();
        }
    }

    private static class TabBean {
        private String title;
        private Fragment fgt;

        public TabBean(String title, Fragment fgt) {
            this.title = title;
            this.fgt = fgt;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Fragment getFgt() {
            return fgt;
        }

        public void setFgt(Fragment fgt) {
            this.fgt = fgt;
        }
    }
}
