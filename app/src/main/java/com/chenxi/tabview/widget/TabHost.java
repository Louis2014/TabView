package com.chenxi.tabview.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.chenxi.tabview.R;
import com.chenxi.tabview.adapter.BaseAdapter;
import com.chenxi.tabview.listener.OnTabSelectedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengxi on 17/4/26.
 */
public class TabHost {


    private Context context;
    /**
     *  布局View
     */
    private LinearLayout rootView;
    //tab集合
    private List<Tab> tabList = new ArrayList<>();
    private ViewPager contentViewPager;


    public TabHost(Context context) {
        this.context = context;
        initView();
    }

    private void initView() {
        rootView = new LinearLayout(context);
        rootView.setOrientation(LinearLayout.HORIZONTAL);
        rootView.setId(R.id.linearlayout_tab);

        RelativeLayout.LayoutParams rootViewLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootViewLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        rootView.setLayoutParams(rootViewLp);
    }

    private void addTab(Tab tab) {
        if (tab == null) return;
        tabList.add(tab);

        LinearLayout tabRootView = tab.getRootView();
        rootView.addView(tabRootView);

        addTabChangeListener(tab);
    }

    public void addTabs(BaseAdapter baseAdapter, int textSize, int textColor, int selectedTextColor, int drawablePadding, int iconWidth, int iconHeight) {
        int count = baseAdapter.getCount();
        int hasMsgIndex=baseAdapter.hasMsgIndex();
        String[] textArray = baseAdapter.getTextArray();
        int[] iconImageArray = baseAdapter.getIconImageArray();
        int[] selectedIconImageArray = baseAdapter.getSelectedIconImageArray();

        if (count == 0 || textArray == null || iconImageArray == null || selectedIconImageArray == null) return;
        if (textArray.length != count || iconImageArray.length != count || selectedIconImageArray.length != count) return;
        boolean hasMsg=false;
        for (int i = 0; i < count; i++) {
            if((i+1)==hasMsgIndex){
                hasMsg=true;
            }
            Tab tab = new Tab(context, textArray[i], textSize, textColor, selectedTextColor,drawablePadding,iconWidth,iconHeight, iconImageArray[i], selectedIconImageArray[i], i,hasMsg);
            addTab(tab);
        }
    }

    public void setContentViewPager(ViewPager contentViewPager) {
        this.contentViewPager = contentViewPager;
    }

    public LinearLayout getRootView() {
        return rootView;
    }

    private void addTabChangeListener(Tab tab) {
        tab.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                contentViewPager.setCurrentItem(tab.getIndex(), false);
            }
        });
    }

    /**
     *  改变tabHost状态
     */
    public void onChangeTabHostStatus(int index) {
        for (int i = 0, size = tabList.size(); i < size; i++) {
            Tab tab = tabList.get(i);
            tab.setTabIsSelected(index == i ? true : false);
        }
    }

    public Tab getTabForIndex(int index) {
        if (tabList.size() <= index) {
            return null;
        }
        return tabList.get(index);
    }

}
