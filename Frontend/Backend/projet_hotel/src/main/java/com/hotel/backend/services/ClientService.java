package com.hotel.backend.services;

import com.hotel.backend.entites.Client;
import com.hotel.backend.entites.Paiement;
import com.hotel.backend.entites.Reservation;
import com.hotel.backend.entites.Avis;

import java.util.List;

public interface ClientService {

    Client login(String email, String motDePasse);

    List<Client> findAll();
    Client findById(Long id);
    Client createClient(Client client);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);

    Reservation reserver(Long clientId, Reservation reservation);
    List<Reservation> getReservationsByClient(Long clientId);

    Avis laisserAvis(Long clientId, Avis avis);
    List<Avis> getAvisByClient(Long clientId);
    Reservation modifierReservation(Long reservationId, Reservation newData);
    void annulerReservation(Long reservationId);
    Reservation effectuerPaiement(Long reservationId, Paiement paiement);

}

