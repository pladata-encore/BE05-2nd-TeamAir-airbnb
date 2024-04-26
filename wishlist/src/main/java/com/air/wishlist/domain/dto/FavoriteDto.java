package com.air.wishlist.domain.dto;

import com.air.wishlist.domain.entity.Favorite;

public record FavoriteDto(
        Integer id,
        Integer roomId,
        String cityName,
        String roomType,
        double starAVG,
        Integer commentCount,
        String roomName

) {
    public static FavoriteDto toDto (Favorite favorite){
        FavoriteDto favoriteDto = new FavoriteDto(favorite.getId(), favorite.getRoomId(), favorite.getCityName(), favorite.getRoomType(), favorite.getStarAVG(), favorite.getCommentCount(), favorite.getRoomName());
        return favoriteDto;
    }
}

