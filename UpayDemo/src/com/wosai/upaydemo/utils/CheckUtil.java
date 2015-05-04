package com.wosai.upaydemo.utils;

import android.widget.TextView;

/**
 * The Util Class for check the String or the widget is
 * empty.(TextView,EditTextView)
 * 
 * @author lei
 * 
 */
public class CheckUtil {

	/**
	 * Check The String[] is empty.
	 * 
	 * @param strs
	 * @return
	 */
	public static boolean isEmpty(String... strs) {

		for (String str : strs) {
			if (str != null && str.isEmpty()) {
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * Check The String is empty.
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {

		if (str != null && str.isEmpty()) {
			return true;
		} else
			return false;

	}

	/**
	 * Check The TextView[] is empty.
	 * 
	 * @param views
	 * @return
	 */

	public static boolean isEmpty(TextView... views) {
		for (TextView view : views) {
			if (view != null && view.getText().toString().isEmpty()) {
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * Check The TextView is empty.
	 * 
	 * @param view
	 * @return
	 */

	public static boolean isEmpty(TextView view) {
		if (view.getText().toString().isEmpty()) {
			return true;
		} else
			return false;
	}
}
