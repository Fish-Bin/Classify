package com.fish.classifyview.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

/**
 * Created by QHT on 2017-04-11.
 */
public class ResourceUtil {

    /**
     * dip转化成px
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转化成dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * px转化成sp
     */
    public static int px2sp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * sp转化成px
     */
    public static int sp2px(Context context, float spValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (spValue * scale + 0.5f);
    }

    /**
     * make a drawable to a bitmap
     *
     * @param drawable drawable you want convert
     * @return converted bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable, float width, float hight) {
        Bitmap bitmap = null;
        float scaleWidth = width > 0 ? width : drawable.getIntrinsicWidth();
        float scaleHeight = hight > 0 ? hight : drawable.getIntrinsicHeight();
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            bitmap = bitmapDrawable.getBitmap();
            if (bitmap != null && bitmap.getHeight() > 0) {
                Matrix matrix = new Matrix();
                matrix.postScale(scaleWidth, scaleHeight);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                return bitmap;
            }
        }
        bitmap = Bitmap.createBitmap((int) scaleWidth, (int) scaleHeight, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}
