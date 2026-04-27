package com.example.cine.controller;

import org.springframework.web.bind.annotation.*;

import com.example.cine.model.Pelicula;
import com.example.cine.service.PeliculaService;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> obtenerPeliculas() {

        List<EntityModel<Pelicula>> peliculas = peliculaService.obtenerTodas()
                .stream()
                .map(p -> EntityModel.of(p,
                        linkTo(methodOn(PeliculaController.class).obtenerPeliculaPorId(p.getId())).withSelfRel(),
                        linkTo(methodOn(PeliculaController.class).obtenerPeliculas()).withRel("peliculas")
                ))
                .toList();

        return CollectionModel.of(peliculas,
                linkTo(methodOn(PeliculaController.class).obtenerPeliculas()).withSelfRel());
    }

    @GetMapping("/{id}")
    public EntityModel<Pelicula> obtenerPeliculaPorId(@PathVariable Long id) {

        Pelicula pelicula = peliculaService.obtenerPorId(id).orElse(null);

        if (pelicula == null) {
            return null;
        }

        return EntityModel.of(pelicula,
                linkTo(methodOn(PeliculaController.class).obtenerPeliculaPorId(id)).withSelfRel(),
                linkTo(methodOn(PeliculaController.class).obtenerPeliculas()).withRel("peliculas"));
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
