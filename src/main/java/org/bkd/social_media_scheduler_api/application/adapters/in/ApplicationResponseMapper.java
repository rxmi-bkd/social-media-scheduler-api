package org.bkd.social_media_scheduler_api.application.adapters.in;

import org.bkd.social_media_scheduler_api.application.domain.Application;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationResponseMapper {

  ApplicationResponse toApplicationResponse(Application application);

  List<ApplicationResponse> toApplicationResponses(List<Application> applications);
}
