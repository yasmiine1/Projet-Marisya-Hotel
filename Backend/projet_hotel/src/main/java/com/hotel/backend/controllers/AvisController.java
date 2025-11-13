
package com.hotel.backend.controllers;

import com.hotel.backend.entites.Avis;
import com.hotel.backend.services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aviss")
public class AvisController {

    @Autowired
    private AvisService service;

    @GetMapping
    public List<Avis> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Avis getById(@PathVariable Long id) {
        return service.findById(id);
    }
}
