package com.hotel.backend.services.impl;

import com.hotel.backend.entites.Client;
import com.hotel.backend.entites.Visiteur;
import com.hotel.backend.repositories.ClientRepository;
import com.hotel.backend.services.VisiteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisiteurServiceImpl implements VisiteurService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client inscrireVisiteur(Visiteur visiteur, String email, String motDePasse) {
        Client client = new Client();
        client.setNom(visiteur.getNom() + " " + visiteur.getPrenom());
        client.setEmail(email);
        client.setMotDePasse(motDePasse);

        return clientRepository.save(client);
    }
}
