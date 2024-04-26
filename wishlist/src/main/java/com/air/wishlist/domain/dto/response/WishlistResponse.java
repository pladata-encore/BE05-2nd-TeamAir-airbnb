package com.air.wishlist.domain.dto.response;

import com.air.wishlist.domain.dto.FavoriteDto;
import com.air.wishlist.domain.entity.Favorite;
import com.air.wishlist.domain.entity.Wishlist;

import java.util.List;

public record WishlistResponse(
        Integer id, String name, List<FavoriteDto> favoriteDtos
) {
    public static WishlistResponse from(Wishlist wishlist) {
        List<FavoriteDto> favoriteDtos = wishlist.getFavorates().stream()
                .map((el)->FavoriteDto.toDto(el))
                .toList();
        return new WishlistResponse(wishlist.getId(),wishlist.getName(),favoriteDtos);
    }
}
