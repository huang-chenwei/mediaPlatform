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
@Table(name = "Comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "PostID", nullable = false)
    private Post post;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt;
}