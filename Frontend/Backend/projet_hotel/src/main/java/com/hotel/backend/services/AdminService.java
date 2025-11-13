
package com.hotel.backend.services;

import com.hotel.backend.entites.Admin;
import com.hotel.backend.entites.Chambre;
import com.hotel.backend.entites.Client;
import com.hotel.backend.entites.Evenement;
import com.hotel.backend.entites.Offre;
import com.hotel.backend.entites.Reservation;

import java.util.List;

import java.util.Optional;

public interface AdminService {

	Admin login(String login, String motDePasse);

    List<Admin> getAllAdmins();
    Optional<Admin> getAdminById(int id);
    Admin createAdmin(Admin admin);
    Admin updateAdmin(int id, Admin admin);
    void deleteAdmin(int id);

    List<Client> getAllClients();
    List<Reservation> getAllReservations();

    Chambre createChambre(Chambre chambre);
    Chambre updateChambre(int id, Chambre chambre);
    void deleteChambre(int id);
    List<Chambre> getAllChambres();

    Offre createOffre(Offre offre);
    Offre updateOffre(long id, Offre offre);
    void deleteOffre(long id);
    List<Offre> getAllOffres();

    Evenement createEvenement(Evenement evenement);
    Evenement updateEvenement(long id, Evenement evenement);
    void deleteEvenement(long id);
    List<Evenement> getAllEvenements();
}
