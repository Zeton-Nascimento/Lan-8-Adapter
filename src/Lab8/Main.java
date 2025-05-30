package Lab8;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        try {
            List<String> lista = new ArrayList<>();
            Map<Integer, String> mapAdaptado = new ListToMapAdapter<>(lista);
            mapAdaptado.put(0, "Ana");
            mapAdaptado.put(1, "Bruno");
            System.out.println("Map via List: " + mapAdaptado.values());

            Map<Integer, String> mapa = new LinkedHashMap<>();
            List<String> listaAdaptada = new MapToListAdapter<>(mapa);
            listaAdaptada.add("Carlos");
            listaAdaptada.add("Diana");
            System.out.println("List via Map: " + Arrays.toString(listaAdaptada.toArray()));

            // Exemplos de exceções
            // mapAdaptado.get(5); // lança IndexOutOfBoundsException
            // listaAdaptada.get(3); // lança IndexOutOfBoundsException
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
