package org.bkd.social_media_scheduler_api.api_key.adapters.out;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bkd.social_media_scheduler_api.api_key.domains.Role;
import org.bkd.social_media_scheduler_api.application.adapters.out.ApplicationEntity;

import java.util.UUID;

@Getter
@Setter
@Entity
public class ApiKeyEntity {

  @Id
  @Column(nullable = false, updatable = false, unique = true)
  private UUID id;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;

  @Column(nullable = false)
  private String value;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ApplicationEntity application;
}
