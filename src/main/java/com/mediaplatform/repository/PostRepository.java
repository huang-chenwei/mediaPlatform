package com.mediaplatform.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.mediaplatform.bean.pojo.Post;
import com.mediaplatform.bean.pojo.User;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByUserIdOrderByCreatedAtDesc(Integer userId);
    
    @Procedure(name = "sp_CreatePost")
    Integer createPost(
        @Param("UserID") Integer userId,
        @Param("Content") String content,
        @Param("Image") String image
    );
	
}