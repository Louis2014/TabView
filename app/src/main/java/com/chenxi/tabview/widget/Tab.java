package com.chenxi.tabview.widget;

import android.content.Context;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chenxi.tabview.R;
import com.chenxi.tabview.listener.OnTabSelectedListener;

/**
 * Created by chengxi on 17/4/26.
 */
public class Tab {

    private Context context;
    private int index;
    //是否被选中
    private boolean isSelected;

    /**
     *  文本信息
     */
    private String text;
    private int textColor;
    private int selectedTextColor;
    private int textSize;
    private int drawablePadding;


    /**
     *  icon信息
     */
    private int iconImage;
    private int selectedIconImage;
    private int iconHeight;
    private int iconWidth;

    /**
     *  Tab布局信息
     */
    private RelativeLayout childView;
    private LinearLayout rootView;
    private ImageView iconImageView;
    private TextView textTextView;
    private boolean hasMsg;

    /**
     *  tab选中监听
     */
    private OnTabSelectedListener onTabSelectedListener;

    public Tab(Context context, String text, int textSize, int textColor, int selectedTextColor, int drawablePadding, int iconWidth, int iconHeight, int iconImage, int selectedIconImage, int index, boolean hasMsg) {
        this.context = context;
        this.text = text;
        this.textSize = textSize;
        this.textColor = textColor;
        this.selectedTextColor = selectedTextColor;
        this.drawablePadding=drawablePadding;

        this.iconImage = iconImage;
        this.selectedIconImage = selectedIconImage;
        this.index = index;
        this.iconHeight=iconHeight;
        this.iconWidth=iconWidth;
        this.hasMsg=hasMsg;

        init();
    }

    private void init() {
        initView();

        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tabSelected();
            }
        });
    }

    private void initView() {
        rootView = new LinearLayout(context);
        childView=new RelativeLayout(context);
        LinearLayout.LayoutParams rootViewLp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rootViewLp.weight = 1;
        rootView.setOrientation(LinearLayout.VERTICAL);
        rootView.setPadding(0,20,0,20);
        rootView.setLayoutParams(rootViewLp);
        textTextView = new TextView(context);
        iconImageView = new ImageView(context);

        /**
         *  icon view
         */
        iconImageView.setImageResource(iconImage);
        RelativeLayout.LayoutParams iconParam=new RelativeLayout.LayoutParams(iconWidth==0? ViewGroup.LayoutParams.WRAP_CONTENT:iconWidth,iconHeight==0? ViewGroup.LayoutParams.WRAP_CONTENT:iconHeight);
        iconParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        iconImageView.setLayoutParams(iconParam);
        iconImageView.setId(index+1);
        childView.addView(iconImageView);

        /**
         *  text view
         */
        textTextView.setText(text);
        textTextView.setTextColor(textColor);
        textTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        textTextView.setPadding(0,drawablePadding,0,0);
        RelativeLayout.LayoutParams txParam=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        txParam.addRule(RelativeLayout.BELOW,childView.getChildAt(0).getId());
        txParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        textTextView.setLayoutParams(txParam);
        childView.addView(textTextView);


        if(hasMsg){
            ImageView circleView=new ImageView(context);
            RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(30,30);
            param.addRule(RelativeLayout.RIGHT_OF,iconImageView.getId());
            circleView.setBackgroundResource(R.drawable.common_red_round);
            circleView.setLayoutParams(param);
            childView.addView(circleView);
        }
        RelativeLayout.LayoutParams childParam=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        childView.setLayoutParams(childParam);
        rootView.addView(childView);

    }

    /**
     * 选中Tab
     */
    private void tabSelected() {
        if (onTabSelectedListener != null) onTabSelectedListener.onTabSelected(this);
    }

    /**
     * 得到rootView
     */
    public LinearLayout getRootView() {
        return rootView;
    }

    public int getIndex() {
        return index;
    }

    public String getText() {
        return text;
    }

    public void setTabIsSelected(boolean isSelected) {
        if (this.isSelected == isSelected) return;

        iconImageView.setImageResource(isSelected ? selectedIconImage : iconImage);
        textTextView.setTextColor(isSelected ? selectedTextColor : textColor);
        this.isSelected = isSelected;
    }

    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }

}
