package org.bkd.social_media_scheduler_api.oauth2.adapters.out.state;

import org.bkd.social_media_scheduler_api.oauth2.domains.state.State;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StateMapper {

  @Mapping(source = "application.id", target = "applicationId")
  State toState(StateEntity stateEntity);
}
