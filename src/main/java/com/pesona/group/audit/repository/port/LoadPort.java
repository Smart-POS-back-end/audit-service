package com.pesona.group.audit.repository.port;

import com.pesona.group.audit.domain.AuditTrail;
import com.pesona.group.dto.Request.GlobalRequestPaginate;
import jakarta.ws.rs.NotFoundException;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.UUID;

public interface LoadPort {
    Page<AuditTrail> loadPagination(GlobalRequestPaginate requestPaginate) throws NotFoundException;

    Optional<AuditTrail> loadById(UUID uuid) throws NotFoundException;
}
