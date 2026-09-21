package com.xeffie.olivebranch.controller;

import com.xeffie.olivebranch.model.AidCategory;
import com.xeffie.olivebranch.model.AidOrganization;
import com.xeffie.olivebranch.service.AidOrganizationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organizations")
public class AidOrganizationController {

    private final AidOrganizationService service;

    public AidOrganizationController(AidOrganizationService service) {
        this.service = service;
    }

    @GetMapping
    public List<AidOrganization> getOrganizations(@RequestParam(required = false) AidCategory category) {
        if (category != null) {
            return service.getOrganizationsByCategory(category);
        }

        return service.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public AidOrganization getOrganizationById(@PathVariable Long id) {
        return service.getOrganizationById(id);
    }

    @PostMapping
    public AidOrganization createOrganization(@RequestBody AidOrganization organization) {
        return service.createOrganization(organization);
    }
}
