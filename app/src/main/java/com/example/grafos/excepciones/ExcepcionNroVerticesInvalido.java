/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.grafos.excepciones;

/**
 *
 * @author Usuario
 */
public class ExcepcionNroVerticesInvalido extends Exception{
    public ExcepcionNroVerticesInvalido(){
        super("Numero de Vertices Inválido");
    }
    
    public ExcepcionNroVerticesInvalido(String message){
        super(message); 
    }
}
