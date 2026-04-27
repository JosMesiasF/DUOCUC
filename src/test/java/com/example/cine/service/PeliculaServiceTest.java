package com.example.cine.service;

import com.example.cine.model.Pelicula;
import com.example.cine.repository.PeliculaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaServiceTest {

    @Test
    void testObtenerPeliculas() {

        PeliculaRepository repo = Mockito.mock(PeliculaRepository.class);

        Mockito.when(repo.findAll()).thenReturn(List.of(
                new Pelicula(1L, "Titanic", 1997, "James Cameron", "Romance", "Test")
        ));

        PeliculaServiceImpl service = new PeliculaServiceImpl(repo);

        List<Pelicula> resultado = service.obtenerTodas();

        assertEquals(1, resultado.size());
        assertEquals("Titanic", resultado.get(0).getTitulo());
    }
}