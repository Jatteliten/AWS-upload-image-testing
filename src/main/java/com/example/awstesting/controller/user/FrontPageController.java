package com.example.awstesting.controller.user;

import com.example.awstesting.services.PostService;
import com.example.awstesting.utils.DateForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class FrontPageController {
    private final PostService postService;

    public FrontPageController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/")
    public String loadFrontPage(Model model){
        model.addAttribute("postsToday", postService.convertPostsListToPostsWithoutIdList(
                postService.getPostsByDate(LocalDate.now())));
        model.addAttribute("dateForm", new DateForm());
        return "index";
    }

    @RequestMapping("/submit-date")
    public String loadFrontPageWithGivenDate(Model model, DateForm dateForm){
        model.addAttribute("postsToday", postService.convertPostsListToPostsWithoutIdList(
                postService.getPostsByDate(dateForm.getSelectedDate())));
        model.addAttribute("dateForm", new DateForm());
        return "index";
    }
}
