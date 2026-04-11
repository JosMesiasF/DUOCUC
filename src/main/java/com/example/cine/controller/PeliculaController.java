package com.example.cine.controller;

import org.springframework.web.bind.annotation.*;

import com.example.cine.model.Pelicula;
import com.example.cine.service.PeliculaService;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public List<Pelicula> obtenerPeliculas() {
        return peliculaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Pelicula obtenerPeliculaPorId(@PathVariable Long id) {
        return peliculaService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Pelicula crearPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.guardar(pelicula);
    }

    @PutMapping("/{id}")
    public Pelicula actualizarPelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        Pelicula existente = peliculaService.obtenerPorId(id).orElse(null);

        if (existente == null) {
            return null;
        }

        existente.setTitulo(pelicula.getTitulo());
        existente.setAnio(pelicula.getAnio());
        existente.setDirector(pelicula.getDirector());
        existente.setGenero(pelicula.getGenero());
        existente.setSinopsis(pelicula.getSinopsis());

        return peliculaService.guardar(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminarPelicula(@PathVariable Long id) {
        peliculaService.eliminar(id);
    }
}
