package com.xeffie.olivebranch.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.xeffie.olivebranch.model.AidCategory;
import com.xeffie.olivebranch.model.AidOrganization;
import com.xeffie.olivebranch.repository.AidOrganizationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class AidOrganizationServiceTest {

    @Mock
    private AidOrganizationRepository repository;

   @InjectMocks
   private AidOrganizationService service;

    @Test
    void shouldReturnAllOrganizations() {
        AidOrganization organization = new AidOrganization("Medical Aid", "Provides medical support.", null, "https://example.org");

        when(repository.findAll()).thenReturn(List.of(organization));

        List<AidOrganization> result = service.getAllOrganizations();

        assertEquals(1, result.size());
        assertEquals("Medical Aid", result.getFirst().getName());
    }

    @Test
    void shouldReturnOrganizationById() {
        AidOrganization organization = new AidOrganization("Food Aid", "Provides food assistance.", AidCategory.FOOD, "https://example.org");

        when(repository.findById(1L)).thenReturn(Optional.of(organization));

        AidOrganization result = service.getOrganizationById(1L);

        assertEquals("Food Aid", result.getName());
        assertEquals(AidCategory.FOOD, result.getCategory());
    }

    @Test
    void shouldThrowExceptionWhenOrganizationDoesNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> service.getOrganizationById(99L));
    }

    @Test
    void shouldReturnOrganizationsByCategory() {
        AidOrganization organization = new AidOrganization("Emergency Aid", "Provides emergency relief.", AidCategory.EMERGENCY, "https://example.org");

        when(repository.findByCategory(AidCategory.EMERGENCY)).thenReturn(List.of(organization));

        List<AidOrganization> result = service.getOrganizationsByCategory(AidCategory.EMERGENCY);

        assertEquals(1, result.size());
        assertEquals(AidCategory.EMERGENCY, result.getFirst().getCategory());
    }

}
