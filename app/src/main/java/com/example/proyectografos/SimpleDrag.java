package com.example.proyectografos;

import static java.lang.Math.sin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.example.grafos.pesados.GrafoPesado;
import com.example.grafos.pesados.NodoKruskal;

import java.util.ArrayList;
import java.util.List;

public class SimpleDrag extends View {

    Path path;
    float x;
    float y;
    private Paint mPaint;
    private Bitmap  mBitmap;
    private Canvas mCanvas;
    List<nodoDraw> p1;
    nodoDraw nodo1;
    boolean putPesos=false;
    boolean desabilitarCLean=false;
    List<nodoDep> listaPesos;
    List<NodoKruskal> nK;
    private Paint   mBitmapPaint;

    public SimpleDrag(Context context) {
        super(context);
        init();

    }



    public SimpleDrag(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public SimpleDrag(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        init();
    }


    public void init(){
         mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        p1=new ArrayList<>();
        listaPesos=new ArrayList<>();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(2);
        mPaint.setColor(Color.BLUE);
        path=new Path();
        nK=new ArrayList<>();

    }

    public void clearMap(){
        init();
        invalidate();
        putPesos=false;
        desabilitarCLean=false;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.map) //-->here load your image
                .copy(Bitmap.Config.ARGB_8888, true);


        //mCanvas = new Canvas(mBitmap);

    }

    private void drawArrow( float from_x, float from_y, float to_x, float to_y)
    {
        float angle,anglerad, radius, lineangle;
        mPaint.setColor(Color.RED);
        //values to change for other appearance *CHANGE THESE FOR OTHER SIZE ARROWHEADS*
        radius=20f;
        angle=25f;

        //some angle calculations

        anglerad= (float) (Math.PI*angle/180.0f);
        lineangle= (float) (Math.atan2(to_y-from_y,to_x-from_x));

        //tha line
       //7 mPaint.setStyle(Paint.Style.STROKE);
        //path.moveTo(from_x,from_y);
        //path.lineTo(to_x,to_y);

        //tha triangle



        path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(to_x, to_y);
        path.lineTo((float)(to_x-radius*Math.cos(lineangle - (anglerad / 2.0))),
                (float)(to_y-radius*sin(lineangle - (anglerad / 2.0))));
        path.lineTo((float)(to_x-radius*Math.cos(lineangle + (anglerad / 2.0))),
                (float)(to_y-radius*sin(lineangle + (anglerad / 2.0))));




    }

    private void drawLinea( float from_x, float from_y, float to_x, float to_y)
    {
        float angle,anglerad, radius, lineangle;
        mPaint.setColor(Color.RED);
        //values to change for other appearance *CHANGE THESE FOR OTHER SIZE ARROWHEADS*
        radius=20f;
        angle=25f;

        //some angle calculations

        anglerad= (float) (Math.PI*angle/180.0f);
        lineangle= (float) (Math.atan2(to_y-from_y,to_x-from_x));

        //tha line
        mPaint.setStyle(Paint.Style.STROKE);
        path.moveTo(from_x,from_y);
        path.lineTo(to_x,to_y);

        //tha triangle

        /*path.setFillType(Path.FillType.EVEN_ODD);
        path.moveTo(to_x, to_y);
        path.lineTo((float)(to_x-radius*Math.cos(lineangle - (anglerad / 2.0))),
                (float)(to_y-radius*sin(lineangle - (anglerad / 2.0))));
        path.lineTo((float)(to_x-radius*Math.cos(lineangle + (anglerad / 2.0))),
                (float)(to_y-radius*sin(lineangle + (anglerad / 2.0))));*/




    }

    /* (non-Javadoc)
     * @see android.view.View#onDraw(android.graphics.Canvas)
     * @since Feb 19, 2013
     * @author rajeshcp
     */
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(mBitmap, canvas.getWidth(),    canvas.getHeight(), true);

        canvas.drawBitmap( scaledBitmap, 0, 0, null);
    for (nodoDraw n:p1) {
        canvas.drawPath(n.getPath(), n.getPaint());
    }
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
        mPaint.setTextSize(20);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        if (putPesos) {


            for (NodoKruskal k : nK) {

                nodoDep aux1=nodoEnLista(listaPesos,k.getOrigen());
                nodoDep aux2=nodoEnLista(listaPesos,k.getDestino());

                int x1=aux1.getX();
                int y1=aux1.getY();
                int x2=aux2.getX();
                int y2=aux2.getY();
                int dato1=(x1+x2)/2;
                int dato2=(y1+y2)/2;
                int valor=(int)k.getPeso();
                canvas.drawText(Integer.toString(valor)+" KM",dato1,dato2,mPaint);
            }
        }
      /*   //putBackground(canvas);
        //canvas.drawPath(path,mPaint);
        //canvas.drawPath(path,mPaint);
       canvas.drawCircle(66,280,10,mPaint);
        canvas.drawCircle(434,700,10,mPaint);
        canvas.drawCircle(606,1001,10,mPaint);
        canvas.drawCircle(339,1001,10,mPaint);
        canvas.drawCircle(248,1051,10,mPaint);
        canvas.drawCircle(380,1233,10,mPaint);
        canvas.drawCircle(421,1186,10,mPaint);
        canvas.drawCircle(459,1473,10,mPaint);
        canvas.drawCircle(166,899,10,mPaint);
        canvas.drawCircle(446,705,10,mPaint);
        canvas.drawCircle(280,720,10,mPaint);

        //drawArrow(mPaint,canvas,380,1233,421,1186);
        //drawArrow(mPaint,canvas,380,1233,421,1186);
        drawArrow(mPaint,canvas,446,705,280,720);

        ponerPeso(canvas,446,705,280,720,"235 km");*/


    }



    public void dibujarVertices(List<nodoDep> lista){
        desabilitarCLean=false;
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
       p1.clear();
        path=new Path();
        for (nodoDep np1:lista){
            if(np1.getPosicion()!=-1) {
                int x = np1.getX();
                int y = np1.getY();
                path.addCircle(x, y, 10, Path.Direction.CW);
            }
        }


        nodo1=new nodoDraw(path,mPaint);
        putPesos=false;
        p1.add(nodo1);
        invalidate();

    }

    public nodoDep nodoEnLista(List<nodoDep> lista,int posicion){
        for (int i=0;i<lista.size();i++){
            nodoDep np=lista.get(i);
            if (np.getPosicion()==posicion){
                return np;
            }

        }
        return null;
    }

    public void dibujarAristas(List<nodoDep> lista,GrafoPesado g){

        //Iterable it=g.cantidadDeVertices()
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.RED);
        putPesos=true;
        path=new Path();
        for (int i=0;i<lista.size();i++){
            nodoDep np=lista.get(i);
            if(np.getPosicion()!=-1){
            Iterable<Integer> adyacentes=g.adyacentesDeVertice(np.getPosicion());
            for (Integer vertice:adyacentes) {
                int x1 = np.getX();
                int y1 = np.getY();
                nodoDep aux=nodoEnLista(lista,vertice);
                int x2 = aux.getX();
                int y2 = aux.getY();
                drawArrow(x1,y1,x2,  y2);


            }
            }
        }
        nodo1=new nodoDraw(path,mPaint);
        p1.add(nodo1);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        path=new Path();

        for (int i=0;i<lista.size();i++){
            nodoDep np=lista.get(i);
            if(np.getPosicion()!=-1){
                Iterable<Integer> adyacentes=g.adyacentesDeVertice(np.getPosicion());
                for (Integer vertice:adyacentes) {
                    int x1 = np.getX();
                    int y1 = np.getY();
                    nodoDep aux=nodoEnLista(lista,vertice);
                    int x2 = aux.getX();
                    int y2 = aux.getY();
                    drawLinea(x1,y1,x2,  y2);


                }
            }
        }
        nK=new ArrayList<>();
        nodo1=new nodoDraw(path,mPaint);
        p1.add(nodo1);

        g.llenarLista(nK);

        listaPesos=lista;

        invalidate();

    }


    public void dibujarAristasSinIndice(List<nodoDep> lista,GrafoPesado g){
        putPesos=true;

        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        path=new Path();

        for (int i=0;i<lista.size();i++){
            nodoDep np=lista.get(i);
            if(np.getPosicion()!=-1){
                Iterable<Integer> adyacentes=g.adyacentesDeVertice(np.getPosicion());
                for (Integer vertice:adyacentes) {
                    int x1 = np.getX();
                    int y1 = np.getY();
                    nodoDep aux=nodoEnLista(lista,vertice);
                    int x2 = aux.getX();
                    int y2 = aux.getY();
                    drawLinea(x1,y1,x2,  y2);
                }
            }
        }
        nK=new ArrayList<>();
        nodo1=new nodoDraw(path,mPaint);
        p1.add(nodo1);

        g.llenarLista(nK);

        listaPesos=lista;

        invalidate();

    }

    public void dibujarVertice(List<nodoDep> lista,int posicion){
        desabilitarCLean=false;
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(2);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.BLUE);
        p1.clear();
        path=new Path();
        for (nodoDep np1:lista){
            int x= np1.getX();
            int y= np1.getY();
            path.addCircle(x,y,10, Path.Direction.CW);

        }


        nodo1=new nodoDraw(path,mPaint);

        p1.add(nodo1);
        invalidate();

    }


    public void putBackground(Canvas canvas){
        canvas.drawRGB(0, 0, 0);
        Resources res = getResources();

        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.map);
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, canvas.getWidth(),    canvas.getHeight(), true);
        canvas.drawBitmap(scaledBitmap, 0, 0, null);
    }

    /* (non-Javadoc)
     * @see android.view.View#onTouchEvent(android.view.MotionEvent)
     * @since Feb 19, 2013
     * @author rajeshcp
     */

    TextView cajatexto;
    /*public boolean onTouchEvent(MotionEvent e){
        final Handler handler = new Handler();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());

        x=(int)e.getX();
        y=(int)e.getY();
        builder.setMessage(""+x+"|"+y);
        final AlertDialog dialog = builder.create();
        dialog.show();

        return true;
    }*/







}

