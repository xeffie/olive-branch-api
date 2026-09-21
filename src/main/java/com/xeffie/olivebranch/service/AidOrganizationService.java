package com.xeffie.olivebranch.service;

import com.xeffie.olivebranch.model.AidCategory;
import com.xeffie.olivebranch.model.AidOrganization;
import com.xeffie.olivebranch.repository.AidOrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AidOrganizationService {

    private final AidOrganizationRepository repository;

    public AidOrganizationService(AidOrganizationRepository repository) {
        this.repository = repository;
    }

    public List<AidOrganization> getAllOrganizations() {
        return repository.findAll();
    }

    public AidOrganization getOrganizationById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Organization not found."));
    }

    public AidOrganization createOrganization(AidOrganization organization) {
        return repository.save(organization);
    }

    public List<AidOrganization> getOrganizationsByCategory(AidCategory category) {
        return repository.findByCategory(category);
    }


}
