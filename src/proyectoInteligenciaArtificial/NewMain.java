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
import javax.swing.JFrame;

public class NewMain {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new FileReader("DatosMapa.txt"));
        int n = 63;
        Grafo g = new Grafo(n);
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            if (a == -1 && b == -1) {
                break;
            }
            sc.nextLine();
            String c = sc.nextLine();
            g.agregarArista(a, b, c);
        }
        Render r = new Render(g);
        r.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class Render extends JFrame {

    public Render(Grafo g) throws Exception {
        setBounds(220, 40, 870, 700);
        setTitle("Proyecto 2 Inteligencia Artificial");
        setResizable(true);
        Panel panel = new Panel(g);
        add(panel);
        setVisible(true);
    }
}
