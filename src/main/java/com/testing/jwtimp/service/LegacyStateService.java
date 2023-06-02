package com.testing.jwtimp.service;

import com.testing.jwtimp.model.StateCore;

import java.util.List;

public interface LegacyStateService {
    public List<StateCore> findAll();
    public StateCore findByLegacyStateId(Long id);
}
