package org.bkd.social_media_scheduler_api.oauth2.core.ports.out;


import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.TokenExchangeException;

import java.util.UUID;

public interface TokenManager {

  boolean supports(Platform platform);

  Token exchangeCodeForToken(String code, UUID applicationId) throws TokenExchangeException;

  Token refreshToken(String refreshToken, UUID applicationId) throws TokenExchangeException;
}
