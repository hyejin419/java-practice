package com.koreait.restapi.mapper;

import com.koreait.restapi.dto.PostDTO;
import com.koreait.restapi.dto.PostFileDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Mapper
public interface PostMapper {
    void insertPost (PostDTO post);
    List<PostDTO> getPosts(@Param("offset") int offset, @Param("limit") int limit);
    void saveFile(PostFileDTO postFile);
}
