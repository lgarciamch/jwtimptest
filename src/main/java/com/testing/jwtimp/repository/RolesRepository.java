package com.testing.jwtimp.repository;


import com.testing.jwtimp.persist.RolesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity, Long> {
    //Método para buscar un role por su nombre en nuestra base de datos
    Optional<RolesEntity> findByName(String name);
}
