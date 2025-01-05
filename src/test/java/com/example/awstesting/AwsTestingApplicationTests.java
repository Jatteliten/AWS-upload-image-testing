package com.example.awstesting;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.awstesting.controller.admin.SubmitPostController;
import com.example.awstesting.controller.autorization.AuthController;
import com.example.awstesting.controller.user.FrontPageController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class AwsTestingApplicationTests {

    @Autowired
    SubmitPostController submitPostController;
    @Autowired
    AuthController authController;
    @Autowired
    FrontPageController frontPageController;

    @Test
    void contextLoads() {
        assertThat(submitPostController).isNotNull();
        assertThat(authController).isNotNull();
        assertThat(frontPageController).isNotNull();
    }

}
