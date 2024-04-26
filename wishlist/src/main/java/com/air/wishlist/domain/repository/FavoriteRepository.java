package com.air.wishlist.domain.repository;

import com.air.wishlist.domain.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository
        extends JpaRepository<Favorite, Integer> {

}
