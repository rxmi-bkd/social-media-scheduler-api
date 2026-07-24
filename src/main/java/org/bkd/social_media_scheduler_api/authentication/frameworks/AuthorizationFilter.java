package org.bkd.social_media_scheduler_api.authentication.frameworks;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bkd.social_media_scheduler_api.authentication.domains.ApiKey;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.bkd.social_media_scheduler_api.authentication.domains.Role.ADMIN;
import static org.bkd.social_media_scheduler_api.authentication.frameworks.SecurityConfiguration.ADMIN_BASE_PATH;

@Order(2)
@Component
public class AuthorizationFilter extends OncePerRequestFilter {

  private final static PathMatcher pathMatcher = new AntPathMatcher();

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    ApiKey apiKey = AuthenticationContext.getAuthentication();

    if (apiKey.getRole().getWeight() >= ADMIN.getWeight()) {
      filterChain.doFilter(request, response);
    } else {
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Insufficient rights to access this resource");
    }
  }

  @Override
  protected boolean shouldNotFilter(HttpServletRequest request) {
    String uri = request.getRequestURI();
    return !pathMatcher.match(ADMIN_BASE_PATH + "/**", uri);
  }
}
