package org.bkd.social_media_scheduler_api.oauth2.adapters.out.state;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.bkd.social_media_scheduler_api.application.adapters.out.ApplicationEntity;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
public class StateEntity {

  @Id
  @Column(nullable = false, updatable = false, unique = true)
  private UUID id;

  @Column(nullable = false)
  private String value;

  @Column(nullable = false)
  private Instant createdAt;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(nullable = false)
  private ApplicationEntity application;
}
