
package com.hotel.backend.services;

import com.hotel.backend.entites.Avis;
import java.util.List;

public interface AvisService {
    List<Avis> findAll();
    Avis findById(Long id);
}
