package com.air.wishlist.domain.dto.request;

public record FavoriteRequest(
        Integer roomId,
        Integer wishlistId,
        String cityName,
        String roomType,
        double starAvg,
        Integer commentCount,
        String roomName
) {

}
