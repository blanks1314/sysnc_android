package com.wosai.upaydemo.wight;

import android.support.v4.view.ViewPager;
/**
 * viewPager相关操作接口
 * @author lei
 *
 */
public interface PageIndicator extends ViewPager.OnPageChangeListener{

	void setViewPager(ViewPager view);
	void setViewPager(ViewPager view,int initalPosition);
	void setCurrentItem(int item); 
	void setOnPageChangeListener(ViewPager.OnPageChangeListener listener);  
	void notifyDataSetChanged(); 
}
