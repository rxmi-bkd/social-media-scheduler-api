package org.bkd.social_media_scheduler_api.authentication.adapters.in;

import org.bkd.social_media_scheduler_api.authentication.domains.ApiKey;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApiKeyResponseMapper {

  ApiKeyResponse toApiKeyResponse(ApiKey apiKey);

  List<ApiKeyResponse> toApiKeyResponses(List<ApiKey> apiKeys);
}
