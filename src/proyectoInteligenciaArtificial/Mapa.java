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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class Mapa {

    ArrayList<String> ruta = new ArrayList<>();
    HashMap<String, ArrayList<String>> mapa;
    HashMap<String, int[]> mapaCart;

    public Mapa() {
        mapa = new HashMap<>();
        mapaCart = new HashMap<>();
        agregar("G", new String[]{"A", "B"});
        agregar("A", new String[]{"C", "G", "E"});
        agregar("B", new String[]{"C", "G", "D"});
        agregar("C", new String[]{"D", "B", "A", "E"});
        agregar("D", new String[]{"F", "C", "B"});
        agregar("E", new String[]{"F", "A", "C"});
        agregar("F", new String[]{"D", "E"});
        System.out.println(mapa.toString());

        //ruta = busqB("G", "F");
        //System.out.println(ruta);
    }

    public Set<String> key() {
        Set<String> s = mapa.keySet();
        return s;

    }

    private void agregar(String key, String[] values) {
        if (!mapa.containsKey(key)) {
            mapa.put(key, new ArrayList<>());
            for (String d : values) {
                mapa.get(key).add(d);
            }
        }
    }

    public ArrayList<String> busqB(String ini, String fin) {
        ArrayList<String> resF = new ArrayList<>();
        ArrayList<String> resB = new ArrayList<>();
        ArrayList<String> openF = new ArrayList<>();
        ArrayList<String> openB = new ArrayList<>();
        openF.add(ini);
        openB.add(fin);
        buscar(openF, new ArrayList<>(), new ArrayList<>(), openB, resF, true, resB);
        Collections.reverse(resB);
        resF.addAll(resB);
        ruta = resF;
        return resF;
    }

    private boolean buscar(ArrayList<String> openB, ArrayList<String> closedB, ArrayList<String> closedF,
            ArrayList<String> openF, ArrayList<String> resF, boolean front, ArrayList<String> resB) {
        boolean find = false;
        if (!openB.isEmpty()) {
            String n = openB.remove(0);
            if (front) {
                resF.add(n);
            } else {
                resB.add(n);
            }
            closedB.add(n);
            for (String suc : mapa.get(n)) {
                if (!closedF.contains(suc)) {
                    if (!openB.contains(suc)) {
                        openB.add(suc);
                    }
                }
                if (openF.contains(suc)) {
                    resF.add(suc);
                    find = true;
                    break;
                }
            }
            front = !front;
            if (!find) {
                if (front) {
                    find = buscar(openF, closedF, closedB, openB, resF, front, resB);
                } else {
                    find = buscar(openB, closedB, closedF, openF, resB, front, resF);
                }
            }
            if (!find) {
                if (!front) {
                    resF.remove(resF.indexOf(n));
                } else {
                    resB.remove(resB.indexOf(n));
                }
            }

        }
        return find;
    }
}
