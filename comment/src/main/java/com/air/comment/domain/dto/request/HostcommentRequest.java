package com.air.comment.domain.dto.request;

import java.time.LocalDate;

public record HostcommentRequest(
        Integer commentId,

        String hostCommentContent,

        String hostName

) {
}
