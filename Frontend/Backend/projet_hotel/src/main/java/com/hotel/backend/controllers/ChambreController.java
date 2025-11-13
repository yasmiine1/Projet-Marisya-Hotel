
package com.hotel.backend.controllers;

import com.hotel.backend.entites.Chambre;
import com.hotel.backend.services.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/chambres")
public class ChambreController {

    @Autowired
    private ChambreService service;

    

    @GetMapping("/{id}")
    public Chambre getById(@PathVariable int id) {
        return service.findById(id);
    }
    @GetMapping("/{id}/disponible")
    public ResponseEntity<Boolean> estDisponible(
            @PathVariable int id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin) {

        boolean disponible = service.estDisponible(id, dateDebut, dateFin);
        return ResponseEntity.ok(disponible);
    }
    @GetMapping("/disponibles")
    public List<Chambre> getChambresDisponibles(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDebut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin) {

        return service.getChambresDisponibles(dateDebut, dateFin);
    }

}
