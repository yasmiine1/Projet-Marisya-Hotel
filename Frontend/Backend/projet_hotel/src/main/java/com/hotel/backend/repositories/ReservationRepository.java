
package com.hotel.backend.repositories;

import com.hotel.backend.entites.Reservation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	List<Reservation> findByClientId(Long clientId);
}
