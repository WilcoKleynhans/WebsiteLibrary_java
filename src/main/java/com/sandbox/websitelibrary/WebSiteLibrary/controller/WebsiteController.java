package com.sandbox.websitelibrary.WebSiteLibrary.controller;



import com.sandbox.websitelibrary.WebSiteLibrary.model.Website;
import com.sandbox.websitelibrary.WebSiteLibrary.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/websites")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    // Get all websites
    @GetMapping
    public List<Website> getAllWebsites() {
        return websiteService.getAllWebsites();
    }

    // Get website by id
    @GetMapping("/{id}")
    public ResponseEntity<Website> getWebsiteById(@PathVariable Long id) {
        Optional<Website> website = websiteService.getWebsiteById(id);
        return website.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new website
    @PostMapping
    public ResponseEntity<Website> createWebsite(@Valid @RequestBody Website website) {
        Website createdWebsite = websiteService.createWebsite(website);
        return new ResponseEntity<>(createdWebsite, HttpStatus.CREATED);
    }

    // Update an existing website
    @PutMapping("/{id}")
    public ResponseEntity<Website> updateWebsite(@PathVariable Long id, @Valid @RequestBody Website website) {
        Website updatedWebsite = websiteService.updateWebsite(id, website);
        return updatedWebsite != null ? ResponseEntity.ok(updatedWebsite) : ResponseEntity.notFound().build();
    }


    // Delete a website
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWebsite(@PathVariable Long id) {
        boolean isDeleted = websiteService.deleteWebsite(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}