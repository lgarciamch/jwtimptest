package com.testing.jwtimp.service;

import com.testing.jwtimp.model.StateCore;
import com.testing.jwtimp.persist.LegacyStateEntity;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StateMapper {
    @Mapping(target = "stateId", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LegacyStateEntity fromApi(@MappingTarget LegacyStateEntity original, StateCore apiState);

    StateCore toApi(LegacyStateEntity entityState);
}
