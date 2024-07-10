package com.pesona.group.audit.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuditTrail {
    @Id
    @GeneratedValue
    @UuidGenerator
    private UUID id;
    private String userId;
    private String userName;
    private String roles;
    private String remoteIp;
    private String sessionId;
    private String action;
    private String functionName;
    @Column(length = 10485760)
    private String errorMessage;
    @Column(length = 10485760)
    private String oldModel;
    @Column(length = 10485760)
    private String newModel;
    private String latency;
    @UpdateTimestamp
    private Date timestamp;
}
