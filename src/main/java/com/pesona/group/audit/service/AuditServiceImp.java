package com.pesona.group.audit.service;

import com.pesona.group.audit.application.request.RequestAuditTrailDto;
import com.pesona.group.audit.domain.AuditTrail;
import com.pesona.group.audit.repository.mapper.Mapper;
import com.pesona.group.audit.repository.port.LoadPort;
import com.pesona.group.audit.repository.port.SavePort;
import com.pesona.group.dto.Request.GlobalRequestPaginate;
import com.pesona.group.dto.Response.GlobalDatabaseActionResult;
import com.pesona.group.dto.Response.GlobalDatabaseActionResultPaginate;
import jakarta.transaction.SystemException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class AuditServiceImp implements IAuditService {
    private final LoadPort loadPort;
    private final SavePort savePort;
    private final Mapper mapper;

    public GlobalDatabaseActionResultPaginate getPagination(GlobalRequestPaginate requestPaginate) throws SystemException {
        try {
            Page<AuditTrail> auditTrails = loadPort.loadPagination(requestPaginate);
            assert auditTrails != null;
            List<AuditTrail> auditTrailsContent = auditTrails.getContent();

            GlobalDatabaseActionResultPaginate responsePaginate = new GlobalDatabaseActionResultPaginate();
            responsePaginate.setStatus(HttpStatus.OK);
            responsePaginate.setCode(HttpStatus.OK.value());
            responsePaginate.setMessage("SUCCESS");
            responsePaginate.setPayload(mapper.convertModelToDtoList(auditTrailsContent));
            responsePaginate.setPageIndex(auditTrails.getNumber());
            responsePaginate.setPageSize(auditTrails.getSize());
            responsePaginate.setTotalElements(auditTrails.getTotalElements());
            responsePaginate.setTotalPages(auditTrails.getTotalPages());
            responsePaginate.setNextPage(auditTrails.getPageable().next().getPageNumber());
            responsePaginate.setPreviousPage(auditTrails.getPageable().previousOrFirst().getPageNumber());

            return responsePaginate;
        } catch (Exception e) {
            throw new SystemException(String.valueOf(e));
        }

    }

    public GlobalDatabaseActionResult create(RequestAuditTrailDto requestAuditTrailDto) throws SystemException {
        try {
            AuditTrail auditTrail = savePort.create(mapper.convertDtoToModel(requestAuditTrailDto));

            GlobalDatabaseActionResult customResponse = new GlobalDatabaseActionResult();
            customResponse.setStatus(HttpStatus.CREATED);
            customResponse.setCode(HttpStatus.CREATED.value());
            customResponse.setMessage("SUCCESS");
            customResponse.setPayload(mapper.convertModelToDto(auditTrail));
            return customResponse;
        } catch (Exception e) {
            throw new SystemException(String.valueOf(e));
        }
    }

    public GlobalDatabaseActionResult getById(UUID uuid) throws NotFoundException {
        try {
            Optional<AuditTrail> optional = loadPort.loadById(uuid);
            GlobalDatabaseActionResult customResponse = new GlobalDatabaseActionResult();
            if (optional.isPresent()) {
                customResponse.setStatus(HttpStatus.OK);
                customResponse.setCode(HttpStatus.OK.value());
                customResponse.setMessage("SUCCESS");
                customResponse.setPayload(mapper.convertModelToDto(optional.get()));
            }

            return customResponse;
        } catch (Exception e) {
            throw new NotFoundException(e);
        }

    }
}
