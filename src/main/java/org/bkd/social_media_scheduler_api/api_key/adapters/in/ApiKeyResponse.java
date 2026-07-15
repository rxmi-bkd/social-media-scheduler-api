package org.bkd.social_media_scheduler_api.api_key.adapters.in;

import lombok.Getter;
import lombok.Setter;
import org.bkd.social_media_scheduler_api.api_key.domains.Role;

import java.util.UUID;

@Getter
@Setter
public class ApiKeyResponse {

  private UUID id;

  private Role role;

  private String value;

  private String description;

  private UUID applicationId;
}
