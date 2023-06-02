package com.testing.jwtimp.controllers;

import com.testing.jwtimp.model.StateCore;
import com.testing.jwtimp.service.LegacyStateImpl;
import com.testing.jwtimp.service.LegacyStateService;
import com.testing.jwtimp.service.StateService;
import com.testing.jwtimp.service.StateSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value="/api")
public class StateSyncController implements StateSyncService {
    private final LegacyStateService legacyStateService;
    private final LegacyStateImpl legacyStateImpl;
    @CrossOrigin(origins = "http://localhost:4200")//only for dev consuming angular
    @GetMapping("/states/all")
    public Flux<StateCore> getAll() {
        List<StateCore> stateList = legacyStateImpl.findAll();
        return Flux.fromIterable(stateList);
    }

    @GetMapping("/states/{id}")
    public Mono<StateCore> getOne(@PathVariable Long id){
        return  Mono.defer(() -> Mono.just(legacyStateImpl.findByLegacyStateId(id)));
    }
}
