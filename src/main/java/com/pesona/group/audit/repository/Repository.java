package com.pesona.group.audit.repository;

import com.pesona.group.audit.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface Repository extends BaseRepository {
  // do a custom query here
}
