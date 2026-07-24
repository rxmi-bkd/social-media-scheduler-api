package org.bkd.social_media_scheduler_api.oauth2.domains.state;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class State {

  public final static String STATE_SEPARATOR = "~";

  private UUID id;
  private String value;
  private Instant createdAt;
  private UUID applicationId;

  /**
   * true if state is younger than 5 mins else false
   */
  public boolean isExpired() {
    Instant now = Instant.now();
    Instant expiresAt = createdAt.plusSeconds(300); // 5 mins
    return expiresAt.isBefore(now);
  }
}
