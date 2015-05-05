package com.wosai.upaydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import cn.wosai.upay.OrderInfo;
import cn.wosai.upay.UpayException;
import cn.wosai.upay.UpayTask;

import com.wosai.upaydemo.HomeActivity.IGetData;
import com.wosai.upaydemo.utils.CheckUtil;
import com.wosai.upaydemo.utils.ViewUtil;
import com.wosai.upaydemo.widget.BaseFragment;

public class SettingFragment extends BaseFragment implements OnClickListener,
		IGetData {

	public static final int REQUEST_CODE1 = 0x5001;
	public static final int REQUEST_CODE2 = 0x5002;
	public static final int REQUEST_CODE3 = 0x5003;

	private Spinner spCurrType;
	private TextView textStoreId;
	private TextView textAppId;
	private TextView textAppKey;
	private EditText editOrderId;
	private EditText editAmount;
	private EditText editSubject;
	private EditText editOperator;
	private EditText editRemark;
	private Button btnCheck;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View view = inflater.inflate(R.layout.fragment_setting, null, false);
		initView(view);
		/**
		 * SDK初始化
		 */
		UpayTask.getInstance(getActivity())
				.initUpay(textStoreId.getText().toString(),
						textAppId.getText().toString(),
						textAppKey.getText().toString());
		return view;
	}

	private void initView(View v) {
		spCurrType = (Spinner) v.findViewById(R.id.spn_currType);
		spCurrType.setAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.simple_spinner_item, getResources().getStringArray(
						R.array.currTypes)));
		textAppId = (TextView) v.findViewById(R.id.tv_appId);
		textAppKey = (TextView) v.findViewById(R.id.tv_appKey);
		textStoreId = (TextView) v.findViewById(R.id.tv_storeId);
		editOrderId = (EditText) v.findViewById(R.id.et_orderId);
		editAmount = (EditText) v.findViewById(R.id.et_amount);
		editOperator = (EditText) v.findViewById(R.id.et_operator);
		editRemark = (EditText) v.findViewById(R.id.et_remark);
		editSubject = (EditText) v.findViewById(R.id.et_subject);
		btnCheck = (Button) v.findViewById(R.id.btn_check);
		v.findViewById(R.id.iv_1).setOnClickListener(this);
		v.findViewById(R.id.iv_2).setOnClickListener(this);
		v.findViewById(R.id.iv_3).setOnClickListener(this);
		btnCheck.setOnClickListener(this);
		btnCheck.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int key = v.getId();
		Intent i = new Intent(getActivity(), ModifyActivity.class);
		switch (key) {
		case R.id.iv_2:
			i.putExtra("data", textAppId.getText().toString());
			startActivityForResult(i, REQUEST_CODE1);

			break;
		case R.id.iv_3:
			i.putExtra("data", textAppKey.getText().toString());
			startActivityForResult(i, REQUEST_CODE2);

			break;
		case R.id.iv_1:
			i.putExtra("data", textStoreId.getText().toString());
			startActivityForResult(i, REQUEST_CODE3);
			break;

		case R.id.btn_check:
			UpayTask.getInstance(getActivity()).initUpay(
					textStoreId.getText().toString(),
					textAppId.getText().toString(),
					textAppKey.getText().toString());
			break;
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == REQUEST_CODE1) {
				textAppId.setText(data.getStringExtra("data"));
			} else if (requestCode == REQUEST_CODE2) {
				textAppKey.setText(data.getStringExtra("data"));
			} else if (requestCode == REQUEST_CODE3) {
				textStoreId.setText(data.getStringExtra("data"));
			}
		}
	}

	@Override
	public OrderInfo getOrderInfo() {
		// TODO Auto-generated method stub
		OrderInfo order = new OrderInfo();
		try {
			order.setMerchantId(textStoreId.getText().toString());

			order.setAppId(textAppId.getText().toString());
			order.setAppKey(textAppKey.getText().toString());
			order.setAmount(editAmount.getText().toString().isEmpty() ? 0L
					: Long.parseLong(editAmount.getText().toString()));
			order.setOrderId(editOrderId.getText().toString().isEmpty() ? System
					.currentTimeMillis() + ""
					: editOrderId.getText().toString());
			order.setOperator(editOperator.getText().toString());
			order.setCurType(spCurrType.getSelectedItemPosition() == 0 ? "156"
					: "156");
			order.setRemark(editRemark.getText().toString());
			order.setSubject(editSubject.getText().toString());
			order.setNotifyUrl("http://www.google.com");
		} catch (UpayException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Toast.makeText(getActivity(), "设置OrderInfo参数失败！",
					Toast.LENGTH_SHORT).show();
		}
		return order;
	}

	@Override
	public boolean checkData() {
		// TODO Auto-generated method stub
		if (CheckUtil.isEmpty(textAppId)) {
			ViewUtil.showError("AppId不能为空！", getActivity());
			return false;
		}
		if (CheckUtil.isEmpty(textAppKey)) {
			ViewUtil.showError("AppKey不能为空！", getActivity());
			return false;
		}
		if (CheckUtil.isEmpty(textStoreId)) {
			ViewUtil.showError("StoreId不能为空！", getActivity());
			return false;
		}
		if (CheckUtil.isEmpty(editAmount)) {
			ViewUtil.showError("请输入交易金额！", getActivity());
			return false;
		}
		if (CheckUtil.isEmpty(editSubject)) {
			ViewUtil.showError("请输入商品名称！", getActivity());
			return false;
		}
		return true;
	}

}
