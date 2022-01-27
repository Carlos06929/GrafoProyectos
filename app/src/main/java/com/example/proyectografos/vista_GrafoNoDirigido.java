package com.example.proyectografos;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.grafos.excepciones.ExcepcionAristaNoExiste;
import com.example.grafos.excepciones.ExcepcionAristaYaExiste;
import com.example.grafos.excepciones.ExcepcionNroVerticesInvalido;
import com.example.grafos.pesados.DigrafoPesado;
import com.example.grafos.pesados.GrafoPesado;

import java.util.ArrayList;
import java.util.List;

public class vista_GrafoNoDirigido extends AppCompatActivity{
    GrafoPesado g;

    final int pando=0;
    final int lapaz=1;
    final int beni=2;
    final int oruro=3;
    final int potosi=4;
    final int cochabamba=5;
    final int chuquisaca=6;
    final int tarija=7;
    final int santacruz=8;
    List<List<Integer>> l1;
    private Spinner sp1;
    private Spinner sp2;
    SimpleDrag s;
    List<nodoDep> lista=new ArrayList<>();
    boolean verticesIndividuales=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vista_grafosnodirigido);



        s=(SimpleDrag)findViewById(R.id.lienzo);


        llenarLista();


    }


    //BUSCAMOS LA POSICION EN LA LISTA DE EL DEPARTAMENTO QUE ENTRA POR PARAMETRO
    public int buscarPosicion(String valor){
        for (int i=0;i<lista.size();i++){
            nodoDep np=lista.get(i);
            if(np.getNombre()==valor){
                return i;
            }

        }
        return -1;
    }


    //METODO PARA LLENAR LOS SPINNERS CON TODOS LOS NOMBRES
    public void llenarSpinners(){
        //LLENAR LISTA PARA LOS SPINNERS
        List<String> opciones=new ArrayList<>();
        for (nodoDep np1:lista) {
            opciones.add(np1.getNombre());
        }

        //LLENAR SPINNER1
        sp1=(Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.style_spinner,opciones);
        sp1.setAdapter(adapter);


        //LLENAR SPINNER2
        sp2=(Spinner)findViewById(R.id.spinner2);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this, R.layout.style_spinner,opciones);
        sp2.setAdapter(adapter2);

    }

    public void llenarLista(){
        nodoDep np;

        np=new nodoDep("Pando",132,166);
        lista.add(np);

        np=new nodoDep("La Paz",84,393);
        lista.add(np);

        np=new nodoDep("Beni",276,301);
        lista.add(np);

        np=new nodoDep("Oruro",123,648);
        lista.add(np);

        np=new nodoDep("Potosi",195,773);
        lista.add(np);

        np=new nodoDep("Cochabamba",235,545);
        lista.add(np);

        np=new nodoDep("Chuquisaca",301,755);
        lista.add(np);

        np=new nodoDep("Tarija",287,864);
        lista.add(np);

        np=new nodoDep("Santa Cruz",457,538);
        lista.add(np);

        llenarSpinners();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_gnd,menu);
        return true;
    }


    //DIBUJAR LOS VERTICES EN LA VISTA DEL MAPA
    public void btn_vertices_predeterminados(MenuItem view){
        verticesIndividuales=true;
        try {
            g=new DigrafoPesado(9);

        } catch (ExcepcionNroVerticesInvalido excepcionNroVerticesInvalido) {
            excepcionNroVerticesInvalido.printStackTrace();
        }
        for (int i=0;i<lista.size();i++) {
            lista.get(i).setPosicion(i);


        }
        s.dibujarVertices(lista);

    }

    public void btn_aristas_predeterminados(MenuItem view){
        boolean validacion=false;
        for (nodoDep np:lista){
            if (np.getPosicion()==-1){
                validacion=true;
                break;
            }
        }
        if (validacion==false) {
            aristas_Predeterminadas();
            s.dibujarAristasSinIndice(lista, g);
        }else{
            Toast.makeText(this,"Requiere vertice por defecto para esta accion",Toast.LENGTH_SHORT).show();
        }
    }


    public void clean(){
        for (int i=0;i<lista.size();i++) {
            lista.get(i).setPosicion(-1);

        }
    }


    //METODO PARA PONER LOS VERTICES PREDETERMINADOS
    public void aristas_Predeterminadas(){

        try {
            g=new DigrafoPesado(9);

        } catch (ExcepcionNroVerticesInvalido excepcionNroVerticesInvalido) {
            excepcionNroVerticesInvalido.printStackTrace();
        }

        try {

            g.insertarArista(8,2,552);
            g.insertarArista(8,5,480);
            g.insertarArista(8,7,665);
            g.insertarArista(4,6,154);
            g.insertarArista(4,7,346);
            g.insertarArista(4,3,311);
            g.insertarArista(4,1,536);
            g.insertarArista(5,1,234);

            g.insertarArista(0,2,1267);
            g.insertarArista(0,1,1119);
            g.insertarArista(1,2,429);
        } catch (ExcepcionAristaYaExiste excepcionAristaYaExiste) {
            excepcionAristaYaExiste.printStackTrace();
        }

    }



    //METODO PARA METER VERTICES DE A UNO EN UNO
    public void meterVertice(MenuItem item){
        if (verticesIndividuales){
            clean();
            if(g!=null) {
                g.limpiarGrafo();
            }else{
                g=new DigrafoPesado();
            }
            verticesIndividuales=false;
        }
        String seleccion=sp1.getSelectedItem().toString();
        int posicion=buscarPosicion(seleccion);


        if(lista.get(posicion).getPosicion()==-1){
            g.insertarVertice();
            lista.get(posicion).setPosicion(g.cantidadDeVertices()-1);
            s.dibujarVertices(lista);
            s.dibujarAristasSinIndice(lista,g);
        }else{
            Toast.makeText(this,"el vertice "+seleccion+" ya existe.",Toast.LENGTH_SHORT).show();
        }
    }


    //METODO PARA METER ARISTA UNO POR UNO
    public void meterArista(MenuItem item) throws ExcepcionAristaYaExiste {
        String seleccion1=sp1.getSelectedItem().toString();
        String seleccion2=sp2.getSelectedItem().toString();
        int posicion1=buscarPosicion(seleccion1);
        int posicion2=buscarPosicion(seleccion2);
        TextView t1=findViewById(R.id.editPeso);

        int aux1=lista.get(posicion1).getPosicion();
        int aux2=lista.get(posicion2).getPosicion();
        if( aux1!=-1&& aux2!=-1 && !t1.getText().toString().isEmpty()) {
            if(!g.existeAdayacencia(aux1,aux2)) {

                g.insertarArista(lista.get(posicion1).posicion, lista.get(posicion2).posicion, Double.parseDouble(t1.getText().toString()));

                s.dibujarAristasSinIndice(lista, g);
            }else{
                Toast.makeText(this,"Ya existe camino entre "+seleccion1 +" y "+seleccion2,Toast.LENGTH_SHORT).show();
            }
        }else if(aux1==-1){
            Toast.makeText(this,"El vertice "+ seleccion1+" no existe.",Toast.LENGTH_SHORT).show();
        }else if(aux2==-1){
            Toast.makeText(this,"El vertice "+ seleccion2+" no existe.",Toast.LENGTH_SHORT).show();
        }else if(t1.getText().toString().isEmpty()){
            Toast.makeText(this,"La distancia es dato obligatorio.",Toast.LENGTH_SHORT).show();
        }



    }

    public void limpiarMapa(MenuItem view){
        clean();
        g.limpiarGrafo();
        s.clearMap();
    }


    public void btn_kruskal(MenuItem view) throws ExcepcionNroVerticesInvalido, ExcepcionAristaNoExiste, ExcepcionAristaYaExiste {
        GrafoPesado g1=g.Kruskal();
        System.out.println(g1);
        s.dibujarVertices(lista);
        s.dibujarAristasSinIndice(lista,g1);

    }
}
