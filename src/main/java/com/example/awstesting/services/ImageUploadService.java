package com.example.awstesting.services;

import com.example.awstesting.model.ImageLink;
import com.example.awstesting.repos.ImageLinkRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.core.sync.RequestBody;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class ImageUploadService {

    private final S3Client s3Client;
    private final String bucketName;
    private final ImageLinkRepo imageLinkRepo;

    public ImageUploadService(S3Client s3Client,
                              @Value("${aws.s3.bucket-name}") String bucketName,
                              ImageLinkRepo imageLinkRepo) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
        this.imageLinkRepo = imageLinkRepo;
    }

    public String uploadImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();

        Path tempFile = Files.createTempFile("upload-", fileName);
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        try (InputStream inputStream = Files.newInputStream(tempFile)) {
            PutObjectResponse putObjectResponse = s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(inputStream, Files.size(tempFile)));
        }

        GetUrlRequest getUrlRequest = GetUrlRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();

        String imageUrl = s3Client.utilities().getUrl(getUrlRequest).toString();

        ImageLink imageLink = ImageLink.builder()
                .URL(imageUrl)
                .build();
        imageLinkRepo.save(imageLink);

        return imageUrl;
    }
}
