package com.example.awstesting.controller.admin;

import com.example.awstesting.model.ImageLink;
import com.example.awstesting.model.Post;
import com.example.awstesting.services.ImageUploadService;
import com.example.awstesting.services.PostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Controller
@PreAuthorize("hasAuthority('Admin')")
public class SubmitPostController {
    private final PostService postService;
    private final ImageUploadService imageUploadService;

    public SubmitPostController(PostService postService, ImageUploadService imageUploadService) {
        this.postService = postService;
        this.imageUploadService = imageUploadService;
    }

    @RequestMapping("/post")
    public String createPost(){
        return "admin/uploadPost";
    }

    @PostMapping("/post/submitPost")
    public String submitPost(@RequestParam("file") MultipartFile file, String postText){
        try {
            String imageUrl = imageUploadService.uploadImage(file);

            ImageLink imageLink = ImageLink.builder()
                    .URL(imageUrl)
                    .build();

            Post post = Post.builder()
                    .text(postText)
                    .date(LocalDate.now())
                    .imageLink(imageLink)
                    .build();

            postService.savePost(post);
        } catch (IOException e) {
            throw new RuntimeException("Error uploading image", e);
        }

        return "redirect:/";
    }

}
