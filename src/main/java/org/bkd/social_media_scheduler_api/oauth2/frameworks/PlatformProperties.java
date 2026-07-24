package org.bkd.social_media_scheduler_api.oauth2.frameworks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "oauth2")
public class PlatformProperties {

  private final Platform tiktok;
}
