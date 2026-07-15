package org.bkd.social_media_scheduler_api.oauth2.domains.state;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class State {

  private UUID id;

  private String value;

  private Instant createdAt;

  private UUID applicationId;

  public State(String value, UUID applicationId) {
    this.id = UUID.randomUUID();
    this.value = value;
    this.createdAt = Instant.now();
    this.applicationId = applicationId;
  }

  /**
   * true if state is younger than 5 mins else false
   */
  public boolean isExpired() {
    Instant now = Instant.now();
    Instant expiresAt = createdAt.plusSeconds(300); // 5 mins
    return expiresAt.isBefore(now);
  }
}
