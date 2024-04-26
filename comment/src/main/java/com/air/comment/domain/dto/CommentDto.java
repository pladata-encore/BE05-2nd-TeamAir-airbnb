package com.air.comment.domain.dto;

import java.time.LocalDate;

public record CommentDto(
        Integer commentId,

        Integer userId,

        Integer roomId,

        String userName,

        double starAvg,

        String comment,

        LocalDate commentCreatedAt
        ) {

}
