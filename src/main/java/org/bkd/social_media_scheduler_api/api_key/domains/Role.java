package org.bkd.social_media_scheduler_api.api_key.domains;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

  ADMIN(2),
  USER(1);

  private final int weight;
}