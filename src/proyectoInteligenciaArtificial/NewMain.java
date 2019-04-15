/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoInteligenciaArtificial;

/**
 *
 * @author Enrique Camacho
 */
import java.util.*;
import java.io.*;

public class NewMain {
    public static void main(String[] args)throws Exception {
        Scanner sc = new Scanner(new FileReader("DatosMapa.txt"));
        int n = 63;
        String mapa[][] = new String[n][n];
        Grafo g = new Grafo(n);
        System.out.println("Ingresando vertices, para cancelar escriba -1 -1");
        while(true){
            int a = sc.nextInt();
            int b = sc.nextInt();
            if(a==-1 && b==-1){
                System.out.println("Insercion de vertices terminado");
                break;
            }
            sc.nextLine();
            String c = sc.nextLine();
            mapa[a][b] = c;
            mapa[b][a] = c;
            g.agregarArista(a, b);
        }
        System.out.println("Ingrese nodo s y t");
        sc = new Scanner(System.in);
        int s = sc.nextInt();
        int t = sc.nextInt();
        System.out.println("El camino usando la busqueda bidireccional es:");
        LinkedList<Integer> path = g.algorithmBidirectional(s, t);
        for(int act : path){
            System.out.printf("%d ",act);
        }
        System.out.println();

        Set<String> set = new LinkedHashSet<String>(); 
        int i = path.removeFirst();
        int j = path.removeFirst();
        for(int act: path){
            set.add(mapa[i][j]);
            i = j;
            j = act;
        }
    }
}
