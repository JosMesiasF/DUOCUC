package com.example.cine;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private List<Pelicula> peliculas = new ArrayList<>();

    public PeliculaController() {
        peliculas.add(new Pelicula(1L, "Titanic", 1997, "James Cameron", "Romance", "Una historia de amor en el famoso transatlántico."));
        peliculas.add(new Pelicula(2L, "Avatar", 2009, "James Cameron", "Ciencia ficción", "Un exmarine viaja a Pandora y se enfrenta a un conflicto entre humanos y nativos."));
        peliculas.add(new Pelicula(3L, "Inception", 2010, "Christopher Nolan", "Ciencia ficción", "Un ladrón entra en los sueños de las personas para robar secretos."));
        peliculas.add(new Pelicula(4L, "The Dark Knight", 2008, "Christopher Nolan", "Acción", "Batman enfrenta al Joker en Gotham."));
        peliculas.add(new Pelicula(5L, "Interstellar", 2014, "Christopher Nolan", "Ciencia ficción", "Un grupo de astronautas viaja por el espacio en busca de un nuevo hogar para la humanidad."));
    }

    @GetMapping
    public List<Pelicula> obtenerPeliculas() {
        return peliculas;
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPeliculaPorId(@PathVariable Long id) {
        for (Pelicula pelicula : peliculas) {
            if (pelicula.getId().equals(id)) {
                return pelicula;
            }
        }
        return null;
    }
}