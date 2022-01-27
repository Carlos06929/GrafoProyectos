package com.example.proyectografos;

import static java.lang.Math.sin;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

class Vista extends View {

    public Vista(Context context){
        super(context);

    }
    public Vista(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    /**
     * @param context
     * @param attrs
     * @param defStyle
     * @return of type SimpleDrag
     * Constructor function
     * @since Feb 19, 2013
     * @author rajeshcp
     */
    public Vista(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public void onDraw(Canvas canvas){
        Paint pintar=new Paint();
        String s="santa cruz";
        pintar.setStyle(Paint.Style.STROKE);
        pintar.setStrokeWidth(5);
        pintar.setColor(Color.RED);
        putBackground(canvas);
        //int ancho=canvas.getWidth();
        //canvas.drawCircle(300,300,50,pintar);
        //canvas.drawText(s,5,5,300,300,pintar);
        canvas.drawCircle(100,100,50,pintar);
        pintar.setStrokeWidth(5);
        pintar.setColor(Color.BLUE);
        //canvas.drawLine(300,300,10,10,pintar);
        pintar.setStyle(Paint.Style.FILL);
        pintar.setStrokeWidth(2);
        drawArrow(pintar,canvas,300,300,100,100);
        drawArrow(pintar,canvas,300,300,100,600);

    }

    private void drawArrow(Paint paint, Canvas canvas, float from_x, float from_y, float to_x, float to_y)
    {
        float angle,anglerad, radius, lineangle;

        //values to change for other appearance *CHANGE THESE FOR OTHER SIZE ARROWHEADS*
        radius=20f;
        angle=25f;

        //some angle calculations
        anglerad= (float) (Math.PI*angle/180.0f);
        lineangle= (float) (Math.atan2(to_y-from_y,to_x-from_x));

        //tha line
        canvas.drawLine(from_x,from_y,to_x,to_y,paint);

        //tha triangle
        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(to_x, to_y);
        path.lineTo((float)(to_x-radius*Math.cos(lineangle - (anglerad / 2.0))),
                (float)(to_y-radius*sin(lineangle - (anglerad / 2.0))));
        path.lineTo((float)(to_x-radius*Math.cos(lineangle + (anglerad / 2.0))),
                (float)(to_y-radius*sin(lineangle + (anglerad / 2.0))));

        path.close();

        canvas.drawPath(path, paint);
    }




    public void putBackground(Canvas canvas){
        canvas.drawRGB(0, 0, 0);
        Resources res = getResources();

        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.map);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(),canvas.getHeight()-200, true);
        canvas.drawBitmap(scaledBitmap, 0, 0, null);
    }



}
