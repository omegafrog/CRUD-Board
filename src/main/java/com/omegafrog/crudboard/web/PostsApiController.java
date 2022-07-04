package com.omegafrog.crudboard.web;

import com.omegafrog.crudboard.dto.PostsSaveRequestDto;
import com.omegafrog.crudboard.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto postsSaveRequestDto){
        System.out.println("hi"+postsSaveRequestDto.toString());
        return postsService.save(postsSaveRequestDto);
    }
}
