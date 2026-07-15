package org.bkd.social_media_scheduler_api.oauth2.core.ports.in;


import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.UnsupportedPlatformException;

import java.util.UUID;

public interface InitiateOAuth2UseCase {

  String initiateOAuth2(Platform platform, UUID applicationId) throws UnsupportedPlatformException;
}
