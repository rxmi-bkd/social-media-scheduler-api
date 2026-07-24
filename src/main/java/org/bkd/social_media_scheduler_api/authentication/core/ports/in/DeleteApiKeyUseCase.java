package org.bkd.social_media_scheduler_api.authentication.core.ports.in;

import java.util.UUID;

public interface DeleteApiKeyUseCase {

  void deleteApiKey(UUID apiKeyId, UUID applicationId);
}
