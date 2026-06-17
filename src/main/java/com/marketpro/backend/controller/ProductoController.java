package com.marketpro.backend.controller;

import com.marketpro.backend.model.Producto;
import com.marketpro.backend.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:5173")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listar();
    }

    // ✅ CREAR PRODUCTO CON CATEGORÍA
    @PostMapping("/{categoriaId}")
    public Producto crear(@RequestBody Producto producto,
                          @PathVariable Long categoriaId) {
        return service.guardar(producto, categoriaId);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}