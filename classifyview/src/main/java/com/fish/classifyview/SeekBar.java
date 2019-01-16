package com.fish.classifyview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.fish.classifyview.Util.ResourceUtil;

public class SeekBar extends BaseView {

    //背景相关
    private Paint mBackgroudPaint;
    private RectF mBackgroudRectF;

    // block四个顶点
    private float[] ints = new float[4];
    private Paint mBlockPaint;

    //滑动监听
    private OnChangeListener mOnChangeListener;

    // 方块宽、高
    private float rectWidth = 5f, rectHeight = 10f, lineHeight = 2f;
    // 线与方块间隔
    private float dotWidth = 2f;
    // 每段线段得宽
    private float lineWidth = 0f;
    // 最左边方块和最右边方块得margin
    private float rectPadding = 0f;
    // 两个方块的间隔长度
    private float perRectWidth = 0f;
    // 两条线的间隔
    private float perLineWidth = 0f;
    private float blockTop = 0f;
    private float rectTop = 0f;
    private float lineTop = 0f;
    private float progressLine = 0f;
    private Drawable blockDrawable;
    private float progress = 0f;
    private boolean isInit = false;

    //上一次滑动到的地方
    private float lastX = 0f, lastY = 0f;

    // 1-买 绿色  2-卖 红色
    private int type = 1;


    public SeekBar(Context context) {
        this(context, null);
    }

    public SeekBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }


    public void setType(int type) {
        if (this.type != type) {
            this.type = type;

            //图标切换
            blockDrawable = getDrawable(type == 1 ? R.drawable.layer_seekbar_buy : R.drawable.layer_seekbar_sell);

            progress = 0;
            invalidate();
        }
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    /**
     * 设置监听
     *
     * @param onChangeListener
     */
    public void setOnChangeListener(OnChangeListener onChangeListener) {
        mOnChangeListener = onChangeListener;
    }

    //改变画笔颜色 1--灰色 2,其它
    private void changePaintColor(int type) {
        mBackgroudPaint.setColor(type == 1 ? getColor(R.color.gray_e7ebee) : this.type == 1 ? getColor(R.color.main_color_green_03c087) : getColor(R.color.main_color_red_ff6969));
    }

    private void initPaint() {
        // 背景和变色得
        mBackgroudPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        changePaintColor(1);
        mBackgroudPaint.setStyle(Paint.Style.FILL);
        mBackgroudRectF = new RectF();

        // thumb相关设置
        mBlockPaint = new Paint(Paint.ANTI_ALIAS_FLAG); //消除锯齿
        mBlockPaint.setFilterBitmap(true);
        mBlockPaint.setDither(true);

        //初始化滑动，需要将图片做成layer,指定宽高，否则画出来的图片会拉长
        blockDrawable = getDrawable(R.drawable.layer_seekbar_buy);
    }

    private void initData() {
        if (!isInit) {
            rectWidth = dp2px(rectWidth);
            rectHeight = dp2px(rectHeight);
            lineHeight = dp2px(lineHeight);
            dotWidth = dp2px(dotWidth);

            rectPadding = (blockDrawable.getIntrinsicWidth() - rectWidth) / 2;
            perRectWidth = (getWidth() - rectPadding * 2 - rectWidth) / 4;
            lineWidth = perRectWidth - rectWidth - dotWidth * 2;
            perLineWidth = lineWidth + dotWidth * 2 + rectWidth;
            blockTop = (getHeight() - blockDrawable.getIntrinsicHeight()) / 2;
            rectTop = (getHeight() - rectHeight) / 2;
            lineTop = (getHeight() - lineHeight) / 2;
            progressLine = getWidth() - blockDrawable.getIntrinsicWidth();
            isInit = true;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initData();
        // 绘制灰色和变色背景
        drawBg(canvas);
        // 绘制block
        drawBlock(canvas);
    }

    private void drawBlock(Canvas canvas) {
        //初始化block的位置
        ints[0] = progressLine * progress;
        ints[1] = blockTop;
        ints[2] = progressLine * progress + blockDrawable.getIntrinsicWidth();
        ints[3] = blockTop + blockDrawable.getIntrinsicHeight();

        Bitmap block = ResourceUtil.drawableToBitmap(blockDrawable, blockDrawable.getIntrinsicWidth(), blockDrawable.getIntrinsicHeight());
        canvas.drawBitmap(block, progressLine * progress, blockTop, mBlockPaint);
    }

    private void drawBg(Canvas canvas) {
        float[] floats = new float[4];
        for (int i = 0; i < 5; i++) {
            //画rect
            floats[0] = rectPadding + i * perRectWidth;
            floats[1] = rectTop;
            floats[2] = rectPadding + rectWidth + i * perRectWidth;
            floats[3] = rectTop + rectHeight;
            changePaintColor(1);
            mBackgroudRectF.set(floats[0], floats[1], floats[2], floats[3]);
            canvas.drawRect(mBackgroudRectF, mBackgroudPaint);

            // 变色的rect
            if (progressLine * progress > floats[0]) {
                changePaintColor(2);
                if (progressLine * progress <= floats[2]) {
                    floats[2] = progressLine * progress;
                }
                mBackgroudRectF.set(floats[0], floats[1], floats[2], floats[3]);
                canvas.drawRect(mBackgroudRectF, mBackgroudPaint);
            }

            if (i < 4) {
                //画线
                floats[0] = rectPadding + rectWidth + dotWidth + i * perLineWidth;
                floats[1] = lineTop;
                floats[2] = rectPadding + rectWidth + dotWidth + lineWidth + i * perLineWidth;
                floats[3] = lineTop + lineHeight;
                changePaintColor(1);
                mBackgroudRectF.set(floats[0], floats[1], floats[2], floats[3]);
                canvas.drawRect(mBackgroudRectF, mBackgroudPaint);

                // 变色的线
                if (progressLine * progress > floats[0]) {
                    changePaintColor(2);
                    if (progressLine * progress <= floats[2]) {
                        floats[2] = progressLine * progress;
                    }
                    mBackgroudRectF.set(floats[0], floats[1], floats[2], floats[3]);
                    canvas.drawRect(mBackgroudRectF, mBackgroudPaint);
                }
            }
        }
    }

    /**
     * 给block加2dp的padding,是再判断是否触碰在block上，
     */
    public boolean isTouchThumb(float x, float y) {
        ints[0] -= dp2px(2f);
        ints[1] -= dp2px(2f);
        ints[2] += dp2px(2f);
        ints[3] += dp2px(2f);
        return x >= ints[0] && x <= ints[2] && y >= ints[1] && y <= ints[3];
    }

    /**
     * 是否在控件范围内,给8dp的padding
     */
    public boolean isBelong(float x, float y) {
        return x >= 0 - dp2px(8f) && x <= getWidth() + dp2px(8f) && y >= 0 - dp2px(8f) && y <= getHeight() + dp2px(8f);

    }

    /**
     * 是否是横向滑动
     */
    public boolean isHorizontalMove(float x, float y) {
        return Math.abs(lastX - x) > Math.abs(lastY - y) + dp2px(2f);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (isTouchThumb(x, y)) {
                    getParent().requestDisallowInterceptTouchEvent(true);// 不允许父控件拦截
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (isHorizontalMove(x, y) && isBelong(x, y)) {  //在范围内
                    progress = x / progressLine;
                    if (progress <= 0) progress = 0;
                    if (progress >= 1) progress = 1;
                    if (mOnChangeListener != null) {
                        mOnChangeListener.onProgressChanged(progress);
                    }
                    //刷新
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            default:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    public interface OnChangeListener {

        void onProgressChanged(double progress);
    }
}
