package org.bkd.social_media_scheduler_api.oauth2.adapters.out.token;


import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.TokenRepository;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
@RequiredArgsConstructor
public class TokenSqlRepository implements TokenRepository {

  private final TokenMapper tokenMapper;
  private final TokenEntityMapper tokenEntityMapper;
  private final TokenJpaRepository tokenJpaRepository;

  @Override
  public Token save(Token token) {
    TokenEntity newTokenEntity = tokenEntityMapper.toTokenEntity(token);
    newTokenEntity = tokenJpaRepository.save(newTokenEntity);
    return tokenMapper.toToken(newTokenEntity);
  }

  @Override
  public void deleteByIdAndApplicationId(UUID tokenId, UUID applicationId) {
    tokenJpaRepository.deleteByIdAndApplicationId(tokenId, applicationId);
  }

  @Override
  public Optional<Token> findByIdAndApplicationId(UUID tokenId, UUID applicationId) {
    return tokenJpaRepository.findByIdAndApplicationId(tokenId, applicationId).map(tokenMapper::toToken);
  }

  @Override
  public List<Token> findAllByApplicationId(UUID applicationId) {
    List<TokenEntity> oAuthTokenEntities = tokenJpaRepository.findAllByApplicationId(applicationId);
    return tokenMapper.toTokens(oAuthTokenEntities);
  }
}
