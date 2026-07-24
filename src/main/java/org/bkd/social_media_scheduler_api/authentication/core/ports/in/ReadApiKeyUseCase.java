package org.bkd.social_media_scheduler_api.authentication.core.ports.in;

import org.bkd.social_media_scheduler_api.authentication.domains.ApiKey;
import org.bkd.social_media_scheduler_api.authentication.domains.ApiKeyNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ReadApiKeyUseCase {

  ApiKey readApiKey(String apiKeyValue, UUID applicationId) throws ApiKeyNotFoundException;

  List<ApiKey> readApiKeys(UUID applicationId);
}
