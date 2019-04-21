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
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.awt.*;
import java.io.FileReader;
import javax.swing.*;
import java.util.*;

public class Panel extends JPanel implements MouseListener, MouseMotionListener, ActionListener {

    private JButton ruta2, ruta22;
    private JTextField origen;
    private JTextField destino;
    private Grafo g;
    private int mapaCart[][];
    private LinkedList<Integer> path;
    int x0, y0, ascii;
    
    private JTextField menCalles;
    
    
    Scanner sc = new Scanner(new FileReader("Coordenadas.txt"));

    public Panel(Grafo g) throws Exception {
        this.g = g;
        ascii = 65;
        path = new LinkedList<Integer>();
        mapaCart = new int[g.vertices][3];
        setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        dibujarMenu();
        ponerPosicionMapa();
    }
    private void ponerJTextField(JTextField text, int x1, int y1, int x2, int y2){
        text.setBounds(x1, y1, x2, y2);
        text.setVisible(true);
        this.add(text);
    }
    private void ponerJLabel(String text, int x1, int y1, int x2, int y2, Color color){
        JLabel label = new JLabel(text);
        label.setBounds(x1, y1, x2, y2);
        label.setVisible(true);
        label.setForeground(color);
        this.add(label);
    }
    
    private void dibujarMenu() {
        String lisParques[] = {" 0   = Parque Lincold."," 58 = Jardin Botanico."," 59 = Parque de Educacion Vial."," 60 = Parque de la Familia."," 61 = Parque Mariscal Santa Cruz."," 62 = Parque Escuela."};
        for(int i=0;i<lisParques.length;i++){
            ponerJLabel(lisParques[i], 630, 480+(i*15), 200, 30, Color.WHITE);
        }
        origen = new JTextField("");
        ponerJTextField(origen, 610, 600, 75, 20);
        destino = new JTextField("");
        ponerJTextField(destino, 690, 600, 75, 20);

        ponerJLabel("inicio", 610, 620, 100, 20, Color.WHITE);
        ponerJLabel("destino", 690, 620, 100, 20, Color.WHITE);

        ruta2 = new JButton();
        ruta2.setText(" ok ");
        ruta2.setBounds(/*450*/770, 600, 75, 20);
        add(ruta2);
        
        menCalles = new JTextField("Sin Ruta");
        ponerJTextField(menCalles,10, 600, 600, 20);
        
        ruta2.setForeground(new java.awt.Color(102, 0, 51));
        ruta2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
    }

    private void ponerPosicionMapa() throws Exception {
        //Scanner sc = new Scanner(new FileReader("Coordenas.txt"));
        int i = 0;
        while (true) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if (a == -1 && a == -1 && c == -1) {
                break;
            }
            mapaCart[i][0] = a;
            mapaCart[i][1] = b;
            mapaCart[i][2] = c;
            i++;
        }
        repaint();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        int inicio = Integer.parseInt(origen.getText());
        int destino = Integer.parseInt(this.destino.getText());
        path = g.algorithmBidirectional(inicio, destino);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 860, 640);
        g.setColor(Color.WHITE);
        
        for(int[] pos : mapaCart){
            Graphics2D g1 = (Graphics2D)g;
            g1.setStroke(new BasicStroke(3));
            for (int s : this.g.listaAdyArr[pos[2]]) {
                int x = mapaCart[s][0];
                int y = mapaCart[s][1];
                g.drawLine(pos[0], pos[1], x, y);
            }
        }
        
        Set<String> set = new LinkedHashSet<String>();//Aqui se guardan las calles recorridas
        if (!path.isEmpty()) {
            g.setColor(Color.RED);
            for (int i = 0; i < path.size() - 1; i++) {
                set.add(this.g.mapa[path.get(i)][path.get(i+1)]);
                int[] aux1 = mapaCart[path.get(i)];
                int[] aux2 = mapaCart[path.get(i+1)];
                g.drawLine(aux1[0], aux1[1], aux2[0], aux2[1]);
            }
        }
        
        for (int[] pos : mapaCart){
            int lincold = 0, botanico = 58, vial = 59, familia = 60, acuatico = 61, escuela = 62;
            if(pos[2]==lincold||pos[2]==botanico||pos[2]==vial||pos[2]==familia||pos[2]==acuatico||pos[2]==escuela){
                g.setColor(Color.GREEN);
                g.fillOval(pos[0]-10, pos[1]-10, 30, 30);
                g.setColor(Color.BLACK);
                g.drawString(""+pos[2], pos[0], pos[1] + 10);
            }else{
                g.setColor(Color.WHITE);
                g.fillOval(pos[0] - 10, pos[1] - 10, 30, 30);
                g.setColor(Color.BLACK);
                g.drawString(""+pos[2], pos[0], pos[1] + 10);
            }
        }
        
        String str = "  ";
        for(String s: set){
            str+=s+", ";
        }
        str = str.substring(0, str.length()-2);
        menCalles.setText(str);
        paintComponents(g);
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseMoved(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        // TODO Auto-generated method stub
    }
}
