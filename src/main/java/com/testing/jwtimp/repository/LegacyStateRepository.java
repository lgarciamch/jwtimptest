package com.testing.jwtimp.repository;

import com.testing.jwtimp.persist.LegacyStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LegacyStateRepository  extends JpaRepository<LegacyStateEntity, Long> {

}
