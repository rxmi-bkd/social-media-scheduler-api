package org.bkd.social_media_scheduler_api.oauth2.core.ports.out;

import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.profile.Profile;
import org.bkd.social_media_scheduler_api.oauth2.domains.profile.ProfileRetrievalException;

public interface ProfileService {

  boolean supports(Platform platform);

  Profile getProfile(String accessToken) throws ProfileRetrievalException;
}
