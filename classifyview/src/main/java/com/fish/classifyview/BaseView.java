package com.fish.classifyview;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import static android.view.View.MeasureSpec.AT_MOST;

/**
 * ============================================================
 * 作 者 :    wgyscsf@163.com
 * 更新时间 ： 20180404
 * 描 述 ：整个自定义view最基础的类，在这里做一些基础重复的操作。
 * ============================================================
 */
public abstract class BaseView extends View {

    protected String TAG;

    protected Context mContext;

    //控件默认宽高。当控件的宽高设置为wrap_content时会采用该参数进行默认的设置（单位：sp）。
    //不允许用户修改，想要修改宽高，使用mWidth、mBaseHeight。
    protected final float DEF_WIDTH = 650;
    protected final float DEF_HIGHT = 400;

    //测量的控件宽高，会在onMeasure中进行测量。
    protected int mBaseWidth;
    protected int mBaseHeight;


    public BaseView(Context context) {
        this(context, null);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TAG = this.getClass().getSimpleName();
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == AT_MOST && heightSpecMode == AT_MOST) {
            setMeasuredDimension((int) DEF_WIDTH, (int) DEF_HIGHT);
        } else if (widthSpecMode == AT_MOST) {
            setMeasuredDimension((int) DEF_WIDTH, heightSpecSize);
        } else if (heightSpecMode == AT_MOST) {
            setMeasuredDimension(widthSpecSize, (int) DEF_HIGHT);
        } else {
            setMeasuredDimension(widthSpecSize, heightSpecSize);
        }
        mBaseWidth = getMeasuredWidth();
        mBaseHeight = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    /**
     * 根据颜色id获取颜色
     *
     * @param colorId
     * @return
     */
    protected int getColor(@ColorRes int colorId) {
        return ContextCompat.getColor(mContext, colorId);
    }

    protected String getString(@StringRes int stringId) {
        return getResources().getString(stringId);
    }

    protected Drawable getDrawable(@DrawableRes int drawableId) {
        return ContextCompat.getDrawable(mContext, drawableId);
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float fontScale = mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * fontScale + 0.5f);
    }

    /**
     * 测量指定画笔的文字的高度
     *
     * @param fontSize
     * @param paint
     * @return
     */
    protected float getFontHeight(float fontSize, Paint paint) {
        paint.setTextSize(fontSize);
        Paint.FontMetrics fm = paint.getFontMetrics();
        return (float) (Math.ceil(fm.descent - fm.top) + 2f);
    }


    public float getDEF_WIDTH() {
        return DEF_WIDTH;
    }

    public float getDEF_HIGHT() {
        return DEF_HIGHT;
    }

    public int getBaseWidth() {
        return mBaseWidth;
    }

    public void setBaseWidth(int baseWidth) {
        mBaseWidth = baseWidth;
    }

    public int getBaseHeight() {
        return mBaseHeight;
    }

    public void setBaseHeight(int baseHeight) {
        mBaseHeight = baseHeight;
    }
}
