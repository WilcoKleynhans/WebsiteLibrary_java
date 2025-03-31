package com.sandbox.websitelibrary.WebSiteLibrary.service;


import com.sandbox.websitelibrary.WebSiteLibrary.model.Website;
import com.sandbox.websitelibrary.WebSiteLibrary.repository.WebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WebsiteService {

    @Autowired
    private WebsiteRepository websiteRepository;

    public List<Website> getAllWebsites() {
        return websiteRepository.findAll();
    }

    public Optional<Website> getWebsiteById(Long id) {
        return websiteRepository.findById(id);
    }

    public Website createWebsite(Website website) {
        return websiteRepository.save(website);
    }

    public Website updateWebsite(Long id, Website website) {
        if (websiteRepository.existsById(id)) {
            website.setId(id);
            return websiteRepository.save(website);
        }
        return null;
    }

    public boolean deleteWebsite(Long id) {
        if (websiteRepository.existsById(id)) {
            websiteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
