package com.omegafrog.crudboard.web;

import com.omegafrog.crudboard.dto.PostsListResponseDto;
import com.omegafrog.crudboard.dto.PostsResponseDto;
import com.omegafrog.crudboard.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {


    private final PostsService postsService;
    @GetMapping("/")
    public String home(Model model){
        List<PostsListResponseDto> postsListResponseDto = postsService.findAllDesc();
        model.addAttribute("posts", postsListResponseDto);
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto responseDto=postsService.findById(id);
        model.addAttribute("post", responseDto);
        return "posts-update";
    }
}
