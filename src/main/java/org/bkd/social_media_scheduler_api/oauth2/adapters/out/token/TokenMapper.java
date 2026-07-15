package org.bkd.social_media_scheduler_api.oauth2.adapters.out.token;

import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TokenMapper {

  @Mapping(source = "application.id", target = "applicationId")
  Token toToken(TokenEntity tokenEntity);

  List<Token> toTokens(List<TokenEntity> tokenEntities);
}
