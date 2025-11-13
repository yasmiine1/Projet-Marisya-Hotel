
package com.hotel.backend.repositories;

import com.hotel.backend.entites.DateReservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateReservationRepository extends JpaRepository<DateReservation, Long> {
}
