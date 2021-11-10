package com.example.testnewdemo.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * 普通进度条
 */
public class SimpleProgress extends View {
    private Paint mBackPaint;
    private Paint mFrontPaint;
    private Paint mTextPaint;
    private float mStrokeWidth = 50;
    private int mHeight;
    private int mWidth;
    private RectF mRect;
    private float mRadius = 200;

    public SimpleProgress(Context context) {
        super(context);
        init();
    }


    public SimpleProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimpleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 完成相关参数初始化
     */
    private void init() {
        mBackPaint = new Paint();
        mBackPaint.setColor(Color.WHITE);
        mBackPaint.setAntiAlias(true);//抗锯齿
        mBackPaint.setStyle(Paint.Style.STROKE);//画笔空心
        mBackPaint.setStrokeWidth(mStrokeWidth);//设置线宽

        mFrontPaint = new Paint();
        mFrontPaint.setColor(Color.GREEN);
        mFrontPaint.setAntiAlias(true);//抗锯齿
        mFrontPaint.setStyle(Paint.Style.STROKE);//描边
        mFrontPaint.setStrokeWidth(mStrokeWidth);

        mTextPaint = new Paint();
        mTextPaint.setColor(Color.GREEN);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(80);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getRealSize(widthMeasureSpec);
        mHeight= getRealSize(heightMeasureSpec);
        setMeasuredDimension(mWidth,mHeight);
    }

    public int getRealSize(int measureSpec) {
        int result = 1;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.UNSPECIFIED) {
            result = (int) (mRadius * 2 + mStrokeWidth);
        } else {
            result = size;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        initRect();

    }

    private void initRect() {
        if (mRect == null){
            mRect = new RectF();
            int viewSize = (int) (mRadius*2);
            int left = (mWidth-viewSize)/2;
            int top =(mHeight-viewSize)/2;
            int right = left+viewSize;
            int bottom = top+viewSize;
            mRect.set(left,top,right,bottom);
        }
    }
}
