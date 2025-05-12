package com.fleetie.service;

import com.fleetie.entity.ServiceLog;
import com.fleetie.repository.ServiceLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceLogService {

    private final ServiceLogRepository serviceLogRepository;

    public ServiceLog createEntry(ServiceLog entry) {
        return serviceLogRepository.save(entry);
    }

    public List<ServiceLog> getEntriesByVehicleId(UUID id) {
        return serviceLogRepository.findAllByVehicleId(id);
    }

    public Optional<ServiceLog> getEntry(UUID id) {
        return serviceLogRepository.findById(id);
    }

    public void deleteEntry(UUID id) {
        serviceLogRepository.deleteById(id);
    }
}
