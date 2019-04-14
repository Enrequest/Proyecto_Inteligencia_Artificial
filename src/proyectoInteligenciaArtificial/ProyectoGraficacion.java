/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoInteligenciaArtificial;

import java.awt.*;
import java.io.PrintWriter;
import javax.swing.*;
import java.util.*;

public class ProyectoGraficacion {
    
    public static void main(String[] args)throws Exception {
        PrintWriter out = new PrintWriter("PruebaGrafi.txt");
        
        int x0=0,y0=0,x1=25,y1=25;
        double deltaX = x1 - x0;
        double deltaY = y1 - y0;
        double deltaErr = Math.abs(deltaY/deltaX);
        double error = 0.0;
        int y = y0;
        for(int x=x0; x <= x1; x++){
            plot(x,y, out);
            
            error = error + deltaErr;
            if(error >= 0.5){
                y = y + sign(deltaY)*1;
            }
        }
    }
    public static void plot(int x, int y, PrintWriter out) {
        for(int i=0;i<y;i++){
            for(int j=0;j<x;j++){
                out.print("  ");
                out.flush();
            }
            out.println("*");
            out.flush();
        }
    }
    public static int sign(double n) {
        return (int)Math.signum(n);
    }
}
