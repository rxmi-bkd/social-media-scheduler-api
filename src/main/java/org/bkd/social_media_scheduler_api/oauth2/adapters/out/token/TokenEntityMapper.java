package org.bkd.social_media_scheduler_api.oauth2.adapters.out.token;

import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TokenEntityMapper {

  @Mapping(source = "applicationId", target = "application.id")
  TokenEntity toTokenEntity(Token token);
}
