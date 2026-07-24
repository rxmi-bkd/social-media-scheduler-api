package org.bkd.social_media_scheduler_api.oauth2.adapters.out.token_managers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TiktokTokenResponse {

  @JsonProperty("open_id")
  private String openId;

  @JsonProperty("scope")
  private String scope;

  @JsonProperty("access_token")
  private String accessToken;

  @JsonProperty("refresh_token")
  private String refreshToken;

  @JsonProperty("expires_in")
  private Integer expiresIn;

  @JsonProperty("refresh_expires_in")
  private Integer refreshExpiresIn;

  @JsonProperty("token_type")
  private String tokenType;

  @JsonProperty("error")
  private String error;

  @JsonProperty("error_description")
  private String errorDescription;
}
