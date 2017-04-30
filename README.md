## TabView
>针对APP的首页出现频次比较高的界面，封装了底部导航栏切换效果的工具类，使用非常方便简洁。

#### Demo展示
[demo gif](https://raw.githubusercontent.com/Louis2014/MarkdownPhotos/master/photos/6BA383165D3660DA685F6DF45E5ADF38.jpg)

### 一、功能介绍
       1.支持滑动切换栏目。
       2.支持底部导航按钮自定义样式。
       3.支持导航按钮出现红点样式的消息提示。
       
### 二、使用方法 
  * 布局中引用

```xml
<com.chenxi.tabview.widget.TabContainerView
        android:id="@+id/tab_container"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:tabTextColor="@color/bottom_icon_up"
        app:selectedTextColor="@color/common_red"
        app:tabTextSize="12sp"
        app:drawablePadding="1dp"
        app:iconHeight="22dp"
        app:iconWidth="22dp"
        app:divideLineColor="@color/common_line_two"
        app:divideLineHeight="0.3dp"/>
```
  * ACTIVITY中引用
 
```java
 TabContainerView tabContainerView = (TabContainerView) findViewById(R.id.tab_container);
        MainViewAdapter mainViewAdapter=new MainViewAdapter(getSupportFragmentManager(),
                new Fragment[] {new TabFragment1(), new TabFragment2(),new TabFragment3(), new TabFragment4(),new TabFragment5()});
        mainViewAdapter.setHasMsgIndex(5);
        tabContainerView.setAdapter(mainViewAdapter);
        tabContainerView.setOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {

            }
        });
```
* 布局自定义属性介绍

```
tabTextSize:导航按钮字体大小
drawablePadding：导航图片与文字的间距
iconHeight： 导航图标高度
iconWidth：导航图标宽度
divideLineColor：导航栏顶部分割线颜色
divideLineHeight：导航栏顶部分割线高度
```
* 设置消息提醒（<font color=#183691>**导航按钮旁边的红色圆点**</font>）

```
setHasMsgIndex(5); //第五个导航按钮有消息提醒
```

	
