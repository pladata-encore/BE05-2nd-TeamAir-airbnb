package com.air.wishlist.controller;
// 숙소 추가, 숙소 삭제, 저장, 위시리스트 생성, 위시리스트 삭제
// 사진불러오기, 룸 디테일 정보로 이어지게?
import com.air.wishlist.domain.dto.WishlistDto;
import com.air.wishlist.domain.dto.request.FavoriteRequest;
import com.air.wishlist.domain.dto.request.WishlistRequest;
import com.air.wishlist.domain.dto.response.WishlistResponse;
import com.air.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wishlist")
@RequiredArgsConstructor
public class WishlistController {
    private final WishlistService wishlistService;

    @PostMapping("/room")
    public WishlistDto addRoomToWishlist(@RequestBody FavoriteRequest request) {
        return wishlistService.addRoomToWishlist(request);
    }
    @DeleteMapping("/{id}")
    public void deleteWishlist(@PathVariable("id") int id ){
        wishlistService.deleteWishlist(id);
    }
    @PostMapping
    public void addWishlist(@RequestBody WishlistRequest request){
        wishlistService.addWishlist(request);
    }
    @DeleteMapping("/room/{id}")
    public void deleteRoomToWishlist(@PathVariable("id") int id){
        wishlistService.deleteRoomToWishlist(id);
    }

    @PutMapping("/wishlist/{id}")
    public void updateWishlistName(@PathVariable("id") int id, @RequestBody String newName) {
        wishlistService.updateWishlistName(id, newName);
    }
    @GetMapping("/wishlistInquiry/{id}")
    public WishlistResponse wishlistInquiry(@PathVariable("id") int id){
        return wishlistService.wishlistInquiry(id);
    }

    @GetMapping("/user/{id}")
    public WishlistDto userWishlist(@PathVariable("id") int id, String name){
        return wishlistService.userWishlist(id, name);
    }

}
