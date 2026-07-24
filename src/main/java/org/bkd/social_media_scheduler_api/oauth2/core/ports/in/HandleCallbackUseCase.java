package org.bkd.social_media_scheduler_api.oauth2.core.ports.in;


import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.UnsupportedPlatformException;
import org.bkd.social_media_scheduler_api.oauth2.domains.profile.ProfileRetrievalException;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.StateExpiredException;
import org.bkd.social_media_scheduler_api.oauth2.domains.state.StateNotFoundException;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.Token;
import org.bkd.social_media_scheduler_api.oauth2.domains.token.TokenExchangeException;

import java.util.UUID;

public interface HandleCallbackUseCase {

  Token handleOAuth2Callback(String code, String scopes, String state, Platform platform, UUID applicationId) throws
                                                                                                              UnsupportedPlatformException,
                                                                                                              StateNotFoundException,
                                                                                                              TokenExchangeException,
                                                                                                              StateExpiredException,
                                                                                                              ProfileRetrievalException;
}
