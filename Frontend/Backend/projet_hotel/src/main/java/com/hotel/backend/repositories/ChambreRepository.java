
package com.hotel.backend.repositories;

import com.hotel.backend.entites.Chambre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChambreRepository extends JpaRepository<Chambre, Integer> {
}
