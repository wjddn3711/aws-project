package com.lee.book.springboot.awsproject.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanUp(){
        postsRepository.deleteAll();
    }

    @DisplayName("임의의 게시글을 저장한 후 불러온 게시글과 저장한 정보가 동일한지 확인")
    @Test
    void givenPosts_whenFindall_thenGetPosts(){
        // given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .author("wjddn3711@naver.com")
                .content(content)
                .title(title)
                .build());
        // When & Then
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    void BaseTimeEntity_등록() {
        // given
        LocalDateTime now = LocalDateTime.of(2022,3,15,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // When & Then
        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);

        System.out.println(">>>>> createdDate = "+posts.getCreatedDate()+"\nmodifiedDate = "+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);

    }
}