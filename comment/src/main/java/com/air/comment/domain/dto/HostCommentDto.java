package com.air.comment.domain.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record HostCommentDto(

        Integer userId,

        Integer roomId,

         String userName,

         String commentContent,

         Integer commentStar,

         LocalDate commentCreatedAt
) {
}
