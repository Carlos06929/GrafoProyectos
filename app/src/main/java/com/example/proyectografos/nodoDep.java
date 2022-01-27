package com.example.proyectografos;

import com.example.grafos.pesados.NodoKruskal;

import java.util.Objects;

public class nodoDep {
    String nombre;
    int x;
    int y;
    int posicion;

    public nodoDep(String nombre,int x, int y){
        this.nombre=nombre;
        this.x=x;
        this.y=y;
        posicion=-1;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }


}
