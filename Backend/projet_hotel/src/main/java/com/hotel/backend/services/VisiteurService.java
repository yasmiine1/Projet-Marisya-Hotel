package com.hotel.backend.services;

import com.hotel.backend.entites.Client;
import com.hotel.backend.entites.Visiteur;

public interface VisiteurService {
    Client inscrireVisiteur(Visiteur visiteur, String email, String motDePasse);
}
