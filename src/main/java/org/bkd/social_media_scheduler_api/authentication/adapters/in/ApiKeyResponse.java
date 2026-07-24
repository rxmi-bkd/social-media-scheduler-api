package org.bkd.social_media_scheduler_api.authentication.adapters.in;

import lombok.Getter;
import lombok.Setter;
import org.bkd.social_media_scheduler_api.authentication.domains.Role;

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
