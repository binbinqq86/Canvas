package com.example.tb.canvas;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class XfermodeActivity extends AppCompatActivity implements View.OnClickListener {
    
    private Button btClear;
    private Button btSrc;
    private Button btDst;
    private Button btSrcOver;
    private Button btDstOver;
    private Button btSrcIn;
    private Button btDstIn;
    private Button btSrcOut;
    private Button btDstOut;
    private Button btSrcATop;
    private Button btDstATop;
    private Button btXOr;
    private Button btDarken;
    private Button btLighten;
    private Button btMultiply;
    private Button btScreen;
    private CanvasView cv;
    private Button btAdd;
    private Button btOverlay;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xfermode);
        initView();
        initListeners();
        cv.setDrawWhat(CanvasView.DRAW_PorterDuffXfermode);
    }
    
    private void initListeners() {
        btClear.setOnClickListener(this);
        btSrc.setOnClickListener(this);
        btDst.setOnClickListener(this);
        btSrcOver.setOnClickListener(this);
        btDstOver.setOnClickListener(this);
        btSrcIn.setOnClickListener(this);
        btDstIn.setOnClickListener(this);
        btSrcOut.setOnClickListener(this);
        btDstOut.setOnClickListener(this);
        btSrcATop.setOnClickListener(this);
        btDstATop.setOnClickListener(this);
        btXOr.setOnClickListener(this);
        btDarken.setOnClickListener(this);
        btLighten.setOnClickListener(this);
        btMultiply.setOnClickListener(this);
        btScreen.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        btOverlay.setOnClickListener(this);
    }
    
    private void initView() {
        btClear = (Button) findViewById(R.id.bt_clear);
        btSrc = (Button) findViewById(R.id.bt_src);
        btDst = (Button) findViewById(R.id.bt_dst);
        btSrcOver = (Button) findViewById(R.id.bt_src_over);
        btDstOver = (Button) findViewById(R.id.bt_dst_over);
        btSrcIn = (Button) findViewById(R.id.bt_src_in);
        btDstIn = (Button) findViewById(R.id.bt_dst_in);
        btSrcOut = (Button) findViewById(R.id.bt_src_out);
        btDstOut = (Button) findViewById(R.id.bt_dst_out);
        btSrcATop = (Button) findViewById(R.id.bt_src_a_top);
        btDstATop = (Button) findViewById(R.id.bt_dst_a_top);
        btXOr = (Button) findViewById(R.id.bt_x_or);
        btDarken = (Button) findViewById(R.id.bt_darken);
        btLighten = (Button) findViewById(R.id.bt_lighten);
        btMultiply = (Button) findViewById(R.id.bt_multiply);
        btScreen = (Button) findViewById(R.id.bt_screen);
        cv = (CanvasView) findViewById(R.id.cv);
        btAdd = (Button) findViewById(R.id.bt_add);
        btOverlay = (Button) findViewById(R.id.bt_overlay);
    }
    
    @Override
    public void onClick(View view) {
        PorterDuff.Mode mode = PorterDuff.Mode.CLEAR;
        switch (view.getId()) {
            case R.id.bt_clear:
                mode = PorterDuff.Mode.CLEAR;
                break;
            case R.id.bt_darken:
                mode = PorterDuff.Mode.DARKEN;
                break;
            case R.id.bt_dst:
                mode = PorterDuff.Mode.DST;
                break;
            case R.id.bt_dst_a_top:
                mode = PorterDuff.Mode.DST_ATOP;
                break;
            case R.id.bt_dst_in:
                mode = PorterDuff.Mode.DST_IN;
                break;
            case R.id.bt_dst_out:
                mode = PorterDuff.Mode.DST_OUT;
                break;
            case R.id.bt_dst_over:
                mode = PorterDuff.Mode.DST_OVER;
                break;
            case R.id.bt_lighten:
                mode = PorterDuff.Mode.LIGHTEN;
                break;
            case R.id.bt_multiply:
                mode = PorterDuff.Mode.MULTIPLY;
                break;
            case R.id.bt_screen:
                mode = PorterDuff.Mode.SCREEN;
                break;
            case R.id.bt_src:
                mode = PorterDuff.Mode.SRC;
                break;
            case R.id.bt_src_a_top:
                mode = PorterDuff.Mode.SRC_ATOP;
                break;
            case R.id.bt_src_in:
                mode = PorterDuff.Mode.SRC_IN;
                break;
            case R.id.bt_src_out:
                mode = PorterDuff.Mode.SRC_OUT;
                break;
            case R.id.bt_src_over:
                mode = PorterDuff.Mode.SRC_OVER;
                break;
            case R.id.bt_x_or:
                mode = PorterDuff.Mode.XOR;
                break;
            case R.id.bt_add:
                mode = PorterDuff.Mode.ADD;
                break;
            case R.id.bt_overlay:
                mode = PorterDuff.Mode.OVERLAY;
                break;
        }
        cv.setMode(mode);
        cv.setDrawWhat(CanvasView.DRAW_PorterDuffXfermode);
    }
}
