package com.example.wzy.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 作者：author
 * 创建时间：2018/7/24:22:07
 * 描述：
 * 修改人：
 * 修改时间：2018/7/24:22:07
 * 修改备注：
 */
public class MyCustomView extends View {

    Bitmap mBmp = BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_flowers);

    Bitmap mSrcBmp = BitmapFactory.decodeResource(this.getResources(),R.drawable.ic_mask);

    Paint mPaint = new Paint();

    RectF mRectArea = new RectF();

    Bitmap mSaveArcBmp = Bitmap.createBitmap(100,100, Bitmap.Config.ARGB_8888);
    Canvas tempCanvas = new Canvas(mSaveArcBmp);

    PorterDuff.Mode mPorterDuffMode = PorterDuff.Mode.MULTIPLY;
    Xfermode mXfermode = new PorterDuffXfermode(mPorterDuffMode);

    public MyCustomView(Context context) {
        super(context);

    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(100,heightMeasureSpec);
        width = height = Math.min(width,height);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);


        canvas.drawBitmap(mBmp,0,0,mPaint);

        //canvas.drawCircle(mBmp.getWidth()/2,mBmp.getHeight()/2,Math.max(mBmp.getWidth()/2,mBmp.getHeight()/2),mPaint);

        mPaint.setXfermode(mXfermode);
        canvas.drawBitmap(mSrcBmp,0,0,mPaint);


        mPaint.setColor(Color.RED);
        mRectArea.left = mRectArea.top = 0;
        mRectArea.right = mRectArea.bottom = Math.max(mBmp.getWidth(),mBmp.getHeight());



        canvas.drawArc(mRectArea,-90,90,true, mPaint);

        //
    }

    private int getMySize(int defaultSize, int measureSpec){
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode){
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.EXACTLY:
                mySize = size;
                break;
        }

        return mySize;
    }


}
