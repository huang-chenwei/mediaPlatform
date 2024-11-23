package com.mediaplatform.service;

import com.mediaplatform.bean.dto.PostDTO;
import com.mediaplatform.bean.pojo.Post;
import com.mediaplatform.bean.pojo.User;
import com.mediaplatform.exception.CustomException;
import com.mediaplatform.repository.PostRepository;
import com.mediaplatform.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Post createPost(String phone, PostDTO postDto) {
        User user = userRepository.findByPhone(phone)
            .orElseThrow(() -> new CustomException("用戶不存在"));

        Post post = new Post();
        post.setUser(user);
        post.setContent(postDto.getContent());
        post.setImage(postDto.getImage());

        return postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void updatePost(Integer postId, PostDTO postDto, String phone) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new CustomException("貼文不存在"));

        User user = userRepository.findByPhone(phone)
            .orElseThrow(() -> new CustomException("用戶不存在"));

        if (!post.getUser().getUserId().equals(user.getUserId())) {
            throw new CustomException("無權限修改此貼文");
        }

        post.setContent(postDto.getContent());
        post.setImage(postDto.getImage());
        postRepository.save(post);
    }

    public void deletePost(Integer postId, String phone) {
        Post post = postRepository.findById(postId)
            .orElseThrow(() -> new CustomException("貼文不存在"));

        User user = userRepository.findByPhone(phone)
            .orElseThrow(() -> new CustomException("用戶不存在"));

        if (!post.getUser().getUserId().equals(user.getUserId())) {
            throw new CustomException("無權限刪除此貼文");
        }

        postRepository.delete(post);
    }
}