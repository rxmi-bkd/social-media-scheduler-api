package org.bkd.social_media_scheduler_api.oauth2.adapters.out.profile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TiktokProfileResponse {

  @JsonProperty("open_id")
  private String openId;

  @JsonProperty("display_name")
  private String displayName;

  @JsonProperty("avatar_url")
  private String avatarUrl;
}
