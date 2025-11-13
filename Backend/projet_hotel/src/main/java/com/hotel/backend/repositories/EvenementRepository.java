
package com.hotel.backend.repositories;

import com.hotel.backend.entites.Evenement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvenementRepository extends JpaRepository<Evenement, Long> {
}
