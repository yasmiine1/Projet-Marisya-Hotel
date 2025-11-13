
package com.hotel.backend.entites;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Offre {
    @Id
    @GeneratedValue
    private Long id;
    private String titre;
    private String description;
    private float prixReduit;
	
	public Offre(Long id, String titre, String description, float prixReduit, List<Client> clients) {
		super();
		this.id = id;
		this.titre = titre;
		this.description = description;
		this.prixReduit = prixReduit;
		this.clients = clients;
	}
	@ManyToMany(mappedBy = "offres")
    private List<Client> clients;

	
	public Offre() {
		super();
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getPrixReduit() {
		return prixReduit;
	}
	public void setPrixReduit(float prixReduit) {
		this.prixReduit = prixReduit;
	}

	public List<Client> getClients() {
		return clients;
	}

	public void setClients(List<Client> clients) {
		this.clients = clients;
	}

  

    // Getters et setters
}
