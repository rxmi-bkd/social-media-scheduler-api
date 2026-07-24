package org.bkd.social_media_scheduler_api.authentication.adapters.out;

import org.bkd.social_media_scheduler_api.authentication.domains.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyMapper {

  @Mapping(source = "application.id", target = "applicationId")
  ApiKey toApiKey(ApiKeyEntity apiKeyEntity);

  List<ApiKey> toApiKeys(List<ApiKeyEntity> apiKeyEntities);
}
