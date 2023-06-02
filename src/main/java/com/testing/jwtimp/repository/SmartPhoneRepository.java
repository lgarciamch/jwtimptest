package com.testing.jwtimp.repository;


import com.testing.jwtimp.persist.SmartPhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmartPhoneRepository extends JpaRepository<SmartPhoneEntity, Long> {
}
