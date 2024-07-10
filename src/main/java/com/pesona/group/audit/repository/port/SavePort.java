package com.pesona.group.audit.repository.port;

import com.pesona.group.audit.domain.AuditTrail;
import jakarta.transaction.SystemException;

public interface SavePort {
      AuditTrail create(AuditTrail auditTrail) throws SystemException;
}
