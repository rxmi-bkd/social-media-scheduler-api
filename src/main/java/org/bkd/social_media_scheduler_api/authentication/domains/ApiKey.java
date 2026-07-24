package org.bkd.social_media_scheduler_api.authentication.domains;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ApiKey {

  private UUID id;

  private Role role;

  private String value;
  
  private UUID applicationId;
}


