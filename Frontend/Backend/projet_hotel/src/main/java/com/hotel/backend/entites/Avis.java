
package com.hotel.backend.entites;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Avis {
    @Id
    @GeneratedValue
    private Long id;
    private String contenu;
    private int note;
    @Column(name = "date_avis")

    private Date date_avis;
    private String etat;
	
	public Avis(Long id, String contenu, int note, Date date_avis, String etat, Client client) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.note = note;
		this.date_avis = date_avis;
		this.etat = etat;
		this.client = client;
	}
	@ManyToOne
    private Client client;
	public Avis() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public int getNote() {
		return note;
	}
	public void setNote(int note) {
		this.note = note;
	}
	public Date getDate() {
		return date_avis;
	}
	public void setDate(Date date) {
		this.date_avis = date;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Date getDate_avis() {
		return date_avis;
	}
	public void setDate_avis(Date date_avis) {
		this.date_avis = date_avis;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}

   

    // Getters et setters
}
