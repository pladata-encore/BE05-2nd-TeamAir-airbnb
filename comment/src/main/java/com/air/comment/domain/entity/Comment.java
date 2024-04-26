package com.air.comment.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Comment")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMMENT_ID")
    private Integer commentId;
    @Column(name = "USER_ID")
    private Integer userId;
    @Column(name = "ROOM_ID")
    private Integer roomId;
    @Column(name = "HOST_ID")
    private Integer hostId;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "COMMENT_STAR")
    private Integer commentStar;
    @Column(name="COMMENT_CONTENT")@Setter
    private String comment;
    @Column(name="COMMENT_CREATED_AT", columnDefinition = "time with time zone DEFAULT now()")
    private LocalDateTime commentCreatedAt;

//    @OneToOne
//    private HostComment hostComment;


}
