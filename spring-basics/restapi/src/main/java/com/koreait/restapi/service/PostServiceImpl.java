package com.koreait.restapi.service;

import com.koreait.restapi.dto.PostDTO;
import com.koreait.restapi.dto.PostFileDTO;
import com.koreait.restapi.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Year;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;

    @Override
    public void insertPost(PostDTO postDTO) {
        postMapper.insertPost(postDTO);
    }

    @Override
    public List<PostDTO> getPosts(int page, int size) {
        int offset = (page - 1) * size;
        return postMapper.getPosts(offset, size);
    }

    @Override
    public void insertPostWithImage(PostDTO post, MultipartFile image) throws IOException {
        // 게시글 저장
        postMapper.insertPost(post); // DB에 insert 후 post.id에 PK가 자동 주입되어야 함

        // 이미지가 없으면 종료
        if (image == null || image.isEmpty()) return;

        String year = String.valueOf(Year.now().getValue());
        String uploadPath = "uploads/" + year + "/";
        Path uploadDir = Paths.get(uploadPath); // uploads/2025/
        if(!Files.exists(uploadDir)) {
            Files.createDirectories(uploadDir);
        }

        // 파일명 처리 + 저장
        String originalName = image.getOriginalFilename();
        String savedName = UUID.randomUUID() + "_" + originalName;
        Path imagePath = uploadDir.resolve(savedName);
        Files.copy(image.getInputStream(), imagePath);

        // 썸네일 생성
        String thumbnailName = "thumb_" + savedName;
        Path thumbnailPath = uploadDir.resolve(thumbnailName);
        BufferedImage originalImage = ImageIO.read(image.getInputStream());
        int thumbWidth = 200;
        int thumbHeight = (int) (((double) originalImage.getHeight() / originalImage.getWidth()) * thumbWidth);

        BufferedImage thumbImage = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = thumbImage.createGraphics();
        g.drawImage(originalImage, 0, 0, thumbWidth, thumbHeight, null);
        g.dispose();
        ImageIO.write(thumbImage, "jpg", thumbnailPath.toFile());

        PostFileDTO fileDTO = new PostFileDTO();
        fileDTO.setPostId(post.getId());
        fileDTO.setOriginalName(originalName);
        fileDTO.setSavedName(savedName);
        fileDTO.setThumbnailName(thumbnailName);
        fileDTO.setUploadPath(year + "/");
        postMapper.saveFile(fileDTO);
    }
}