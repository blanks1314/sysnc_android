package com.wosai.upaydemo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.wosai.upay.OrderInfo;
import cn.wosai.upay.PayMethod;
import cn.wosai.upay.UpayCallBack;
import cn.wosai.upay.UpayResult;
import cn.wosai.upay.UpayTask;

import com.wosai.upaydemo.HomeActivity.IGetData;
import com.wosai.upaydemo.utils.DBUtil;
import com.wosai.upaydemo.utils.EnvUtil;
import com.wosai.upaydemo.widget.BaseFragment;

public class LakalaFragment extends BaseFragment {

	IGetData mDaGetData;

	private Button btnPay;
	private TextView textResult;
	private Button btnChoose;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_lakala, null, false);
		initView(view);
		return view;
	}

	private void initView(View v) {

		mDaGetData = ((HomeActivity) getActivity()).getIGetData();
		textResult = (TextView) v.findViewById(R.id.tv_payResult);
		btnPay = (Button) v.findViewById(R.id.btn_pay);
		btnChoose = (Button) v.findViewById(R.id.btn_pay_choose);
		btnChoose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!mDaGetData.checkData()) {
					return;
				}
				UpayTask.getInstance(getActivity()).checkout(preData(),
						new UpayCallBack() {

							@Override
							public void onExecuteResult(UpayResult result) {
								// TODO Auto-generated method stub
								if (result != null) {
									textResult.setText(result.toString());
									if (result != null
											&& result.getState() == 1) {
										DBUtil.getOperation(getActivity())
												.save(EnvUtil.parseDeal(result,
														"��֧��"));
									}
								}
							}
						});

			}
		});
		btnPay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!mDaGetData.checkData()) {
					return;
				}
				UpayTask.getInstance(getActivity()).checkout(
						mDaGetData.getOrderInfo(), new UpayCallBack() {

							@Override
							public void onExecuteResult(UpayResult result) {
								// TODO Auto-generated method stub
								if (result != null) {
									textResult.setText(result.toString());
									if (result != null
											&& result.getState() == 1) {
										DBUtil.getOperation(getActivity())
												.save(EnvUtil.parseDeal(result,
														"��֧��"));
									}
								}
							}
						});
			}
		});
	}

	private OrderInfo preData() {
		OrderInfo orderInfo = mDaGetData.getOrderInfo();
		orderInfo.setPayMethod(PayMethod.UPAY_LAKALA);
		return orderInfo;
	}

}
