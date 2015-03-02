package com.football2015schedulematchups.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import android.util.Log;

public class SaveScreenshot {

	public static final String SCREENSHOT_ = "/screenshot.png";
	public String SCREENSHOT_FOLDER;
	private List<Bitmap> images;

	public SaveScreenshot(Context context) {

		SCREENSHOT_FOLDER = "/" + getApplicationName(context) + "/";
		setImages(new ArrayList<Bitmap>());
	}

	public static String getApplicationName(Context context) {
		int stringId = context.getApplicationInfo().labelRes;
		return context.getString(stringId);
	}

	public void saveBitmap(Bitmap bitmap) {

		File imagePath = new File(Environment.getExternalStorageDirectory()
				+ SCREENSHOT_);

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(imagePath);
			bitmap.compress(CompressFormat.JPEG, 100, fos);
			getImages().add(bitmap);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			Log.e("GREC", e.getMessage(), e);
		} catch (IOException e) {
			Log.e("GREC", e.getMessage(), e);
		}
	}

	public String getUriImage() {
		File file = new File(Environment.getExternalStorageDirectory()
				+ SCREENSHOT_);
		return file.getAbsolutePath();
	}

	/**
	 * @return the images
	 */
	public List<Bitmap> getImages() {
		return images;
	}

	/**
	 * @param images
	 *            the images to set
	 */
	public void setImages(List<Bitmap> images) {
		this.images = images;
	}

}
