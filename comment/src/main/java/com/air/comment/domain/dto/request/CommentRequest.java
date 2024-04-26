package com.air.comment.domain.dto.request;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CommentRequest(

        Integer roomId,

        Integer commentStar,

        String comment

) {

}
