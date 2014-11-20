package com.hdlink.online.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.http.util.EncodingUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.hdlink.online.application.MyApplication;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import android.util.Log;

/**
 * 
 * @author tuwei
 * 
 */

public class SharedPreferenceHelper {
	private static final String TAG = SharedPreferenceHelper.class.getSimpleName();

	public static final String PREFER_CUSTOMER = "customerszt";
	public static final String PREFER_CAR_TYPE = "cartype";
	public static final String PREFER_ORDER_LIST = "orderlist";
	public static final String PREFER_INVOICE = "invoice";
	public static final String PREFER_CONFIG = "config";

	private static Application mApp = MyApplication.getInstance();

	/**
	 * @param name
	 *            要清理的preference
	 * @param key
	 *            要清理的item, null表示全部清除
	 */
	public static void clear(String name, String key) {
		SharedPreferences prefer = mApp.getSharedPreferences(name,
				Application.MODE_WORLD_WRITEABLE);

		Editor editor = prefer.edit();
		if (key == null)
			editor.clear();
		else
			editor.remove(key);
		editor.commit();
	}

	/**
	 * 
	 * @param name
	 *            要读取的preference
	 * @param key
	 * @return
	 */
	public static JSONObject readJson(String name, String key) {
		if (mApp == null)
			Log.e(TAG, "mApp is null");

		SharedPreferences prefer = mApp.getSharedPreferences(name,
				Application.MODE_WORLD_READABLE);
		String jsonStr = prefer.getString(key, null);

		if (jsonStr == null) {
			return null;
		} else {
			try {
				return new JSONObject(jsonStr);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @param name
	 *            读取的集合
	 * @param key
	 * @return
	 */
	public static String readList(String name, String key) {
		if (mApp == null)
			Log.e(TAG, "mApp is null");

		SharedPreferences prefer = mApp.getSharedPreferences(name,
				Application.MODE_WORLD_READABLE);
		String jsonStr = prefer.getString(key, null);

		return jsonStr;

	}

	/**
	 * 
	 * @param name
	 *            要写入的preference
	 * @param key
	 * @param data
	 *            数据
	 */
	public static void writeJson(String name, String key, JSONObject data) {
		if (mApp == null)
			Log.e(TAG, "mApp is null");

		SharedPreferences prefer = mApp.getSharedPreferences(name,
				Application.MODE_WORLD_WRITEABLE);
		Editor editor = prefer.edit();
		editor.putString(key, data.toString());
		System.out.println("data.toString()======>" + data.toString());
		editor.commit();
	}

	/**
	 * 
	 * @param name
	 *            写入的集合
	 * @param key
	 * @param data
	 */
	public static void writeJsonList(String name, String key, String data) {

		if (mApp == null)
			Log.e(TAG, "mApp is null");

		SharedPreferences prefer = mApp.getSharedPreferences(name,
				Application.MODE_WORLD_WRITEABLE);
		Editor editor = prefer.edit();
		editor.putString(key, data);
		System.out.println("data.toString()======>" + data.toString());
		editor.commit();
	}

	/**
	 * 
	 * @param name
	 *            要读取的preference
	 * @param key
	 * 
	 *            清除json格式数据
	 */
	public static void clearJson(String name, String key) {
		if (mApp == null)
			Log.e(TAG, "mApp is null");

		SharedPreferences prefer = mApp.getSharedPreferences(name,
				Application.MODE_WORLD_WRITEABLE);
		Editor editor = prefer.edit();
		editor.putString(key, null);
		editor.commit();
	}

	/**
	 * 写入手机SD卡文件数据
	 */
	public void writeDataToSD() {
		try {
			
			File file = new File(Environment.getExternalStorageDirectory(),
					"test.txt");

			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {

				FileOutputStream fos = new FileOutputStream(file);

				String message = "天气不是很好";

				byte[] buffer = message.getBytes();

				fos.write(buffer);

				fos.close();
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * 读取手机SD卡文件数据
	 */
	public static String readDataFromSD() {
		String res = null;
		try {

			File file = new File(Environment.getExternalStorageDirectory(),
					"test.txt");

			FileInputStream fis = new FileInputStream(
					file);
			byte[] buffer = new byte[fis.available()];

			fis.read(buffer);
			fis.close();
			res = EncodingUtils.getString(buffer, "UTF-8");

			
		} catch (Exception ex) {
			System.out.println("读取文件失败");
			res = null;
		}
		
		return res;
	}

}
