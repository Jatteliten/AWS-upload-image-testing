package com.example.awstesting.repos;

import com.example.awstesting.model.ImageLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageLinkRepo extends JpaRepository<ImageLink, UUID> {
    ImageLink findByURL(String url);
}
