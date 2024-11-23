package com.mediaplatform.bean.pojo;

import lombok.Data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Posts")
public class Post {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Integer postId;

 @ManyToOne
 @JoinColumn(name = "UserID", nullable = false)
 private User user;

 @Column(nullable = false)
 private String content;

 private String image;

 private LocalDateTime createdAt;
}