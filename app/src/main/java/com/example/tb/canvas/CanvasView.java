package com.example.tb.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by : tb on 2017/10/23 上午11:17.
 * Description :Canvas绘制相关
 * <a href="https://developer.android.google.cn/reference/android/graphics/PorterDuff.Mode.html">详情</a>
 */
public class CanvasView extends View{
    private int drawWhat=0;
    public static final int DRAW_BITMAP0=0;
    public static final int DRAW_BITMAP1=1;
    public static final int DRAW_ARC=2;
    public static final int DRAW_OVAL=3;
    public static final int DRAW_PorterDuffXfermode=4;
    private Paint mPaint;
    private Bitmap bitmap;
    private int width,height;
    private PorterDuff.Mode xfermode= PorterDuff.Mode.CLEAR;
    
    public CanvasView(Context context) {
        this(context,null);
    }
    
    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    
    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    
    private void init(){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.ROUND);// 笔刷图形样式
        mPaint.setStrokeCap(Paint.Cap.ROUND);// 设置画笔转弯的连接风格
        mPaint.setDither(true);//防抖动
        bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.aaa);
        setBackgroundColor(Color.GREEN);
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width=w;
        height=h;
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        switch (drawWhat){
            case DRAW_BITMAP0:
                //x,y代表要绘制在canvas上的位置坐标，而非图片内容的位置坐标
//                canvas.drawBitmap(bitmap,0,0,mPaint);
                canvas.drawBitmap(bitmap,50,50,mPaint);
                break;
            case DRAW_BITMAP1:
                //要绘制的图片本身的内容区域，为null则绘制整个图
                Rect srcf=new Rect(100,100,bitmap.getWidth(),bitmap.getHeight());
                //要绘制图片的canvas区域，画布本身的大小
                RectF dstf=new RectF(100,100,width,height);
                canvas.drawBitmap(bitmap,null,dstf,mPaint);
                break;
            case DRAW_ARC:
                //要绘制的圆弧的外接矩形
                RectF rf=new RectF(0,0,width/2f,height/2f);
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(rf,mPaint);
                mPaint.setColor(Color.CYAN);
                mPaint.setStyle(Paint.Style.STROKE);
                //中间三个参数：起始角度（3点钟方向代表0度），顺时针扫过的角度，是否绘制边缘线（从中心到两边的连线）
                canvas.drawArc(rf,90,90,true,mPaint);
                break;
            case DRAW_OVAL:
                //要绘制的椭圆的外接矩形
                RectF rf1=new RectF(0,0,width/2f,height/2f);
                mPaint.setColor(Color.BLUE);
                canvas.drawRect(rf1,mPaint);
                mPaint.setColor(Color.CYAN);
                canvas.drawOval(rf1,mPaint);
                break;
            case DRAW_PorterDuffXfermode:
                //关闭硬件加速，否则clear,darken,lighten,overlay绘制不正常
                setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                type1(canvas);
    
//                type2(canvas);
                break;
        }
    }
    
    /**
     * 另外一种方案，不用saveLayer，把需要绘制的东西放在一张临时的bitmap中
     * @param canvas
     */
    private void type2(Canvas canvas) {
        //创建一张临时bitmap来存放最终要绘制的内容
        Bitmap bT=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas cT=new Canvas(bT);
        //绘制dst
        cT.drawBitmap(bitmap,0,0,mPaint);
        
        //创建遮罩层bitmap(宽高覆盖整个绘制区域，这样clear模式直接清画布了)
        Bitmap bb=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas cc=new Canvas(bb);
        mPaint.setColor(Color.YELLOW);
        cc.drawCircle(width/2f,height/2f,width/2f>height/2f?height/2f:width/2f,mPaint);
        
        //绘制src
        mPaint.setXfermode(new PorterDuffXfermode(xfermode));
        cT.drawBitmap(bb,0,0,mPaint);
        
        mPaint.setXfermode(null);
        //绘制最终内容
        canvas.drawBitmap(bT,0,0,mPaint);
    }
    
    /**
     * 采用layer分层绘制的概念，把最终绘制的内容保存在新建的图层
     * @param canvas
     */
    private void type1(Canvas canvas) {
        Paint pt=new Paint(mPaint);
//        int count=canvas.saveLayer(0,0,width,height,mPaint,Canvas.ALL_SAVE_FLAG);
        //绘制dst
        canvas.drawBitmap(bitmap,0,0,mPaint);
        
        //创建遮罩层bitmap(宽高覆盖整个绘制区域，这样clear模式直接清画布了)
        Bitmap bb=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas cc=new Canvas(bb);
        pt.setColor(Color.YELLOW);
        cc.drawCircle(width/2f,height/2f,width/2f>height/2f?height/2f:width/2f,pt);
        
        //设置模式
        pt.setXfermode(new PorterDuffXfermode(xfermode));
        //绘制src
        canvas.drawBitmap(bb,0,0,pt);
        //还原
//        mPaint.setXfermode(null);
//        canvas.restoreToCount(count);
    }
    
    public void setMode(PorterDuff.Mode mode){
        this.xfermode=mode;
    }
    public void setDrawWhat(int drawWhat){
        this.drawWhat=drawWhat;
        invalidate();
    }
}
