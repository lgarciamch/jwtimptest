package com.testing.jwtimp.service;


import com.testing.jwtimp.persist.SmartPhoneEntity;
import com.testing.jwtimp.repository.SmartPhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SmartPhoneService {
    private final SmartPhoneRepository smartPhoneRepo;

    @Autowired
    public SmartPhoneService(SmartPhoneRepository smartPhoneRepo) {
        this.smartPhoneRepo = smartPhoneRepo;
    }

    //Creamos un celular
    public void crear(SmartPhoneEntity smartPhoneEntity) {
        smartPhoneRepo.save(smartPhoneEntity);
    }

    //Obtenemos toda una lista de celulares
    public List<SmartPhoneEntity> readAll() {
        return smartPhoneRepo.findAll();
    }

    //Obtenemos un celular por su id
    public Optional<SmartPhoneEntity> readOne(Long id) {
        return smartPhoneRepo.findById(id);
    }

    //Actualizamos un celular
    public void update(SmartPhoneEntity smartPhoneEntity) {
        smartPhoneRepo.save(smartPhoneEntity);
    }

    //Eliminamos un celular
    public void delete(Long id) {
        smartPhoneRepo.deleteById(id);
    }
}
