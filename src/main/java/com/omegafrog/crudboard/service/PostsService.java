package com.omegafrog.crudboard.service;

import com.omegafrog.crudboard.domain.Posts;
import com.omegafrog.crudboard.domain.PostsRepository;
import com.omegafrog.crudboard.dto.PostsListResponseDto;
import com.omegafrog.crudboard.dto.PostsResponseDto;
import com.omegafrog.crudboard.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsSaveRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(()->
            new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return posts.update(requestDto.getTitle(), requestDto.getContent());
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        PostsResponseDto responseDto = new PostsResponseDto(posts);
        return responseDto;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        List<Posts> allDesc = postsRepository.findAllDesc();

        return allDesc.stream().map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
