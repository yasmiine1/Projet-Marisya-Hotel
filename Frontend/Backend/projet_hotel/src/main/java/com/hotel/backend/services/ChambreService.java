
package com.hotel.backend.services;

import com.hotel.backend.entites.Chambre;

import java.util.Date;
import java.util.List;

public interface ChambreService {
    boolean estDisponible(int chambreId, Date dateDebut, Date dateFin); // New
    Chambre findById(int id);
    List<Chambre> getChambresDisponibles(Date dateDebut, Date dateFin);

}
