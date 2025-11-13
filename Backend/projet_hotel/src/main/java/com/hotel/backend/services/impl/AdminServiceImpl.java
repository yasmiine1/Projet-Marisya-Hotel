
package com.hotel.backend.services.impl;

import com.hotel.backend.entites.Admin;
import com.hotel.backend.entites.Chambre;
import com.hotel.backend.entites.Client;
import com.hotel.backend.entites.Evenement;
import com.hotel.backend.entites.Offre;
import com.hotel.backend.entites.Reservation;
import com.hotel.backend.repositories.AdminRepository;
import com.hotel.backend.repositories.ChambreRepository;
import com.hotel.backend.repositories.ClientRepository;
import com.hotel.backend.repositories.EvenementRepository;
import com.hotel.backend.repositories.OffreRepository;
import com.hotel.backend.repositories.ReservationRepository;
import com.hotel.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public Admin login(String login, String motDePasse) {
        return adminRepository.findByLoginAndMotDePasse(login, motDePasse)
                .orElse(null);
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Optional<Admin> getAdminById(int id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public Admin updateAdmin(int id, Admin admin) {
        return adminRepository.findById(id).map(a -> {
            a.setNom(admin.getNom());
            a.setLogin(admin.getLogin());
            a.setMotDePasse(admin.getMotDePasse());
            return adminRepository.save(a);
        }).orElse(null);
    }

    @Override
    public void deleteAdmin(int id) {
        adminRepository.deleteById(id);
    }

    
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Chambre createChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    @Override
    public Chambre updateChambre(int id, Chambre chambre) {
        return chambreRepository.findById(id).map(c -> {
            c.setDesc(chambre.getDesc());
            c.setImages(chambre.getImages());
            c.setType(chambre.getType());
            c.setPrix(chambre.getPrix());
            c.setDisponibilite(chambre.isDisponibilite());
            return chambreRepository.save(c);
        }).orElse(null);
    }

    @Override
    public void deleteChambre(int id) {
        chambreRepository.deleteById(id);
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Offre createOffre(Offre offre) {
        return offreRepository.save(offre);
    }

    @Override
    public Offre updateOffre(long id, Offre offre) {
        return offreRepository.findById(id).map(o -> {
            o.setTitre(offre.getTitre());
            o.setDescription(offre.getDescription());
            o.setPrixReduit(offre.getPrixReduit());
            return offreRepository.save(o);
        }).orElse(null);
    }

    @Override
    public void deleteOffre(long id) {
        offreRepository.deleteById(id);
    }

    @Override
    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    @Override
    public Evenement createEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public Evenement updateEvenement(long id, Evenement evenement) {
        return evenementRepository.findById(id).map(e -> {
            e.setNom(evenement.getNom());
            e.setDate(evenement.getDate());
            e.setLieu(evenement.getLieu());
            return evenementRepository.save(e);
        }).orElse(null);
    }

    @Override
    public void deleteEvenement(long id) {
        evenementRepository.deleteById(id);
    }

    @Override
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }
}

