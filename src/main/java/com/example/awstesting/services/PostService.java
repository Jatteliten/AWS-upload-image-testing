package com.example.awstesting.services;

import com.example.awstesting.model.Post;
import com.example.awstesting.repos.PostRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PostService {
    private final PostRepo postRepo;

    public PostService(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    public void savePost(Post post){
        postRepo.save(post);
    }

    public List<Post> getPostsByDate(LocalDate date){
        return postRepo.findByDate(date);
    }
}
