package com.pesona.group.audit.application.api;

import com.pesona.group.audit.application.request.RequestAuditTrailDto;
import com.pesona.group.audit.service.IAuditService;
import com.pesona.group.dto.Request.GlobalRequestPaginate;
import com.pesona.group.dto.Response.GlobalDatabaseActionResult;
import com.pesona.group.dto.Response.GlobalDatabaseActionResultPaginate;
import feign.Param;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Nullable;
import jakarta.transaction.SystemException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/audit-trail")
@RequiredArgsConstructor
@Tag(name = "Audit Trail", description = "Audit Log Action")
public class AuditControllerV1 {
    private final IAuditService service;

    @Operation(summary = "get all audit", description = "Returns all the audit")
    @GetMapping
    public ResponseEntity<GlobalDatabaseActionResultPaginate> getPagination(@Param Integer pageIndex, @Param Integer pageSize, @Param @Nullable String sortBy,
                                                                            @Param @Nullable String[] filterByColumns,
                                                                            @Param @Nullable String searchByAdv,
                                                                            @Param @Nullable String searchByAdvValue) throws SystemException {
        GlobalRequestPaginate requestPaginate = new GlobalRequestPaginate();
        requestPaginate.setPageIndex(pageIndex);
        requestPaginate.setPageSize(pageSize);
        requestPaginate.setSortBy(sortBy);
        requestPaginate.setFilterByColumns(filterByColumns);
        requestPaginate.setSearchByAdv(searchByAdv);
        requestPaginate.setSearchByAdvValue(searchByAdvValue);

        GlobalDatabaseActionResultPaginate responseList = service.getPagination(requestPaginate);

        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @Operation(summary = "get audit by id", description = "Returns the audit by id")
    @GetMapping("/")
    public ResponseEntity<GlobalDatabaseActionResult> getById(@Param String id) throws NotFoundException {
        GlobalDatabaseActionResult customResponse = service.getById(UUID.fromString(id));

        return new ResponseEntity<>(customResponse, HttpStatus.OK);
    }

    @Operation(summary = "add new audit", description = "Returns the new audit")
    @PostMapping(value = "", produces = "application/json")
    public ResponseEntity<GlobalDatabaseActionResult> create(@Validated @RequestBody RequestAuditTrailDto requestAuditTrailDto) throws SystemException {
        GlobalDatabaseActionResult customResponse = service.create(requestAuditTrailDto);

        return new ResponseEntity<>(customResponse, HttpStatus.CREATED);
    }

}