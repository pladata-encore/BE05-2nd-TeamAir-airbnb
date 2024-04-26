package com.air.comment.domain.dto.response;

import com.air.comment.domain.entity.HostComment;

import java.time.LocalDate;

public record HostcommentResponse(
        Integer hostCommentId,

        String hostName,

        String hostCommentContent,

        LocalDate hostCommentCreatedAt
) {
    public static HostcommentResponse from(HostComment hostComment) {
        return new HostcommentResponse(hostComment.getHostCommentId(),
                hostComment.getHostName(),
                hostComment.getHostCommentContent(),
                hostComment.getHostCommentCreatedAt());
    }
}
