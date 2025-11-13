package com.hotel.backend.controllers;

import com.hotel.backend.entites.Client;
import com.hotel.backend.entites.Paiement;
import com.hotel.backend.entites.Reservation;
import com.hotel.backend.entites.Avis;
import com.hotel.backend.services.ClientService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String motDePasse, HttpSession session) {
        Client client = clientService.login(email, motDePasse);
        if (client != null) {
            session.setAttribute("clientId", client.getId()); // Stocker dans la session
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou mot de passe incorrect");
        }
    }
    @GetMapping("/me")
    public ResponseEntity<?> getCurrentClient(HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
        System.out.println("ClientId from session: " + clientId); // Log pour vérifier le clientId
        if (clientId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Non connecté");
        }

        Client client = clientService.findById(clientId);
        if (client == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client introuvable");
        }

        return ResponseEntity.ok(client);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Déconnecté");
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.findById(id);
    }

    @PostMapping
    public Client createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable Long id, @RequestBody Client client) {
        return clientService.updateClient(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    @PostMapping("/{clientId}/reservations")
    public Reservation reserver(@PathVariable Long clientId, @RequestBody Reservation reservation) {
        return clientService.reserver(clientId, reservation);
    }

    @GetMapping("/{clientId}/reservations")
    public List<Reservation> getReservationsByClient(@PathVariable Long clientId) {
        return clientService.getReservationsByClient(clientId);
    }

    @PostMapping("/{clientId}/avis")
    public Avis laisserAvis(@PathVariable Long clientId, @RequestBody Avis avis) {
        return clientService.laisserAvis(clientId, avis);
    }

    @GetMapping("/{clientId}/avis")
    public List<Avis> getAvisByClient(@PathVariable Long clientId) {
        return clientService.getAvisByClient(clientId);
    }
    @PutMapping("/reservation/{reservationId}/modifier")
    public Reservation modifierReservation(@PathVariable Long reservationId, @RequestBody Reservation reservation) {
        return clientService.modifierReservation(reservationId, reservation);
    }

    // Annuler une réservation
    @PutMapping("/reservation/{reservationId}/annuler")
    public void annulerReservation(@PathVariable Long reservationId) {
        clientService.annulerReservation(reservationId);
    }

    // Payer une réservation
    @PostMapping("/reservation/{reservationId}/payer")
    public Reservation effectuerPaiement(@PathVariable Long reservationId, @RequestBody Paiement paiement) {
        return clientService.effectuerPaiement(reservationId, paiement);
    }
}
