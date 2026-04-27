package com.example.cine.controller;

import com.example.cine.model.Pelicula;
import com.example.cine.service.PeliculaService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PeliculaControllerTest {

    @Test
    void testObtenerPeliculas() {

        PeliculaService service = Mockito.mock(PeliculaService.class);

        Mockito.when(service.obtenerTodas()).thenReturn(List.of(
                new Pelicula(1L, "Avatar", 2009, "James Cameron", "Sci-Fi", "Test")
        ));

        PeliculaController controller = new PeliculaController(service);

        var resultado = controller.obtenerPeliculas();

        assertNotNull(resultado);
    }
}