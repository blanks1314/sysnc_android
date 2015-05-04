package com.wosai.upaydemo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.wosai.upay.OrderInfo;
import cn.wosai.upay.PayMethod;
import cn.wosai.upay.UpayCallBack;
import cn.wosai.upay.UpayResult;
import cn.wosai.upay.UpayTask;
import cn.wosai.upay.OrderInfo.PayModel;

import com.wosai.upaydemo.HomeActivity.IGetData;
import com.wosai.upaydemo.utils.CheckUtil;
import com.wosai.upaydemo.utils.DBUtil;
import com.wosai.upaydemo.utils.EnvUtil;
import com.wosai.upaydemo.utils.ViewUtils;
import com.wosai.upaydemo.widget.BaseFragment;

@SuppressLint("InflateParams")
public class WexinFragment extends BaseFragment {

	IGetData mDaGetData;

	private EditText editQrCode;// 付款码
	private Button btnPay;
	private Button btnPayNone;
	private Button btnQrCode;
	private TextView textResult;
	private Dialog mDialog;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_wexin, null, false);
		initView(view);
		return view;
	}

	private void initView(View view) {

		mDaGetData = ((HomeActivity) getActivity()).getIGetData();
		mDialog = ViewUtils.createLoadingDialog(getActivity(), "二维码获取中，请稍后...");
		textResult = (TextView) view.findViewById(R.id.tv_payResult);
		editQrCode = (EditText) view.findViewById(R.id.et_qrcode);
		btnPay = (Button) view.findViewById(R.id.btn_pay);
		btnPay.setOnClickListener(new OnClickListener() {

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
									DBUtil.getOperation(getActivity()).save(EnvUtil.parseDeal(result));
								}
							}
						});
			}
		});
		btnPayNone = (Button) view.findViewById(R.id.btn_pay_none);
		btnPayNone.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!mDaGetData.checkData()) {
					return;
				}
				if (CheckUtil.isEmpty(editQrCode)) {
					ViewUtils.showError("请输入付款二维码！", getActivity());
					return;
				}
				final OrderInfo orderInfo = preData();
				orderInfo.setModel(PayModel.NO_UI);
				orderInfo.setDynamic_id(editQrCode.getText().toString());
				UpayTask.getInstance(getActivity()).checkout(orderInfo,
						new UpayCallBack() {

							@Override
							public void onExecuteResult(UpayResult arg0) {
								// TODO Auto-generated method stub
								textResult.setText(arg0.toString());
								DBUtil.getOperation(getActivity()).save(EnvUtil.parseDeal(arg0));
							}
						});
			}
		});
		btnQrCode = (Button) view.findViewById(R.id.btn_qrcode);
		btnQrCode.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!mDaGetData.checkData()) {
					return;
				}
				mDialog.show();
				UpayTask.getInstance(getActivity()).getPayQrcode(preData(),
						new UpayCallBack() {

							@Override
							public void onExecuteResult(UpayResult arg0) {
								// TODO Auto-generated method stub
								textResult.setText(arg0.toString());
								mDialog.dismiss();
							}
						});

			}
		});
	}

	private OrderInfo preData() {
		OrderInfo orderInfo = mDaGetData.getOrderInfo();
		orderInfo.setPayMethod(PayMethod.UPAY_WEIXIN);
		return orderInfo;
	}
}
