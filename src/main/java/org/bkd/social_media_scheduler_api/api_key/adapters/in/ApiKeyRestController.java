package org.bkd.social_media_scheduler_api.api_key.adapters.in;

import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.api_key.core.ports.in.CreateApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.core.ports.in.DeleteApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.core.ports.in.ReadApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.domains.ApiKey;
import org.bkd.social_media_scheduler_api.security.AuthenticationContext;
import org.bkd.social_media_scheduler_api.security.TenantContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.bkd.social_media_scheduler_api.security.SecurityConfiguration.ADMIN_BASE_PATH;

@RestController
@RequiredArgsConstructor
public class ApiKeyRestController {

  private final ReadApiKeyUseCase apiKeyReadService;
  private final DeleteApiKeyUseCase apiKeyDeleteService;
  private final CreateApiKeyUseCase apiKeyCreateService;
  private final ApiKeyResponseMapper apiKeyResponseMapper;

  @GetMapping(ADMIN_BASE_PATH + "/apiKeys")
  public List<ApiKeyResponse> readApiKeys() {
    UUID applicationId = TenantContext.getTenant();
    List<ApiKey> apiKeys = apiKeyReadService.readApiKeys(applicationId);
    return apiKeyResponseMapper.toApiKeyResponses(apiKeys);
  }

  @PostMapping(ADMIN_BASE_PATH + "/apiKey")
  public ApiKeyResponse createApiKey() {
    ApiKey apiKey = AuthenticationContext.getAuthentication();
    UUID applicationId = apiKey.getApplicationId();
    ApiKey newApiKey = apiKeyCreateService.createApiKey(applicationId);
    return apiKeyResponseMapper.toApiKeyResponse(newApiKey);
  }

  @DeleteMapping(ADMIN_BASE_PATH + "/apiKey/{apiKeyId}")
  public void deleteApiKey(@PathVariable UUID apiKeyId) {
    UUID applicationId = TenantContext.getTenant();
    apiKeyDeleteService.deleteApiKey(apiKeyId, applicationId);
  }
}
