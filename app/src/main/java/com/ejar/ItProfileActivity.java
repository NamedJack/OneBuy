package com.ejar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ejar.onebuy.LuckyRecordFragment;
import com.ejar.onebuy.R;
import com.ejar.onebuy.ShareRecordFragemt;
import com.ejar.onebuy.databinding.AtyItProfileBinding;
import com.ejar.onebuy.me.fgt.JoinRecordFragment;
import com.ejar.onebuy.util.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import wang.wongxd.wquicklib.base.ui.BaseHeaderBindingActivity;


/**
 * Created by wongxd on 2017/8/28.
 * Ta的个人中心
 */
@Route(path = "/base/itProfile")
public class ItProfileActivity extends BaseHeaderBindingActivity<AtyItProfileBinding> {

    @Autowired
    String userId;

    @Autowired
    String userName;

    @Autowired
    String userHead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_it_profile);
        ARouter.getInstance().inject(this);

        setToolBarLeftText("往期揭晓");
        setToolbarTitle("TA的个人中心");

        bindingView.tvName.setText(userName);
        bindingView.tvId.setText("ID: " + userId);
        ImageLoader.cicleImg(userHead, bindingView.iv);

        List<TabBean> list = new ArrayList<>();

        list.add(new TabBean("活动记录", JoinRecordFragment.newInstance(userId)));
        list.add(new TabBean("幸运记录", LuckyRecordFragment.newInstance(userId)));
        list.add(new TabBean("晒单记录", ShareRecordFragemt.newInstance(userId)));

        bindingView.vp.setAdapter(new VpAdapter(getSupportFragmentManager(), list));
        bindingView.tab.setupWithViewPager(bindingView.vp);
        bindingView.vp.setOffscreenPageLimit(3);


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
