package com.pesona.group.audit.repository.base;

import com.pesona.group.audit.domain.AuditTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BaseRepository extends JpaRepository<AuditTrail, UUID> {

}
