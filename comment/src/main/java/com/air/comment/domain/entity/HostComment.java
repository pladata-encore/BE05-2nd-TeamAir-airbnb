package com.air.comment.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "HostComment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class HostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOST_COMMENT_ID")
    private Integer hostCommentId;
    @Column(name = "HOST_NAME")
    private String hostName;
    @Column(name = "HOST_COMMENT_CONTENT")@Setter
    private String hostCommentContent;
    @Column(name = "HOST_COMMENT_CREATED_AT")
    private LocalDate hostCommentCreatedAt;

    @Column(name = "COMMENT_ID")
    private Integer commentId;

}
