package org.bkd.social_media_scheduler_api.application.adapters.out;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class ApplicationEntity {

  @Id
  @Column(nullable = false, updatable = false, unique = true)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String description;
}
