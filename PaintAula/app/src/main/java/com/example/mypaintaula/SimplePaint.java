package com.example.mypaintaula;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SimplePaint extends View {

    public static final int LINHA = 0;
    public static final int RETANGULO = 1;
    public static final int CIRCULO = 2;

    private int tipoDesenho = LINHA;

    List<Paint> mPaintList;
    List<Path> mPathList;
    Paint currentPaint;
    Path currentPath;

    ColorDrawable currentColor;

    private float startX;
    private float startY;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        mPaintList = new ArrayList<>();
        mPathList = new ArrayList<>();

        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);

        initLayerDraw();
    }

    public void initLayerDraw() {
        currentPaint = new Paint();
        currentPath = new Path();

        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(20);
        currentPaint.setColor(currentColor.getColor());
    }

    public void setTipoDesenho(int tipo) {
        tipoDesenho = tipo;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mPaintList.size(); i++) {
            canvas.drawPath(mPathList.get(i), mPaintList.get(i));
        }

        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float lx = event.getX();
        float ly = event.getY();

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                startX = lx;
                startY = ly;

                if (tipoDesenho == LINHA) {
                    currentPath.moveTo(lx, ly);
                    currentPath.lineTo(lx, ly);
                }

                break;

            case MotionEvent.ACTION_MOVE:

                if (tipoDesenho == LINHA) {

                    currentPath.lineTo(lx, ly);

                } else if (tipoDesenho == RETANGULO) {

                    currentPath.reset();
                    currentPath.addRect(startX, startY, lx, ly, Path.Direction.CW);

                } else if (tipoDesenho == CIRCULO) {

                    currentPath.reset();

                    float raio = (float) Math.sqrt(
                            Math.pow(lx - startX, 2) +
                                    Math.pow(ly - startY, 2)
                    );

                    currentPath.addCircle(startX, startY, raio, Path.Direction.CW);
                }

                invalidate();
                break;

            case MotionEvent.ACTION_UP:

                if (tipoDesenho == LINHA) {
                    currentPath.lineTo(lx, ly);
                }

                mPaintList.add(currentPaint);
                mPathList.add(currentPath);

                initLayerDraw();

                invalidate();
                break;
        }

        return true;
    }

    public void setColor(android.graphics.Color color) {
        currentColor.setColor(color.toArgb());
        currentPaint.setColor(color.toArgb());
    }
}