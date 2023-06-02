package com.testing.jwtimp.service;

import com.testing.jwtimp.persist.StateEntity;
import com.testing.jwtimp.repository.StateRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class StateService {
    private StateRepository stateRepository;

    public StateService(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    public Flux<StateEntity> readAll() {
        return Flux.fromIterable(stateRepository.findAll());
    }
}
