package com.xeffie.olivebranch.model;

import jakarta.persistence.*;

@Entity
public class AidOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private AidCategory category;

    private String website;

    public AidOrganization() {

    }

    public AidOrganization(String name, String description, AidCategory category, String website) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.website = website;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AidCategory getCategory() {
        return category;
    }

    public void setCategory(AidCategory category) {
        this.category = category;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}
