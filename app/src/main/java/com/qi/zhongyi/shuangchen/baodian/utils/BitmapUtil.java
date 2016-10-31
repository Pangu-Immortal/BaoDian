package com.qi.zhongyi.shuangchen.baodian.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * @author yanzi
 *????Bitmap??byte[] Drawable???????
 */
public class BitmapUtil {

	/**
	 * @param drawable
	 * drawable ?  Bitmap
	 */
	public static Bitmap drawableToBitmap(Drawable drawable) {
		// ? drawable ?????
		int w = drawable.getIntrinsicWidth();
		int h = drawable.getIntrinsicHeight();

		// ? drawable ????????
		Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
				: Bitmap.Config.RGB_565;
		// ??????? bitmap
		Bitmap bitmap = Bitmap.createBitmap(w, h, config);
		// ??????? bitmap ?????
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, w, h);
		// ?? drawable ?????????????
		drawable.draw(canvas);
		return bitmap;
	}

	/**
	 * @param bitmap
	 * @param roundPx
	 * ????????
	 */
	public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Bitmap output = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas canvas = new Canvas(output);
		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, w, h);
		final RectF rectF = new RectF(rect);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}
}
