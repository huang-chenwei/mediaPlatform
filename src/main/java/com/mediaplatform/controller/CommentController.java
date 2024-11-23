package com.mediaplatform.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.mediaplatform.bean.dto.CommentDTO;
import com.mediaplatform.bean.pojo.Comment;
import com.mediaplatform.service.CommentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@AuthenticationPrincipal String phone,
            @Valid @RequestBody CommentDTO commentDTO) {
        return ResponseEntity.ok(commentService.createComment(phone, commentDTO));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getPostComments(@PathVariable Integer postId) {
        return ResponseEntity.ok(commentService.getPostComments(postId));
    }
}