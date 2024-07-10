package com.pesona.group.audit.application.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestAuditTrailDto {
    private UUID id;
    private String userId;
    private String userName;
    private String roles;
    private String remoteIp;
    private String sessionId;
    private String action;
    private String functionName;
    private String errorMessage;
    private String oldModel;
    private String newModel;
    private String latency;
    @JsonIgnore
    private Date timestamp = null;
}
