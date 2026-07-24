package org.bkd.social_media_scheduler_api.oauth2.adapters.out.profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bkd.social_media_scheduler_api.oauth2.core.ports.out.ProfileService;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.profile.Profile;
import org.bkd.social_media_scheduler_api.oauth2.domains.profile.ProfileRetrievalException;
import org.bkd.social_media_scheduler_api.oauth2.frameworks.PlatformProperties;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
@RequiredArgsConstructor
public class TiktokProfileService implements ProfileService {

  private final static RestClient restClient = RestClient.create();

  private final PlatformProperties platformProperties;

  @Override
  public boolean supports(Platform platform) {
    return platform == Platform.tiktok;
  }

  @Override
  public Profile getProfile(String accessToken) throws ProfileRetrievalException {
    try {
      MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
      body.add("fields", "fields=display_name,avatar_url");
      return performRequest(body, accessToken);
    } catch (Exception e) {
      String errorMsg = "An error occurred while trying to retrieve the profile information from TikTok";
      log.error(errorMsg, e);
      throw new ProfileRetrievalException(errorMsg);
    }
  }

  private Profile performRequest(MultiValueMap<String, String> body, String accessToken) {
    TiktokProfileResponse response = restClient.get()
                                               .uri(uriBuilder -> uriBuilder.scheme("https")
                                                                            .host(platformProperties.getTiktok()
                                                                                                    .userInfoUri()
                                                                                                    .substring(
                                                                                                        "https://".length()))
                                                                            .queryParam("fields",
                                                                                        "display_name,avatar_url")
                                                                            .build())
                                               .header("Authorization", "Bearer " + accessToken)
                                               .retrieve()
                                               .body(TiktokProfileResponse.class);

    return new Profile(response.getOpenId(), response.getDisplayName(), response.getAvatarUrl());
  }
}
