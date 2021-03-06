package com.wosai.upaydemo.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wosai.upaydemo.R;

public class TipDialog extends Dialog {

	Context ctx;
	TextView mQuery;
	TextView mRevoke;
	View view;
	int y;

	public TipDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.dialog_tip, null);
		mQuery = (TextView) view.findViewById(R.id.tvQuery);
		mRevoke = (TextView) view.findViewById(R.id.tvRevoke);
		this.ctx = context;
	}

	public TipDialog(Context context, int theme) {
		super(context, theme);
		// TODO Auto-generated constructor stub
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.dialog_tip, null);
		mQuery = (TextView) view.findViewById(R.id.tvQuery);
		mRevoke = (TextView) view.findViewById(R.id.tvRevoke);
		this.ctx = context;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		init();
		this.setContentView(view);
	}

	public void setRevokeClick(android.view.View.OnClickListener c) {
		mRevoke.setOnClickListener(c);
	}

	public void setQuerClick(android.view.View.OnClickListener c) {
		mQuery.setOnClickListener(c);
	}

	public void setY(int y) {
		this.y = y;
	}

	private void init() {
		Window windows = this.getWindow();
		WindowManager.LayoutParams wl = windows.getAttributes();
		windows.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
		wl.y = y-124;
		windows.setAttributes(wl);
		this.setCanceledOnTouchOutside(true);
	}

}
