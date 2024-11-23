package com.mediaplatform.service;

import com.mediaplatform.bean.dto.CommentDTO;
import com.mediaplatform.bean.pojo.Comment;
import com.mediaplatform.bean.pojo.Post;
import com.mediaplatform.bean.pojo.User;
import com.mediaplatform.exception.CustomException;
import com.mediaplatform.repository.CommentRepository;
import com.mediaplatform.repository.PostRepository;
import com.mediaplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Comment createComment(String phone, CommentDTO commentDto) {
        User user = userRepository.findByPhone(phone)
            .orElseThrow(() -> new CustomException("用戶不存在"));

        Post post = postRepository.findById(commentDto.getPostId())
            .orElseThrow(() -> new CustomException("貼文不存在"));

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setContent(commentDto.getContent());

        return commentRepository.save(comment);
    }

    public List<Comment> getPostComments(Integer postId) {
        return commentRepository.findByPostIdWithUser(postId);
    }
}