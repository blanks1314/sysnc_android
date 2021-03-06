package com.wosai.upaydemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.wosai.upaydemo.utils.ViewUtil;

public class ModifyActivity extends Activity {

	private Button btnOK;
	private Button btnCancel;
	private EditText editInfo;
	private String info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modifyinfo);
		btnOK = (Button) findViewById(R.id.btn_ok);
		btnOK.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult();

			}
		});
		btnCancel = (Button) findViewById(R.id.btn_cancel);
		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		editInfo = (EditText) findViewById(R.id.et_content);
		info = getIntent().getStringExtra("data");
		editInfo.setHint(info);
	}

	private void setResult() {
		if (TextUtils.isEmpty(editInfo.getText().toString())) {
			ViewUtil.showError("������" + getIntent().getStringExtra("info"), this);
			return;
		}
		Intent i = new Intent();
		i.putExtra("data", editInfo.getText().toString());
		setResult(RESULT_OK, i);
		finish();
	}

}
