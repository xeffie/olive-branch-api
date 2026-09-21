package com.xeffie.olivebranch.integration;

import com.xeffie.olivebranch.model.AidCategory;
import com.xeffie.olivebranch.model.AidOrganization;
import com.xeffie.olivebranch.repository.AidOrganizationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AidOrganizationIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AidOrganizationRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void shouldReturnAllOrganizations() throws Exception {
        repository.save(new AidOrganization("Medical Aid", "Provides medical support", AidCategory.MEDICAL, "https://example.org"));
        repository.save(new AidOrganization("Food Aid", "Provides food assistance", AidCategory.FOOD, "https://example.org"));

        mockMvc.perform(get("/api/organizations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

    }

    @Test
    void shouldReturnOrganizationsById() throws Exception {
        AidOrganization saved = repository.save(new AidOrganization("Food Aid", "Provides food assistance", AidCategory.FOOD, "https://example.org"));

        mockMvc.perform(get("/api/organizations/{id}", saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Food Aid"))
                .andExpect(jsonPath("$.category").value("FOOD"));
    }

    @Test
    void shouldFilterOrganizationsByCategory() throws Exception {
        repository.save(new AidOrganization("Medical Aid", "Provides medical support", AidCategory.MEDICAL, "https://example.org"));
        repository.save(new AidOrganization("Food Aid", "Provides food assistance", AidCategory.FOOD, "https://example.org"));

        mockMvc.perform(get("/api/organizations").param("category", "MEDICAL"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].category").value("MEDICAL"));
    }

    @Test
    void shouldCreateOrganization() throws Exception {
        AidOrganization aidOrganization = new AidOrganization("Food Aid", "Provides food assistance", AidCategory.FOOD, "https://example.org");

        mockMvc.perform(post("/api/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(aidOrganization)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Food Aid"));
    }


}
