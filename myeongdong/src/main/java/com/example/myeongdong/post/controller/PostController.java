package com.example.myeongdong.post.controller;


import com.example.myeongdong.post.dto.response.ImageUploadDto;
import com.example.myeongdong.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;


    // 해커톤에서 사용한 이미지 업로드 api
    @PostMapping("/images")
    public ImageUploadDto uploadImage (@RequestPart MultipartFile file) throws IOException {

        String imageUrl = postService.imageCreate(file);

        return new ImageUploadDto(imageUrl);
    }

}
