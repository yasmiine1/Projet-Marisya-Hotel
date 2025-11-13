package com.hotel.backend.services.impl;

import com.hotel.backend.entites.Client;
import com.hotel.backend.entites.Paiement;
import com.hotel.backend.entites.Reservation;
import com.hotel.backend.entites.Avis;
import com.hotel.backend.repositories.ClientRepository;
import com.hotel.backend.repositories.ReservationRepository;
import com.hotel.backend.repositories.AvisRepository;
import com.hotel.backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private AvisRepository avisRepository;

    @Override
    public Client login(String email, String motDePasse) {
        return clientRepository.findByEmailAndMotDePasse(email, motDePasse).orElse(null);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Long id, Client client) {
        return clientRepository.findById(id).map(c -> {
            c.setNom(client.getNom());
            c.setEmail(client.getEmail());
            c.setMotDePasse(client.getMotDePasse());
            return clientRepository.save(c);
        }).orElse(null);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Reservation reserver(Long clientId, Reservation reservation) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            reservation.setClient(client);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    @Override
    public List<Reservation> getReservationsByClient(Long clientId) {
        return reservationRepository.findByClientId(clientId);
    }

    @Override
    public Avis laisserAvis(Long clientId, Avis avis) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if (client != null) {
            avis.setClient(client);
            return avisRepository.save(avis);
        }
        return null;
    }

    @Override
    public List<Avis> getAvisByClient(Long clientId) {
        return avisRepository.findByClientId(clientId);
    }
    @Override
    public Reservation modifierReservation(Long reservationId, Reservation newData) {
        return reservationRepository.findById(reservationId).map(res -> {
            res.setStatut(newData.getStatut());
            res.setTypeReservation(newData.getTypeReservation());
            // Tu peux aussi modifier les dates ou la chambre si besoin
            return reservationRepository.save(res);
        }).orElse(null);
    }

    @Override
    public void annulerReservation(Long reservationId) {
        reservationRepository.findById(reservationId).ifPresent(res -> {
            res.setStatut("Annulée"); // ou un Enum pour plus de propreté
            reservationRepository.save(res);
        });
    }

    @Override
    public Reservation effectuerPaiement(Long reservationId, Paiement paiement) {
        return reservationRepository.findById(reservationId).map(res -> {
            paiement.setReservation(res);
            res.setPaiement(paiement); // si relation OneToOne
            // ou paiementRepository.save(paiement) si relation externe
            return reservationRepository.save(res);
        }).orElse(null);
    }

}
