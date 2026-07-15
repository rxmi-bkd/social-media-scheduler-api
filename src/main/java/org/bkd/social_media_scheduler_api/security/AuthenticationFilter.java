package org.bkd.social_media_scheduler_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.bkd.social_media_scheduler_api.api_key.core.ports.in.ReadApiKeyUseCase;
import org.bkd.social_media_scheduler_api.api_key.domains.ApiKey;
import org.bkd.social_media_scheduler_api.api_key.domains.ApiKeyNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

import static org.springframework.util.StringUtils.hasText;


@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {

  private final static String API_KEY_HEADER = "X-API-KEY";
  private final static String TENANT_HEADER = "X-APPLICATION-ID";

  private final static PathMatcher pathMatcher = new AntPathMatcher();

  private final ReadApiKeyUseCase apiKeyReadService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    try {
      String apiKey = extractApiKeyFromHeaders(request);
      UUID tenant = extractTenantFromHeaders(request);
      ApiKey apiKey_ = apiKeyReadService.readApiKey(apiKey, tenant);
      AuthenticationContext.setAuthentication(apiKey_);
      filterChain.doFilter(request, response);
    } catch (IllegalArgumentException | NullPointerException e) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    } catch (ApiKeyNotFoundException e) {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    } finally {
      AuthenticationContext.clear();
    }
  }

  private String extractApiKeyFromHeaders(HttpServletRequest request) {
    String apiKey = request.getHeader(API_KEY_HEADER);

    if (!hasText(apiKey)) {
      throw new NullPointerException("API key is null or empty");
    }

    return apiKey;
  }

  private UUID extractTenantFromHeaders(HttpServletRequest request) {
    String tenant = request.getHeader(TENANT_HEADER);

    if (!hasText(tenant)) {
      throw new NullPointerException("Tenant is null or empty");
    }

    try {
      return UUID.fromString(tenant);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Tenant ID is not a valid UUID");
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String uri = request.getRequestURI();
    return pathMatcher.match("/api/login/oauth/code/**", uri);
  }
}
