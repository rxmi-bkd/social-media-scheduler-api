package org.bkd.social_media_scheduler_api.application.adapters.out;

import org.bkd.social_media_scheduler_api.application.domain.Application;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationEntityMapper {

  ApplicationEntity toApplicationEntity(Application application);
}