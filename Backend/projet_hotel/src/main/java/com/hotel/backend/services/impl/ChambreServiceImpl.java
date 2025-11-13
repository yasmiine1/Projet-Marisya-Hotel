
package com.hotel.backend.services.impl;

import com.hotel.backend.entites.Chambre;
import com.hotel.backend.repositories.ChambreRepository;
import com.hotel.backend.services.ChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ChambreServiceImpl implements ChambreService {

    @Autowired
    private ChambreRepository repository;

    @Override
    public boolean estDisponible(int chambreId, Date dateDebut, Date dateFin) {
        Optional<Chambre> chambreOpt = repository.findById(chambreId);
        if (chambreOpt.isPresent()) {
            Chambre chambre = chambreOpt.get();
            return chambre.estDisponible(dateDebut, dateFin);
        }
        return false;
    }
    @Override
    public Chambre findById(int id) {
        return repository.findById(id).orElse(null);
    }
    @Override
    public List<Chambre> getChambresDisponibles(Date dateDebut, Date dateFin) {
        List<Chambre> toutesLesChambres = repository.findAll();
        return toutesLesChambres.stream()
            .filter(c -> c.estDisponible(dateDebut, dateFin))
            .toList(); // Java 16+ ; sinon utiliser .collect(Collectors.toList())
    }


}
