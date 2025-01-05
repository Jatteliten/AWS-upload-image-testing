package com.example.awstesting.services;

import com.example.awstesting.model.ImageLink;
import com.example.awstesting.repos.ImageLinkRepo;
import org.springframework.stereotype.Service;

@Service
public class ImageLinkService {
    private final ImageLinkRepo imageLinkRepo;

    public ImageLinkService(ImageLinkRepo imageLinkRepo) {
        this.imageLinkRepo = imageLinkRepo;
    }

    public void saveImageLink(ImageLink imageLink){
        imageLinkRepo.save(imageLink);
    }

    public ImageLink getImageLinkByUrl(String url){
        return imageLinkRepo.findByURL(url);
    }

}
