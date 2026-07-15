package org.bkd.social_media_scheduler_api.api_key.adapters.out;

import org.bkd.social_media_scheduler_api.api_key.domains.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyEntityMapper {

  @Mapping(source = "applicationId", target = "application.id")
  ApiKeyEntity toApiKeyEntity(ApiKey apiKey);
}
