package org.bkd.social_media_scheduler_api.oauth2.domains.profile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Profile {
  
  private String openId;

  private String displayName;

  private String avatarUrl;
}
