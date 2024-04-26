package com.air.wishlist.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Wishlists")
@Getter @AllArgsConstructor @NoArgsConstructor
@Builder
public class Wishlist {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "WISHLIST_ID")
    private Integer id;
    @Column(name = "WISHLIST_NAME", nullable = false)
    private String name;
    // 유저 추가해야함
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wishlist")
    private List<Favorite> favorates;

    public void setName(String name) {
        this.name = name;
    }
}
