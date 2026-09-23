package com.xeffie.olivebranch.config;

import com.xeffie.olivebranch.model.AidCategory;
import com.xeffie.olivebranch.model.AidOrganization;
import com.xeffie.olivebranch.repository.AidOrganizationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final AidOrganizationRepository repository;

    public DataSeeder(AidOrganizationRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        if (repository.count() > 0) {
            return;
        }

        repository.saveAll(List.of(new AidOrganization("Olive Medical Relief", "Provides medical assistance and healthcare support.", AidCategory.MEDICAL, "https://example.org"), new AidOrganization(
                        "Community Food Support",
                        "Provides food and essential supplies to families.",
                        AidCategory.FOOD,
                        "https://example.org"
                ),
                new AidOrganization(
                        "Palestine Children's Relief Fund",
                        "Provides medical and humanitarian support for children.",
                        AidCategory.CHILDREN,
                        "https://www.pcrf.net"
                ),
                new AidOrganization(
                        "Emergency Relief Network",
                        "Provides emergency humanitarian assistance.",
                        AidCategory.EMERGENCY,
                        "https://example.org"
                ),
                new AidOrganization(
                        "Education Support Initiative",
                        "Supports access to education and learning resources.",
                        AidCategory.EDUCATION,
                        "https://example.org"
                )
                ));
    }
}
