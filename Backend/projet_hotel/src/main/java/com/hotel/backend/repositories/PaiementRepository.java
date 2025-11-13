
package com.hotel.backend.repositories;

import com.hotel.backend.entites.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
}
