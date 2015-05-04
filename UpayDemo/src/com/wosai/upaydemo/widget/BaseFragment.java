package com.wosai.upaydemo.widget;

import android.support.v4.app.Fragment;
/**
 * @author lei
 *
 */
public abstract class BaseFragment extends Fragment {
	private String title;
	private int iconId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIconId() {
		return iconId;
	}

	public void setIconId(int iconId) {
		this.iconId = iconId;
	}
	
}
