package com.air.wishlist.domain.dto.request;

public record WishlistRequest(
        String wishlistName,
        Integer userId,
        Integer roomId,
        String cityName,
        String roomType,
        double starAvg,
        Integer commentCount,
        String roomName
) {
}
