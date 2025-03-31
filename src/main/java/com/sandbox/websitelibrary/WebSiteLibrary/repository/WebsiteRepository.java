package com.sandbox.websitelibrary.WebSiteLibrary.repository;



import com.sandbox.websitelibrary.WebSiteLibrary.model.Website;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WebsiteRepository extends JpaRepository<Website, Long> {
}
