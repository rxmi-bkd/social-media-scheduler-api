package org.bkd.social_media_scheduler_api.authentication.core.ports.in;

import org.bkd.social_media_scheduler_api.authentication.domains.ApiKey;

import java.util.UUID;

public interface CreateApiKeyUseCase {

  ApiKey createApiKey(UUID applicationId);
}
