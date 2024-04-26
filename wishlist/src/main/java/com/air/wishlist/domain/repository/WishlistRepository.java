package com.air.wishlist.domain.repository;

import com.air.wishlist.domain.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository
extends JpaRepository<Wishlist, Integer> {

}
