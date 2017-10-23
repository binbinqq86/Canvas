package com.example.tb.canvas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    
    private Button btDrawBitmap0,btDrawBitmap1;
    private Button btDrawArc;
    private Button btDrawOval;
    private Button btPorterDuffXfermode;
    private CanvasView cv;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListeners();
    }
    
    private void initListeners() {
        btDrawBitmap0.setOnClickListener(this);
        btDrawBitmap1.setOnClickListener(this);
        btDrawArc.setOnClickListener(this);
        btDrawOval.setOnClickListener(this);
        btPorterDuffXfermode.setOnClickListener(this);
    }
    
    private void initView() {
        btDrawBitmap0 = (Button) findViewById(R.id.bt_draw_bitmap0);
        btDrawBitmap1 = (Button) findViewById(R.id.bt_draw_bitmap1);
        btDrawArc = (Button) findViewById(R.id.bt_draw_arc);
        btDrawOval = (Button) findViewById(R.id.bt_draw_oval);
        btPorterDuffXfermode = (Button) findViewById(R.id.bt_PorterDuffXfermode);
        cv = (CanvasView) findViewById(R.id.cv);
    }
    
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_draw_bitmap0:
                cv.setDrawWhat(CanvasView.DRAW_BITMAP0);
                break;
            case R.id.bt_draw_bitmap1:
                cv.setDrawWhat(CanvasView.DRAW_BITMAP1);
                break;
            case R.id.bt_draw_arc:
                cv.setDrawWhat(CanvasView.DRAW_ARC);
                break;
            case R.id.bt_draw_oval:
                cv.setDrawWhat(CanvasView.DRAW_OVAL);
                break;
            case R.id.bt_PorterDuffXfermode:
                startActivity(new Intent(this,XfermodeActivity.class));
                break;
        }
    }
}
