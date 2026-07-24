package org.bkd.social_media_scheduler_api.authentication.frameworks;

import java.util.UUID;

public class TenantContext {

  public static UUID getTenant() {
    return AuthenticationContext.getAuthentication().getApplicationId();
  }
}
