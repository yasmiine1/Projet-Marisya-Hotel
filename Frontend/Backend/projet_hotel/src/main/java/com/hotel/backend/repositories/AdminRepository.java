
package com.hotel.backend.repositories;

import com.hotel.backend.entites.Admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

	Optional<Admin> findByLoginAndMotDePasse(String login, String motDePasse);
}
