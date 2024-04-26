package com.air.comment.domain.repository;

import com.air.comment.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository
        extends JpaRepository<Comment, Integer> {
    public List<Comment> findAllByRoomId(Integer roomId);
}
