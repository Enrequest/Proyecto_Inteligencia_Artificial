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
    
    public int algorithmBidirectional(int s, int t) {
        LinkedList<Integer> OpenF = new LinkedList<>();
        LinkedList<Integer> OpenB = new LinkedList<>();
        LinkedList<Integer> ClosedF = new LinkedList<>();
        LinkedList<Integer> ClosedB = new LinkedList<>();
        
        int s_parent[] = new int[vertices];
        int t_parent[] = new int[vertices];
        
        s_parent[s] = -1;
        t_parent[t] = -1;
        
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
                            s_parent[succ] = n;
                            OpenF.add(succ);
                        }
                    }
                    if(OpenB.contains(succ)){
                        p = succ;
                        LinkedList<Integer> path = getPath(s_parent,t_parent,s,t,p); 
                        System.out.println("Camino encontrado");
                        for(int act : path){
                           System.out.printf("%d ",act);
                        }
                        System.out.println();
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
                            t_parent[succ] = n;
                            OpenB.add(succ);
                        }
                    }
                    if(OpenF.contains(succ)){
                        p = succ;
                        LinkedList<Integer> path = getPath(s_parent,t_parent,s,t,p); 
                        System.out.println("Camino encontrado");
                        for(int act : path){
                           System.out.printf("%d ",act);
                        }
                        System.out.println();
                        return p;
                    }
                }
            }
            front = !front;
        }
        return -1;
    }
}
