package com.wosai.upaydemo.utils;

import java.util.ArrayList;
import java.util.List;

import com.wosai.upaydemo.bean.DealInfo;

import cn.wosai.upay.OrderInfo;
import cn.wosai.upay.UpayResult;

public class EnvUtil {

	public static DealInfo parseDeal(UpayResult result) {
		DealInfo info = new DealInfo();
		info.setOrderId(result.getOrderId());
		info.setAcount(CheckUtil.isEmpty(result.getAccount()) ? "" : result
				.getAccount());
		info.setAmount(result.getAmount());
		info.setbNo(CheckUtil.isEmpty(result.getBatchNo()) ? "" : result
				.getBatchNo());
		info.setcNo(CheckUtil.isEmpty(result.getVoucherNo()) ? "" : result
				.getVoucherNo());
		info.setPayMethod(result.getPayMethod());
		info.setState(result.getState());
		info.setTime(result.getTime());
		return info;

	}

	public static List<OrderInfo> parseOrderInfo(DealInfo... infos) {
		List<OrderInfo> oInfos = new ArrayList<OrderInfo>();
		for (DealInfo info : infos) {
			OrderInfo o = new OrderInfo();
			o.setAmount(info.getAmount());
			o.setBatchNo(info.getbNo());
			o.setVoucherNo(info.getcNo());
			o.setOrderId(info.getOrderId());
			o.setTime(info.getTime());
			o.setPayMethod(info.getPayMethod());
			oInfos.add(o);
		}
		return oInfos;

	}
}
