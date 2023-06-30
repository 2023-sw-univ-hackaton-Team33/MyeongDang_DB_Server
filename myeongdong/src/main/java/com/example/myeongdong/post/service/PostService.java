package com.example.myeongdong.post.service;


import com.example.myeongdong.exception.BusinessException;
import com.example.myeongdong.fileUpload.S3Service;
import com.example.myeongdong.post.dto.request.PostCreateRequestDto;
import com.example.myeongdong.post.dto.request.PostCreateResponseDto;
import com.example.myeongdong.post.dto.request.PostUpdateRequestDto;
import com.example.myeongdong.post.dto.response.PostSearchResponseDto;
import com.example.myeongdong.post.dto.response.PostUpdateResponseDto;
import com.example.myeongdong.post.entity.Post;
import com.example.myeongdong.post.repository.PostRepository;
import com.example.myeongdong.response.ErrorMessage;
import com.example.myeongdong.user.entity.User;
import com.example.myeongdong.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PostService {

    private final S3Service s3Uploader;


    // 이미지 업로드
    public String imageCreate (MultipartFile file) throws IOException {
        String imgUrl = s3Uploader.upload(file);

        return imgUrl;
    }


}
