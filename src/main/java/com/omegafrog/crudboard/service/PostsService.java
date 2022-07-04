package com.omegafrog.crudboard.service;

import com.omegafrog.crudboard.domain.Posts;
import com.omegafrog.crudboard.domain.PostsRepository;
import com.omegafrog.crudboard.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        Posts post = Posts.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .author(requestDto.getAuthor())
                .build();

        return postsRepository.save(post).getId();
    }
}
