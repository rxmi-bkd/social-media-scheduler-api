package org.bkd.social_media_scheduler_api.oauth2.domains.token;

import lombok.Getter;
import lombok.Setter;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Token {

  private UUID id;

  private String openId;

  private String scope;

  private String accessToken;

  private String refreshToken;

  private Instant accessTokenExpiresAt;

  private Instant refreshTokenExpiresAt;

  private String tokenType;

  private Platform platform;

  private Instant createdAt;

  private UUID applicationId;

  /**
   * true if access token is still valid else false
   */
  public boolean canBeUsed() {
    return Instant.now().isBefore(accessTokenExpiresAt);
  }

  /**
   * true if refresh token is still valid else false
   */
  public boolean canBeRefreshed() {
    return Instant.now().isBefore(refreshTokenExpiresAt);
  }
}
