/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoInteligenciaArtificial;

import java.io.*;
import java.util.*;

public class Grafo {

    int vertices;
    LinkedList<Integer> listaAdyArr[];
    boolean visitados[];

    public Grafo(int V) {
        vertices = V;
        listaAdyArr = new LinkedList[V];
        visitados = new boolean[V];
        for (int i = 0; i < V; i++) {
            listaAdyArr[i] = new LinkedList<>();
        }
    }

    public void agregarArista(int origen, int destino) {
        listaAdyArr[origen].add(destino);
        listaAdyArr[destino].add(origen);
    }

    public void BFS(LinkedList<Integer> cola, boolean visitados[], int padres[]) {
        int valor = cola.removeFirst();
        for (int i : listaAdyArr[valor]) {
            if (!visitados[i]) {
                padres[i] = valor;
                visitados[i] = true;
                cola.add(i);
            }
        }
    }

    public int hayInterseccion(boolean s_visitados[], boolean t_visitados[]) {
        int nodoInterseccion = -1;
        for (int i = 0; i < vertices; i++) {
            if (s_visitados[i] && t_visitados[i]) {
                nodoInterseccion = i;
            }
        }
        return nodoInterseccion;
    }

    public LinkedList<Integer> getPath(int s_parent[], int t_parent[], int s, int t, int intersectNode) {
        LinkedList<Integer> path = new LinkedList<Integer>();
        path.addLast(intersectNode);
        int i = intersectNode;
        while(i != s){
            path.addLast(s_parent[i]);
            i = s_parent[i];
        }
        Collections.reverse(path);
        i = intersectNode;
        while(i != t){
            path.addLast(t_parent[i]);
            i = t_parent[i];
        }
        return path;
    }

    public boolean busquedaBidireccional(int s, int t) {
        boolean respuesta = false;
        boolean s_visitados[] = new boolean[vertices];
        boolean t_visitados[] = new boolean[vertices];

        int s_padres[] = new int[vertices];
        int t_padres[] = new int[vertices];

        LinkedList<Integer> s_cola = new LinkedList<>();
        LinkedList<Integer> t_cola = new LinkedList<>();

        int nodoInterseccion = -1;

        s_cola.add(s);
        s_visitados[s] = true;
        s_padres[s] = -1;

        t_cola.add(t);
        t_visitados[t] = true;
        t_padres[t] = -1;

        while (!s_cola.isEmpty() && !t_cola.isEmpty()) {
            BFS(s_cola, s_visitados, s_padres);
            BFS(t_cola, t_visitados, t_padres);
            nodoInterseccion = hayInterseccion(s_visitados, t_visitados);
            if (nodoInterseccion != -1) {
                System.out.printf("Camino entre %d y %d es %d\n", s, t, nodoInterseccion);
                getPath(s_padres, t_padres, s, t, nodoInterseccion);
                respuesta = true;
                break;
            }
        }
        return respuesta;
    }

    public int algorithmBidirectional(int s, int t) {
        LinkedList<Integer> OpenF = new LinkedList<>();
        LinkedList<Integer> OpenB = new LinkedList<>();
        LinkedList<Integer> ClosedF = new LinkedList<>();
        LinkedList<Integer> ClosedB = new LinkedList<>();
        
        OpenF.add(s);
        OpenB.add(t);
        boolean front = true;
        int p = -1; 
        while (!OpenF.isEmpty() && !OpenB.isEmpty()) {
            if (front) {
                int n = OpenF.removeFirst();
                ClosedF.add(n);
                for (int succ : listaAdyArr[n]) {
                    if (ClosedF.contains(succ)) {
                        //continue;
                    } else {
                        if (!OpenF.contains(succ)) {
                            OpenF.add(succ);
                        }
                    }
                    if(OpenB.contains(succ)){
                       p = succ;
                       return p;
                    }
                }
            } else {
                int n = OpenB.removeFirst();
                ClosedB.add(n);
                for (int succ : listaAdyArr[n]) {
                    if (ClosedB.contains(succ)) {
                        //continue;
                    }else{
                        if(!OpenB.contains(succ)){
                            OpenB.add(succ);
                        }
                    }
                    if(OpenF.contains(succ)){
                        p = succ;
                        return p;
                    }
                }
            }
            front = !front;
        }
        return -1;
    }
}
