package com.example.awstesting.services;

import com.example.awstesting.model.Post;
import com.example.awstesting.model.dto.postdto.PostWithoutIdDto;
import com.example.awstesting.repos.PostRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<PostWithoutIdDto> convertPostsListToPostsWithoutIdList(List<Post> posts){
        return posts.stream()
                .map(post -> PostWithoutIdDto.builder()
                        .text(post.getText())
                        .date(post.getDate())
                        .imageLink(post.getImageLink())
                        .build())
                .collect(Collectors.toList());
    }
}
