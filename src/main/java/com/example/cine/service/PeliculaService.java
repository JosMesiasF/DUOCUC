package com.example.cine.service;

import com.example.cine.model.Pelicula;

import java.util.List;
import java.util.Optional;

public interface PeliculaService {

    List<Pelicula> obtenerTodas();

    Optional<Pelicula> obtenerPorId(Long id);

    Pelicula guardar(Pelicula pelicula);

    void eliminar(Long id);
}