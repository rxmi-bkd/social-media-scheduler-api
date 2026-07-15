package org.bkd.social_media_scheduler_api.oauth2.core.ports.out;


import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TokenRepository {

  Token save(Token token);

  void deleteByIdAndApplicationId(UUID tokenId, UUID applicationId);

  Optional<Token> findByIdAndApplicationId(UUID tokenId, UUID applicationId);

  List<Token> findAllByApplicationId(UUID applicationId);
}
