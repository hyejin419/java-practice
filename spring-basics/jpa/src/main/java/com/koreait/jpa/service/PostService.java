package com.koreait.jpa.service;

import com.koreait.jpa.dto.PostResponseDTO;
import com.koreait.jpa.entity.MemberEntity;
import com.koreait.jpa.entity.PostEntity;
import com.koreait.jpa.repository.MemberRepository;
import com.koreait.jpa.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    public PostService(MemberRepository memberRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.postRepository = postRepository;
    }

    public void write(String username, String title, String content) {
        MemberEntity writer = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));
        PostEntity post = new PostEntity();
        post.setTitle(title);
        post.setContent(content);
        post.setWriter(writer);
        postRepository.save(post);
    }

    public List<PostResponseDTO> search(String keyword) {
        return postRepository.searchByTitle(keyword).stream()
                .map(PostResponseDTO::new).collect(Collectors.toList());
    }

    public List<PostResponseDTO> myPosts(String username) {
        return postRepository.findByWriterUsername(username).stream()
                .map(PostResponseDTO::new).collect(Collectors.toList());
    }


}
