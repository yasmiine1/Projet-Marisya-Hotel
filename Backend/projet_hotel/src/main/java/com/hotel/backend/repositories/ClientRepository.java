
package com.hotel.backend.repositories;

import com.hotel.backend.entites.Client;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

	Optional<Client> findByEmailAndMotDePasse(String email, String motDePasse);
}
