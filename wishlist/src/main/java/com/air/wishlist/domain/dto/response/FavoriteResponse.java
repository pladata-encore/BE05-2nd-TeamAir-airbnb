package com.air.wishlist.domain.dto.response;

import com.air.wishlist.domain.dto.FavoriteDto;
import com.air.wishlist.domain.dto.WishlistDto;
import com.air.wishlist.domain.entity.Favorite;
import com.air.wishlist.domain.entity.Wishlist;

public record FavoriteResponse(
        Integer id,
        Integer roomId,
        String cityName,
        String roomType,
        double starAVG,
        Integer commentCount,
        String roomName,
        WishlistDto wishlist
) {
    public static FavoriteResponse from(Favorite favorite) {
        WishlistDto wishlistDto = new WishlistDto(favorite.getWishlist().getId(), favorite.getWishlist().getName());
        return new FavoriteResponse(favorite.getId(), favorite.getRoomId(), favorite.getCityName(),
                favorite.getRoomType(), favorite.getStarAVG(), favorite.getCommentCount(), favorite.getRoomName(),
                wishlistDto
        );

    }
}
