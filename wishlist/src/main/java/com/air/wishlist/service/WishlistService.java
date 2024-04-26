package com.air.wishlist.service;

import com.air.wishlist.domain.dto.WishlistDto;
import com.air.wishlist.domain.dto.request.FavoriteRequest;
import com.air.wishlist.domain.dto.request.WishlistRequest;
import com.air.wishlist.domain.dto.response.FavoriteResponse;
import com.air.wishlist.domain.dto.response.WishlistResponse;
import com.air.wishlist.domain.entity.Favorite;
import com.air.wishlist.domain.entity.Wishlist;
import com.air.wishlist.domain.repository.FavoriteRepository;
import com.air.wishlist.domain.repository.WishlistRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final FavoriteRepository favoriteRepository;
    private final WishlistRepository wishlistRepository;


    public WishlistDto addRoomToWishlist(FavoriteRequest request) {

        Wishlist wishlist = Wishlist.builder()
                .id(request.wishlistId())
                .build();
        Favorite favorite = Favorite.builder()
                .roomId(request.roomId())
                .roomName(request.roomName())
                .roomType(request.roomType())
                .starAVG(request.starAvg())
                .commentCount(request.commentCount())
                .cityName(request.cityName())
                .wishlist(wishlist)
                .build();
        Favorite save = favoriteRepository.save(favorite);
        return new WishlistDto(save.getId(), save.getRoomName());
    }

    public void deleteWishlist(int id) {
        wishlistRepository.deleteById(id);
    }

    public void addWishlist(WishlistRequest request){
        Wishlist wishlist = Wishlist.builder()
                .name(request.wishlistName())
                .id(request.userId())
                .build();
        wishlistRepository.save(wishlist);

    }
    public void deleteRoomToWishlist(int id) {
        favoriteRepository.deleteById(id);
    }
    @Transactional
    // dirty checking
    public void updateWishlistName(int id, String newName){
        Wishlist wishlist = wishlistRepository.findById(id)
                .orElseThrow();
        wishlist.setName(newName);
    }

    public WishlistResponse wishlistInquiry(int id){
        Optional<Wishlist> byId = wishlistRepository.findById(id);
        Wishlist wishlist = byId.orElseThrow();
        WishlistResponse from = WishlistResponse.from(wishlist);
        return from;
    }

    public WishlistDto userWishlist(int id, String name){
        Optional<Wishlist> byId = wishlistRepository.findById(id);
        Wishlist wishlist = byId.orElseThrow();
        return new WishlistDto(wishlist.getId(), wishlist.getName());
    }
}
