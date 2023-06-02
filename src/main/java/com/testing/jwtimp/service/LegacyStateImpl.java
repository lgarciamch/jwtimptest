package com.testing.jwtimp.service;

import com.testing.jwtimp.model.StateCore;
import com.testing.jwtimp.repository.LegacyStateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LegacyStateImpl implements LegacyStateService{
    private final LegacyStateRepository legacyStateRepository;
    private final StateMapper mapper;

    @Override
    public List<StateCore> findAll() {
        return legacyStateRepository.findAll().stream().map(mapper::toApi).toList();
    }

    @Override
    public StateCore findByLegacyStateId(Long id) {
        return legacyStateRepository.findById(id)
                .map(mapper::toApi)
                .orElseThrow(() -> new RuntimeException("Unable to find state "+id));
    }
}
