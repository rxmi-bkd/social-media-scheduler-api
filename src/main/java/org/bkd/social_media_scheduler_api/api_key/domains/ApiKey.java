package org.bkd.social_media_scheduler_api.api_key.domains;

import lombok.Getter;
import lombok.Setter;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;

@Getter
@Setter
public class ApiKey {

  private UUID id;

  private Role role;

  private String value;

  private UUID applicationId;

  public ApiKey(UUID applicationId) {
    this.id = UUID.randomUUID();
    this.role = Role.USER;

    byte[] randomBytes = new byte[32];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(randomBytes);
    this.value = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);

    this.applicationId = applicationId;
  }
}
