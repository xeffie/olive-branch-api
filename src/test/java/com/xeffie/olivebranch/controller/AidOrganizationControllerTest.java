package com.xeffie.olivebranch.controller;

import com.xeffie.olivebranch.model.AidCategory;
import com.xeffie.olivebranch.model.AidOrganization;
import com.xeffie.olivebranch.service.AidOrganizationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;


import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AidOrganizationController.class)
@AutoConfigureMockMvc(addFilters = false)
public class AidOrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AidOrganizationService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnAllOrganizations() throws Exception {
        AidOrganization organization = new AidOrganization("Medical Aid", "Provides medical support", AidCategory.MEDICAL, "https://example.org");

        when(service.getAllOrganizations()).thenReturn(List.of(organization));

        mockMvc.perform(get("/api/organizations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Medical Aid"));
    }

    @Test
    void shouldReturnOrganizationsById() throws Exception {
        AidOrganization organization = new AidOrganization("Food Aid", "Provides food assistance.", AidCategory.FOOD, "https://example.org");

        when(service.getOrganizationById(1L)).thenReturn(organization);

        mockMvc.perform(get("/api/organizations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Food Aid"))
                .andExpect(jsonPath("$.category").value("FOOD"));
    }

    @Test
    void shouldFilterOrganizationsByCategory() throws Exception {
        AidOrganization organization = new AidOrganization("Emergency Aid", "Provides emergency relief.", AidCategory.EMERGENCY, "https://example.org");

        when(service.getOrganizationsByCategory(AidCategory.EMERGENCY)).thenReturn(List.of(organization));

        mockMvc.perform(get("/api/organizations").param("category", "EMERGENCY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].category").value("EMERGENCY"));

    }

    @Test
    void shouldCreateOrganization() throws Exception {
        AidOrganization organization = new AidOrganization("Medical Aid", "Provides medical support", AidCategory.MEDICAL, "https://example.org");

        when(service.createOrganization(any(AidOrganization.class)))
                .thenReturn(organization);

        mockMvc.perform(post("/api/organizations")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(organization)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Medical Aid"))
                .andExpect(jsonPath("$.category").value("MEDICAL"));
    }

}
