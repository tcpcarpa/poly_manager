package es.polytex.integracionback.limit.mapper;

import es.polytex.integracionback.core.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;

public class LimitMaper extends Mapper {
    public static List<String[]> soloMayusculas(List<String[]> listaOriginal) {
        List<String[]> listaMayusculas = new ArrayList<>();

        for (String[] fila : listaOriginal) {
            if (isMayusculas(fila[0])) {
                listaMayusculas.add(fila);
            }
        }
        return listaMayusculas;
    }
    public static boolean isMayusculas(String str) { return str.equals(str.toUpperCase());}

}
