package com.testing.jwtimp.repository;


import com.testing.jwtimp.persist.UsersApiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersApiRepository extends JpaRepository<UsersApiEntity, Long> {
    //Método para poder buscar un usuario mediante su nombre
    Optional<UsersApiEntity> findByUsername(String username);

    //Método para poder verificar si un usuario existe en nuestra base de datos
    Boolean existsByUsername(String username);
}
