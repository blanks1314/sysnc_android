package com.wosai.upaydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wosai.upaydemo.wight.BaseFragment;

public class LakalaFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_lakala, null, false);
		initView(view);
		return view;
	}

	private void initView(View v) {

	}

}
