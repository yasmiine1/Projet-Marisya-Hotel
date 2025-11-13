package com.hotel.backend.entites;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
public class Chambre {

    @Id
    @GeneratedValue
    private int numero;

    private String type;
    private float prix;

    @Column(name = "description")
    private String desc;

    @ElementCollection
    @CollectionTable(name = "chambre_images", joinColumns = @JoinColumn(name = "chambre_id"))
    @Column(name = "image_url")
    private List<String> images;

    private int capacite;

    private boolean disponibilite = true; // disponible par défaut

    @OneToMany(mappedBy = "chambre", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<DateReservation> dateReservations;

    // Constructeur par défaut
    public Chambre() {
        super();
    }

    // Constructeur avec paramètres
    public Chambre(String type, float prix, String desc, List<String> images, int capacite, boolean disponibilite, List<DateReservation> dateReservations) {
        this.type = type;
        this.prix = prix;
        this.desc = desc;
        this.images = images;
        this.capacite = capacite;
        this.disponibilite = disponibilite;
        this.dateReservations = dateReservations;
    }

    // Getters et setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public List<DateReservation> getDateReservations() {
        return dateReservations;
    }

    public void setDateReservations(List<DateReservation> dateReservations) {
        this.dateReservations = dateReservations;
    }

    // Méthode pour vérifier la disponibilité dans une plage de dates
    public boolean estDisponible(Date dateDebut, Date dateFin) {
        if (this.dateReservations == null) return true;

        for (DateReservation reservation : this.dateReservations) {
            boolean chevauchement = !(reservation.getDateFin().before(dateDebut) || reservation.getDateDebut().after(dateFin));
            if (chevauchement) {
                return false;
            }
        }
        return true;
    }
}
