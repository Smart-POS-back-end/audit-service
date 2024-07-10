package com.pesona.group.audit.repository;

import com.pesona.group.audit.domain.AuditTrail;
import com.pesona.group.audit.repository.port.LoadPort;
import com.pesona.group.audit.repository.port.SavePort;
import com.pesona.group.dto.Request.GlobalRequestPaginate;
import jakarta.transaction.SystemException;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class Adapter implements LoadPort, SavePort {
    private final Repository repository;

    @Override
    public Page<AuditTrail> loadPagination(GlobalRequestPaginate requestPaginate) throws NotFoundException {
        try {
            Sort.Direction sortDirection = Sort.Direction.ASC;
            String sortBy = "timestamp";

            if (requestPaginate.getSortBy() != null && requestPaginate.getSortBy().equalsIgnoreCase("DESC")) {
                sortDirection = Sort.Direction.DESC;
            }

            Pageable pageable;

            Sort sort = Sort.by(sortDirection, sortBy);

            pageable = PageRequest.of(
                    requestPaginate.getPageIndex(),
                    requestPaginate.getPageSize(),
                    sort
            );
            return repository.findAll(pageable);
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public Optional<AuditTrail> loadById(UUID uuid) throws NotFoundException {
        try {
            return Optional.of(repository.findById(uuid).orElseThrow(NotFoundException::new));
        } catch (Exception e) {
            throw new NotFoundException();
        }
    }

    @Override
    public AuditTrail create(AuditTrail auditTrail) throws SystemException {
        try {
            return repository.save(auditTrail);
        } catch (Exception e) {
            throw new SystemException();
        }
    }
}
