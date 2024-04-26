package com.air.user.global.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Integer id;
    @Column(name = "USER_EMAIL", unique = true) @Setter
    private String email;
    @Column(name = "USER_PASSWORD", nullable = false) @Setter
    private String password;
    @Column(name = "USER_NAME", nullable = false) @Setter
    private String userName;
    @Column(name = "USER_DISABLE", nullable = false, columnDefinition = "boolean default false") @Setter
    private boolean userDisable;
}
