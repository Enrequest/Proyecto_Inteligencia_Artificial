/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoInteligenciaArtificial;

/**
 *
 * @author HP
 */
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        int n = 15;//Numero de vertices del grafo.
        int s = 0;//Vertice origen.
        int t = 14;//Vertice objetivo.
        Grafo g = new Grafo(n);
        g.agregarArista(0, 4);
        g.agregarArista(1, 4);
        g.agregarArista(2, 5);
        g.agregarArista(3, 5);
        g.agregarArista(4, 6);
        g.agregarArista(5, 6);
        g.agregarArista(6, 7);
        g.agregarArista(7, 8);
        g.agregarArista(8, 9);
        g.agregarArista(8, 10);
        g.agregarArista(9, 11);
        g.agregarArista(9, 12);
        g.agregarArista(10, 13);
        g.agregarArista(10, 14);
        
        if(g.busquedaBidireccional(s, t)){
            System.out.println("Existe camino");
        }
        /*int res = g.algorithmBidirectional(s, t);
        if(res != -1){
            System.out.println("Existe Camino "+res);
        }else{
            System.out.println("Falla");
        }*/
    }
}
