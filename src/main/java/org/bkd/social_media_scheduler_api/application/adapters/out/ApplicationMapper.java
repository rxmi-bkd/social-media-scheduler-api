package org.bkd.social_media_scheduler_api.application.adapters.out;

import org.bkd.social_media_scheduler_api.application.domain.Application;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper {

  Application toApplication(ApplicationEntity applicationEntity);

  List<Application> toApplications(List<ApplicationEntity> applicationEntities);
}