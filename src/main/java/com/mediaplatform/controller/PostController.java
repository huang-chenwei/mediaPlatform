package com.mediaplatform.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.mediaplatform.bean.dto.PostDTO;
import com.mediaplatform.bean.pojo.Post;
import com.mediaplatform.service.PostService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Post> createPost(@AuthenticationPrincipal String phone, @Valid @RequestBody PostDTO postDto) {
        return ResponseEntity.ok(postService.createPost(phone, postDto));
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(@AuthenticationPrincipal String phone, @PathVariable Integer postId,
            @Valid @RequestBody PostDTO postDto) {
        postService.updatePost(postId, postDto, phone);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@AuthenticationPrincipal String phone, @PathVariable Integer postId) {
        postService.deletePost(postId, phone);
        return ResponseEntity.ok().build();
    }
}