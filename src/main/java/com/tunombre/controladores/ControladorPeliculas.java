package com.tunombre.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/peliculas")
public class ControladorPeliculas {
    private static HashMap<String, String> listaPeliculas = new HashMap<String, String>();

    public ControladorPeliculas() {
        listaPeliculas.put("Winnie the Pooh", "Don Hall");
        listaPeliculas.put("El zorro y el sabueso", "Ted Berman");
        listaPeliculas.put("Tarzán", "Kevin Lima");
        listaPeliculas.put("Mulán", "Barry Cook");
        listaPeliculas.put("Oliver", "Kevin Lima");
        listaPeliculas.put("Big Hero 6", "Don Hall");
    }

    public String obtenerTodasLasPeliculas() {
        StringBuilder resultado = new StringBuilder();
        for (String pelicula : listaPeliculas.keySet()) {
            resultado.append("Película: ").append(pelicula)
                     .append(", Director: ").append(listaPeliculas.get(pelicula))
                     .append("\n");
        }
        return resultado.toString();
    }

    @GetMapping("/{nombre}")
    public String obtenerPeliculaPorNombre(@PathVariable String nombre) {
        String director = listaPeliculas.get(nombre);
        if (director != null) {
            return "Película: " + nombre + ", Director: " + director;
        } else {
            return "La película no se encuentra en nuestra lista.";
        }
    }
    
    @GetMapping("/director/{nombre}")
    public String obtenerPeliculasPorDirector(@PathVariable String nombre) {
        StringBuilder resultado = new StringBuilder();
        boolean encontrado = false;
        
        for (HashMap.Entry<String, String> entry : listaPeliculas.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(nombre)) {
                resultado.append("Película: ").append(entry.getKey()).append("\n");
                encontrado = true;
            }
        }
        
        if (encontrado) {
            return resultado.toString();
        } else {
            return "No contamos con películas con ese director en nuestra lista.";
        }
    }

}

