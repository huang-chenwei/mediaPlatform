package com.mediaplatform.repository;


import com.mediaplatform.bean.pojo.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
 @Query("SELECT c FROM Comment c LEFT JOIN FETCH c.user WHERE c.post.id = :postId ORDER BY c.createdAt DESC")
 List<Comment> findByPostIdWithUser(Integer postId);
}