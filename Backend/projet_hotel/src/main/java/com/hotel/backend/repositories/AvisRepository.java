
package com.hotel.backend.repositories;

import com.hotel.backend.entites.Avis;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AvisRepository extends JpaRepository<Avis, Long> {

	List<Avis> findByClientId(Long clientId);
}
