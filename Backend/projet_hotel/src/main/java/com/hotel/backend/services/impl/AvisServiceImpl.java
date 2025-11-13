
package com.hotel.backend.services.impl;

import com.hotel.backend.entites.Avis;
import com.hotel.backend.repositories.AvisRepository;
import com.hotel.backend.services.AvisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvisServiceImpl implements AvisService {

    @Autowired
    private AvisRepository repository;

    @Override
    public List<Avis> findAll() {
        return repository.findAll();
    }

    @Override
    public Avis findById(Long id) {
        return repository.findById(id).orElse(null);
    }
}
