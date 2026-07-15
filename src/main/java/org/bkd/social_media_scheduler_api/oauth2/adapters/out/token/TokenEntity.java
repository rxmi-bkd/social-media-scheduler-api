package org.bkd.social_media_scheduler_api.oauth2.adapters.out.token;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bkd.social_media_scheduler_api.application.adapters.out.ApplicationEntity;
import org.bkd.social_media_scheduler_api.oauth2.domains.platform.Platform;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "oauth2_token")
public class TokenEntity {

  @Id
  @Column(nullable = false, updatable = false, unique = true)
  private UUID id;

  @Column(nullable = false)
  private String openId;

  @Column(nullable = false)
  private String scope;

  @Column(nullable = false)
  private String accessToken;

  @Column(nullable = false)
  private String refreshToken;

  @Column(nullable = false)
  private Instant accessTokenExpiresAt;

  @Column(nullable = false)
  private Instant refreshTokenExpiresAt;

  @Column(nullable = false)
  private String tokenType;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Platform platform;

  @Column(nullable = false)
  private Instant createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ApplicationEntity application;
}
