package com.xeffie.olivebranch.repository;

import com.xeffie.olivebranch.model.AidCategory;
import com.xeffie.olivebranch.model.AidOrganization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AidOrganizationRepository extends JpaRepository<AidOrganization, Long> {
    List<AidOrganization> findByCategory(AidCategory category);
}
