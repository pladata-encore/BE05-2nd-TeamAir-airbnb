package com.air.comment.domain.dto.response;

import com.air.comment.domain.dto.CommentDto;
import com.air.comment.domain.entity.Comment;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CommentResponse(
        Integer commentId,

        Integer userId,

        Integer roomId,

        String userName,

        Integer commentStar,

        String comment,

        LocalDateTime commentCreatedAt
){
    public static CommentResponse form(Comment comment) {
        return new CommentResponse(comment.getCommentId(),
                comment.getUserId(),
                comment.getRoomId(),
                comment.getUserName(),
                comment.getCommentStar(),
                comment.getComment(),
                comment.getCommentCreatedAt());
    }
}
