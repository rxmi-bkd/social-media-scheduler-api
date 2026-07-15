package org.bkd.social_media_scheduler_api.oauth2.core.services;


import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;

import java.util.UUID;

public interface AuthorizationUrlBuilder {

  boolean supports(Platform platform);

  String buildAuthorizationUrl(UUID applicationId);
}
