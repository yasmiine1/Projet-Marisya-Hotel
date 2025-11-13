package com.hotel.backend.controllers;

import com.hotel.backend.entites.*;
import com.hotel.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public Admin login(@RequestParam String login, @RequestParam String motDePasse) {
        return adminService.login(login, motDePasse);
    }

    @GetMapping
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping("/{id}")
    public Optional<Admin> getAdminById(@PathVariable int id) {
        return adminService.getAdminById(id);
    }

    @PostMapping
    public Admin createAdmin(@RequestBody Admin admin) {
        return adminService.createAdmin(admin);
    }

    @PutMapping("/{id}")
    public Admin updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        return adminService.updateAdmin(id, admin);
    }

    @DeleteMapping("/{id}")
    public void deleteAdmin(@PathVariable int id) {
        adminService.deleteAdmin(id);
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return adminService.getAllClients();
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        return adminService.getAllReservations();
    }

    @PostMapping("/chambres")
    public Chambre createChambre(@RequestBody Chambre chambre) {
        return adminService.createChambre(chambre);
    }

    @PutMapping("/chambres/{id}")
    public Chambre updateChambre(@PathVariable int id, @RequestBody Chambre chambre) {
        return adminService.updateChambre(id, chambre);
    }

    @DeleteMapping("/chambres/{id}")
    public void deleteChambre(@PathVariable int id) {
        adminService.deleteChambre(id);
    }

    @GetMapping("/chambres")
    public List<Chambre> getAllChambres() {
        return adminService.getAllChambres();
    }

    @PostMapping("/offres")
    public Offre createOffre(@RequestBody Offre offre) {
        return adminService.createOffre(offre);
    }

    @PutMapping("/offres/{id}")
    public Offre updateOffre(@PathVariable long id, @RequestBody Offre offre) {
        return adminService.updateOffre(id, offre);
    }

    @DeleteMapping("/offres/{id}")
    public void deleteOffre(@PathVariable long id) {
        adminService.deleteOffre(id);
    }

    @GetMapping("/offres")
    public List<Offre> getAllOffres() {
        return adminService.getAllOffres();
    }

    @PostMapping("/evenements")
    public Evenement createEvenement(@RequestBody Evenement evenement) {
        return adminService.createEvenement(evenement);
    }

    @PutMapping("/evenements/{id}")
    public Evenement updateEvenement(@PathVariable long id, @RequestBody Evenement evenement) {
        return adminService.updateEvenement(id, evenement);
    }

    @DeleteMapping("/evenements/{id}")
    public void deleteEvenement(@PathVariable long id) {
        adminService.deleteEvenement(id);
    }

    @GetMapping("/evenements")
    public List<Evenement> getAllEvenements() {
        return adminService.getAllEvenements();
    }
}
