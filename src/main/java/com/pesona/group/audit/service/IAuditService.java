package com.pesona.group.audit.service;


import com.pesona.group.audit.application.request.RequestAuditTrailDto;
import com.pesona.group.dto.Request.GlobalRequestPaginate;
import com.pesona.group.dto.Response.GlobalDatabaseActionResult;
import com.pesona.group.dto.Response.GlobalDatabaseActionResultPaginate;
import jakarta.transaction.SystemException;
import jakarta.ws.rs.NotFoundException;

import java.util.UUID;

public interface IAuditService {
    GlobalDatabaseActionResultPaginate getPagination(GlobalRequestPaginate requestPaginate) throws SystemException;

    GlobalDatabaseActionResult create(RequestAuditTrailDto requestAuditTrailDto) throws SystemException;

    GlobalDatabaseActionResult getById(UUID uuid) throws NotFoundException;
}
