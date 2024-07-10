package com.pesona.group.audit.repository.mapper;


import com.pesona.group.audit.application.request.RequestAuditTrailDto;
import com.pesona.group.audit.domain.AuditTrail;

import java.util.List;

@org.mapstruct.Mapper
public interface Mapper {

    RequestAuditTrailDto convertModelToDto(AuditTrail auditTrail);

    AuditTrail convertDtoToModel(RequestAuditTrailDto requestAuditTrailDto);

    List<Object> convertModelToDtoList(List<AuditTrail> auditTrailList);

    List<AuditTrail> convertDtoToModelList(List<RequestAuditTrailDto> requestAuditTrailDtoList);
}
