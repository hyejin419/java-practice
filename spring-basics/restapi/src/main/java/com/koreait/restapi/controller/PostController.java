package com.koreait.restapi.controller;

import com.koreait.restapi.dto.PostDTO;
import com.koreait.restapi.jwt.JwtUtil;
import com.koreait.restapi.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final JwtUtil jwtUtil;
    private final PostService postService;


//    @PostMapping
//    public ResponseEntity<?> create(@RequestBody PostDTO post, HttpServletRequest request) {
//        int userId = jwtUtil.getUserIdFromRequest(request);
//        post.setWriterId(userId);
//        postService.insertPost(post);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> create(@RequestPart("post") PostDTO post,
                                    @RequestPart(value="image", required=false) MultipartFile image,
                                    HttpServletRequest request) throws IOException{
        int userId = jwtUtil.getUserIdFromRequest(request);
        post.setWriterId(userId);
        postService.insertPostWithImage(post, image);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<?> list(@RequestParam(defaultValue = "1") int page,
                        @RequestParam(defaultValue = "10") int size) {
        return postService.getPosts(page, size);
    }
}