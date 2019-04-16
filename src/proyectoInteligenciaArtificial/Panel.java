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

    private JButton ruta2, ruta22;// = new JButton(" ok ");
    private JTextField l1;
    private JTextField l2;
    private JLabel lusuario;
    private JLabel lusuario2;
    private Grafo g; //= new Mapa();
    private int mapaCart[][];
    private LinkedList<Integer> path;
    private boolean primeraVez = true;
    int x0, y0, ascii;
    Scanner sc = new Scanner(new FileReader("Coordenadas.txt"));

    public Panel(Grafo g) throws Exception {
        this.g = g;
        ascii = 65;
        path = new LinkedList<Integer>();
        mapaCart = new int[g.vertices][3];
        setLayout(null);
        addMouseListener(this);
        addMouseMotionListener(this);
        //addActionListener(this);
        dibujarMenu();
        ponerPosicionMapa();

    }

    private void dibujarMenu() {
        l1 = new JTextField("");
        l1.setBounds(270, 600, 75, 20);
        l1.setVisible(true);
        add(l1);

        l2 = new JTextField("");
        l2.setBounds(350, 600, 75, 20);
        l2.setVisible(true);
        this.add(l2);

        lusuario = new JLabel("inicio");
        lusuario.setBounds(270, 620, 100, 20);
        lusuario.setVisible(true);
        this.add(lusuario);

        lusuario2 = new JLabel("destino");
        lusuario2.setBounds(350, 620, 100, 20);
        lusuario2.setVisible(true);
        this.add(lusuario2);

        ruta2 = new JButton();
        ruta2.setText(" ok ");
        ruta2.setBounds(450, 600, 75, 20);
        add(ruta2);

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
        int inicio = Integer.parseInt(l1.getText());
        int destino = Integer.parseInt(l2.getText());
        path = g.algorithmBidirectional(inicio, destino);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        /*
		 * x0 = e.getX(); y0 = e.getY(); if(ascii-65 < map.mapa.size()) {
		 * map.mapaCart.put(((char)ascii)+"", new int[]{x0,y0,ascii});
		 * 
		 * ascii++; repaint(); }
         */
    }

    @Override
    public void paint(Graphics g) {
        if (primeraVez) {
            //g.drawImage(((new ImageIcon("mapa.jpg")).getImage()), 0, 0, this);
            g.drawImage(((new ImageIcon("mapa.jpg")).getImage()), 0, 0, 860, 600, this);
            primeraVez = false;
        }else{
            g.setColor(Color.WHITE); // borar la imagen
            g.fillRect(0, 0, 860, 640);//
            g.setColor(Color.BLACK);
            pintRuta = true;
        }
        for (int[] pos : mapaCart) {
            g.drawOval(pos[0] - 10, pos[1] - 10, 30, 30);
            //g.drawString(Character.toString(pos[2], pos[0], pos[1] + 10));
            g.drawString(""+pos[2], pos[0], pos[1] + 10);
            /*for(int s: this.g.listaAdyArr[2]){
                int x = mapaCart[s][0];
		int y = mapaCart[s][1];
		g.drawLine(pos[0], pos[1], x, y);
            }*/
        }
        for(int[] pos : mapaCart){
            for (int s : this.g.listaAdyArr[pos[2]]/*map.mapa.get(((char) aux[2]) + "")*/) {
                // System.out.println(s);
                int x = mapaCart[s][0];//map.mapaCart.get(s)[0];
                // System.out.println(x);
                int y = mapaCart[s][1];//map.mapaCart.get(s)[1];
                // System.out.println(y);
                g.drawLine(pos[0], pos[1], x, y);
            }
        }
        /*
        if (!map.ruta.isEmpty()) {
            if (pintRuta) {
                g.setColor(Color.RED);
                for (int i = 0; i < map.ruta.size() - 1; i++) {
                    int[] aux1 = map.mapaCart.get(map.ruta.get(i));
                    int[] aux2 = map.mapaCart.get(map.ruta.get(i + 1));
                    g.drawLine(aux1[0], aux1[1], aux2[0], aux2[1]);
                }
            }
        }*/
        paintComponents(g);
    }

    boolean pintRuta = false;

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
