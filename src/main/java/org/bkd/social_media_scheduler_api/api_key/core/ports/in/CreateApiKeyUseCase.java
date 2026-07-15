package org.bkd.social_media_scheduler_api.api_key.core.ports.in;

import org.bkd.social_media_scheduler_api.api_key.domains.ApiKey;

import java.util.UUID;

public interface CreateApiKeyUseCase {

  ApiKey createApiKey(UUID applicationId);
}
