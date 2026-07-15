package org.bkd.social_media_scheduler_api.oauth2.adapters.out.state;

import org.bkd.social_media_scheduler_api.oauth2.domains.state.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StateEntityMapper {

  @Mapping(source = "applicationId", target = "application.id")
  StateEntity toStateEntity(State state);
}
