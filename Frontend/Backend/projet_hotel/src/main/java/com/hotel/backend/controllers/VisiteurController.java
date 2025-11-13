package com.hotel.backend.controllers;

import com.hotel.backend.entites.Visiteur;
import com.hotel.backend.entites.Client;
import com.hotel.backend.services.VisiteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visiteurs")
public class VisiteurController {

    @Autowired
    private VisiteurService visiteurService;

    @PostMapping("/inscription")
    public Client inscription(@RequestBody VisiteurInscriptionRequest request) {
        return visiteurService.inscrireVisiteur(request.getVisiteur(), request.getEmail(), request.getMotDePasse());
    }

    // Classe interne ou fichier séparé pour transporter les données
    public static class VisiteurInscriptionRequest {
        private Visiteur visiteur;
        private String email;
        private String motDePasse;

        public Visiteur getVisiteur() {
            return visiteur;
        }

        public void setVisiteur(Visiteur visiteur) {
            this.visiteur = visiteur;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMotDePasse() {
            return motDePasse;
        }

        public void setMotDePasse(String motDePasse) {
            this.motDePasse = motDePasse;
        }
    }
}
