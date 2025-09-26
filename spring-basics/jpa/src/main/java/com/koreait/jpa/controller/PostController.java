package com.koreait.jpa.controller;

import com.koreait.jpa.component.JwtTokenProvider;
import com.koreait.jpa.dto.PostResponseDTO;
import com.koreait.jpa.repository.PostRepository;
import com.koreait.jpa.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final JwtTokenProvider jwtTokenProvider;
    private final PostService postService;

    public PostController(PostService postService, JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.postService = postService;
    }

    @PostMapping("/write")
    public ResponseEntity<String> write(@RequestHeader("Authorization") String authHeader, @RequestBody Map<String, String> request) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtTokenProvider.getUsername(token);
        postService.write(username, request.get("title"), request.get("content"));
        return ResponseEntity.ok("작성 완료");
    }

    @GetMapping("/search")
    public List<PostResponseDTO> search(@RequestParam String keyword) {
        return postService.search(keyword);
    }

    @GetMapping("/my")
public List<PostResponseDTO> myPosts(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");
        String username = jwtTokenProvider.getUsername(token);
        return postService.myPosts(username);
    }
}